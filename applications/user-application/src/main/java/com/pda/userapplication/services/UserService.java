package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ConflictException;
import com.pda.exceptionhandler.exceptions.NotFoundException;
import com.pda.exceptionhandler.exceptions.UnAuthorizedException;
import com.pda.s3utils.service.S3Service;
import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.ServicePurpose;
import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.UserInfoEncoder;
import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenInfo;
import com.pda.tofinsecurity.jwt.TokenableUser;
import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.Birth;
import com.pda.userapplication.domains.vo.ImageUrl;
import com.pda.userapplication.domains.vo.Nickname;
import com.pda.userapplication.domains.vo.TofinId;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.ConnectAssetUseCase;
import com.pda.userapplication.services.in.GetUserDetailInfo;
import com.pda.userapplication.services.in.GetUserUseCase;
import com.pda.userapplication.services.in.IsAvailableContact;
import com.pda.userapplication.services.in.IsAvailableTofinIdUseCase;
import com.pda.userapplication.services.in.ReissueUseCase;
import com.pda.userapplication.services.in.SetPublicOptionUseCase;
import com.pda.userapplication.services.in.SetTendencyUseCase;
import com.pda.userapplication.services.in.SignInUseCase;
import com.pda.userapplication.services.in.SignUpUseCase;
import com.pda.userapplication.services.in.UpdateUserUseCase;
import com.pda.userapplication.services.in.dto.req.ConnectAssetsServiceRequest;
import com.pda.userapplication.services.in.dto.req.SearchUserServiceRequest;
import com.pda.userapplication.services.in.dto.req.SetPublicOptionServiceRequest;
import com.pda.userapplication.services.in.dto.req.SetTendencyServiceRequest;
import com.pda.userapplication.services.in.dto.req.SignInServiceRequest;
import com.pda.userapplication.services.in.dto.req.SignUpServiceRequest;
import com.pda.userapplication.services.in.dto.req.UpdateProfileServiceRequest;
import com.pda.userapplication.services.in.dto.res.AvailableContactServiceResponse;
import com.pda.userapplication.services.in.dto.res.AvailableTofinIdServiceResponse;
import com.pda.userapplication.services.in.dto.res.ConnectAssetInfoResponse;
import com.pda.userapplication.services.in.dto.res.GetUserPagingResponse;
import com.pda.userapplication.services.in.dto.res.GetUserSummaryResponse;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;
import com.pda.userapplication.services.in.dto.res.UserDetailInfoResponse;
import com.pda.userapplication.services.in.dto.res.UserServiceResponse;
import com.pda.userapplication.services.out.GetAssetsOutputPort;
import com.pda.userapplication.services.out.ReadFollowOutputPort;
import com.pda.userapplication.services.out.ReadNormalUserOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.RefreshTokenOutputPort;
import com.pda.userapplication.services.out.SaveNormalUserOutputPort;
import com.pda.userapplication.services.out.SaveUserOutputPort;
import com.pda.userapplication.services.out.SendCreditOutputPort;
import com.pda.userapplication.services.out.SendUpdateUserOutputPort;
import com.pda.userapplication.services.out.dto.req.SearchUserOutputRequest;
import com.pda.userapplication.services.out.dto.req.SendCreditOutputRequest;
import com.pda.userapplication.services.out.dto.req.UserUpdateOutputRequest;
import com.pda.userapplication.services.out.dto.res.AccountResponse;
import com.pda.userapplication.services.out.dto.res.AssetInfoResponse;
import com.pda.userapplication.services.out.dto.res.CardResponse;
import com.pda.userapplication.services.out.dto.res.FollowInfoResponse;
import com.pda.userapplication.services.out.dto.res.SearchUserPagingOutputResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements SignUpUseCase, ReissueUseCase,
    IsAvailableTofinIdUseCase, SignInUseCase, ConnectAssetUseCase,
    IsAvailableContact, SetPublicOptionUseCase, SetTendencyUseCase,
    GetUserDetailInfo, UpdateUserUseCase, GetUserUseCase {
    private final SaveUserOutputPort saveUserOutputPort;
    private final ReadUserOutputPort readUserOutputPort;
    private final UserInfoEncoder userInfoEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenOutputPort refreshTokenOutputPort;
    private final SaveNormalUserOutputPort saveNormalUserOutputPort;
    private final GetAssetsOutputPort getAssetsOutputPort;
    private final ReadNormalUserOutputPort readNormalUserOutputPort;
    private final SendCreditOutputPort sendCreditOutputPort;
    private final S3Service s3Service;
    private final SendUpdateUserOutputPort sendUpdateUserOutputPort;
    private final ReadFollowOutputPort readFollowOutputPort;

    @Transactional
    @Override
    public TokenInfoServiceResponse signUp(final SignUpServiceRequest request) {
        if (isDuplicateTofinId(TofinId.of(request.getTofinId())))
            throw new ConflictException("해당 아이디는 이미 존재합니다.");

        User user = saveUserOutputPort.save(NormalUser.builder()
            .toFinId(TofinId.of(request.getTofinId()))
            .userInfo(userInfoEncoder.hashed(request.getUserInfo()))
            .birth(Birth.of(request.getBirth()))
            .job(toJobFrom(request.getJob()))
            .profileImage(ImageUrl.of(request.getProfileImage()))
            .nickname(Nickname.of(request.getNickname()))
            .role(UserRole.NORMAL)
            .build());

        // 회원가입시 100 크레딧 지정
        sendCreditTo(user.getId().toLong(), 100L);

        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(user), user);
    }

    @Async
    public void sendCreditTo(Long userId, Long amount) {
        sendCreditOutputPort.addCredit(SendCreditOutputRequest.builder()
            .userId(userId)
            .amount(amount)
            .transactionDateTime(LocalDateTime.now())
            .build());
    }

    @Override
    public AvailableTofinIdServiceResponse isAvailableTofinId(final String tofinId) {
        try {
            boolean isAvailable = !isDuplicateTofinId(TofinId.of(tofinId));

            return AvailableTofinIdServiceResponse.builder()
                .tofinId(tofinId)
                .available(isAvailable)
                .reason(isAvailable?"사용 가능한 아이디 입니다":"이미 사용중인 아이디 입니다")
                .build();

        } catch (IllegalArgumentException e) {
            return AvailableTofinIdServiceResponse.builder()
                .tofinId(tofinId)
                .available(false)
                .reason("아이디는 8~30글자 사이, 영문+숫자 형식이어야 합니다.")
                .build();
        }
    }

    @Transactional
    @Override
    public TokenInfoServiceResponse signIn(final SignInServiceRequest request) {
        User user = readUserOutputPort.findByTofinId(TofinId.of(request.getTofinId()))
            .orElseThrow(() -> new UnAuthorizedException("아이디 혹은 비밀번호가 틀렸습니다."));

        if (!userInfoEncoder.matches(request.getUserInfo(), user.getUserInfo()))
            throw new UnAuthorizedException("아이디 혹은 비밀번호가 틀렸습니다.");

        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(user), user);
    }

    @Transactional
    @Override
    public TokenInfoServiceResponse reissue(Optional<String> refreshToken) {
        String token = refreshToken.orElseThrow(() -> new UnAuthorizedException("리프레쉬 토큰 이상"));

        User user = refreshTokenOutputPort.deleteByRefreshToken(token)
            .orElseThrow(() -> new UnAuthorizedException("해당 리프레쉬 토큰은 사용할 수 없음"));

        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(user), user);
    }

    @Override
    public AvailableContactServiceResponse isAvailableContact(String contact) {
        boolean isAvailable = true;
        String reason = "사용가능한 전화번호 입니다.";

        if (!Pattern.matches("^01(?:0|1|[6-9])\\d{8}", contact)) {
            isAvailable = false;
            reason = "전화 번호 형식이 아닙니다.";
        } else if (isDuplicateContact(contact)) {
            isAvailable = false;
            reason = "이미 사용 중인 전화번호 입니다.";
        }


        return AvailableContactServiceResponse.builder()
            .contact(contact)
            .available(isAvailable)
            .reason(reason)
            .build();
    }

    @Transactional
    @Override
    public List<ConnectAssetInfoResponse> connectAssets(final ConnectAssetsServiceRequest request) {
        NormalUser user = NormalUser.from(readUserOutputPort.getUserByUserId(UserId.of(request.getUserId())));
        if (isDuplicateContact(request.getContact()))
            throw new BadRequestException("해당 전화번호는 이미 사용 중 입니다.");

        user.setContact(request.getContact());
        user.setBackSocialId(request.getBackSocialId());
        user.setSocialName(request.getSocialName());

        user = saveNormalUserOutputPort.save(user);

        return toConnectAssetInfoListFrom(getAssetsOutputPort.getAssets(user));
    }

    @Transactional
    @Override
    public void setPublicOption(final SetPublicOptionServiceRequest request) {
        NormalUser user = readNormalUserOutputPort.findNormalUserByUserId(UserId.of(request.getUserId()))
            .orElseThrow(() -> new NotFoundException("해당 유저의 세부 정보가 존재하지 않습니다."));

        if (user.getBackSocialId() == null) throw new BadRequestException("자산 연결부터 하세요");

        user.setPublicAmount(request.isPublicAmount());
        user.setPublicPercent(request.isPublicPercent());

        saveNormalUserOutputPort.save(user);
    }

    @Transactional
    @Override
    public void setTendency(final SetTendencyServiceRequest request) {
        NormalUser user = readNormalUserOutputPort.findNormalUserByUserId(UserId.of(request.getUserId()))
            .orElseThrow(() -> new NotFoundException("해당 유저의 세부 정보가 존재하지 않습니다."));

        if (user.getBackSocialId() == null) throw new BadRequestException("자산 연결부터 하세요");

        user.setAccountTendency(request.isAccount());
        user.setCardTendency(request.isCard());
        user.setLoanTendency(request.isLoan());
        user.setInvestTendency(request.isInvest());

        try {
            user.setPurpose(ServicePurpose.valueOf(request.getPurpose()));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("서비스 이용 목적이 올바르지 않습니다.");
        }

        saveNormalUserOutputPort.save(user);
    }

    @Override
    public UserDetailInfoResponse getUserDetailInfo(Long userId) {
        NormalUser user = readNormalUserOutputPort.findNormalUserByUserId(UserId.of(userId))
            .orElseThrow(() -> new NotFoundException("해당 유저의 세부 정보가 존재하지 않습니다."));

        return UserDetailInfoResponse.builder()
                .frontSocialId(user.getFrontSocialId())
                .backSocialId(user.getBackSocialId())
                .socialName(user.getSocialName())
                .contact(user.getContact())
            .build();
    }

    @Transactional
    @Override
    public TokenInfoServiceResponse updateProfile(final UpdateProfileServiceRequest request) {
        User user = readUserOutputPort.getUserByUserId(UserId.of(request.getUserId()));
        UserUpdateOutputRequest.UserUpdateOutputRequestBuilder builder = UserUpdateOutputRequest.builder();

        if (request.getJob() != null) {
            user.setJob(toJobFrom(request.getJob()));
            builder.job(toJobFrom(request.getJob()));
        }

        if (request.getNickname() != null) {
            user.setNickname(Nickname.of(request.getNickname()));
            builder.nickname(request.getNickname());
        }


        if (request.getProfileImage() != null) {
            if (!isDefaultProfileImage(user.getProfileImage().toString()))
                s3Service.delete(user.getProfileImage().toString());

            String imageUrl = uploadImage(request.getProfileImage(), user.getId());
            user.setProfileImage(
                ImageUrl.of(imageUrl));
            builder.profileImage(imageUrl);
        }

        sendUpdateUserOutputPort.sendUserOutput(builder.build());
        User saveUser = saveUserOutputPort.save(user);

        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(saveUser),saveUser);
    }

    @Override
    public UserServiceResponse findById(final Long id, final Long myId) {
        User user = readUserOutputPort.getUserByUserId(UserId.of(id));
        FollowInfoResponse followInfo = readFollowOutputPort
            .getFollowInfo(user.getId(), Optional.ofNullable(myId==null?null:UserId.of(myId)));

        return UserServiceResponse.builder()
            .id(user.getId().toLong())
            .nickname(user.getNickname().toString())
            .job(user.getRole().equals(UserRole.CORP)?null:user.getJob().toKorean())
            .profileImage(user.getProfileImage().toString())
            .role(user.getRole())
            .tofinId(user.getToFinId().toString())
            .followers(followInfo.getNumOfFollowers())
            .followings(followInfo.getNumOfFollowings())
            .follow(followInfo.isFollow())
            .ageRange(user.getRole().equals(UserRole.CORP)?null:user.getBirth().getAgeRange())
            .build();
    }

    @Override
    public GetUserPagingResponse searchUserByNickname(final SearchUserServiceRequest request) {
        SearchUserPagingOutputResponse response = readUserOutputPort.searchByNickname(SearchUserOutputRequest.builder()
                .limit(request.getLimit())
                .lastIndex(request.getLastIndex())
                .nickname(request.getNickname())
                .build());

        return GetUserPagingResponse.builder()
            .users(response.getUsers().stream().map(user -> GetUserSummaryResponse.builder()
                .role(user.getRole())
                .userId(user.getId().toLong())
                .tofinId(user.getToFinId().toString())
                .profileImage(user.getProfileImage().toString())
                .nickname(user.getNickname().toString())
                .build()).toList())
            .totalCount(response.getTotalCount())
            .isLast(response.isLast())
            .lastIndex(response.getLastIndex())
            .build();
    }

    private String uploadImage(MultipartFile file, UserId id) {
        try {
            return s3Service.upload(file,String.format("users/profile/%d",id.toLong()));
        } catch (IOException e) {
            throw new BadRequestException("이미지 업로드에 실패하였습니다.");
        }
    }

    private boolean isDuplicateTofinId(final TofinId tofinId) {
        return readUserOutputPort.isExistsByTofinId(tofinId);
    }

    private TokenInfo generateTokenAndSaveRefresh(User user) {
        TokenInfo tokenInfo = jwtProvider.generateToken(TokenableUser.builder()
            .id(user.getId().toLong())
            .userRole(user.getRole())
            .job(user.getJob())
            .profileImage(user.getProfileImage().toString())
            .nickname(user.getNickname().toString())
            .birth(user.getBirth().toLocalDate())
            .build());

        refreshTokenOutputPort.saveOnlyOneUser(user, tokenInfo.getRefreshToken());

        return tokenInfo;
    }

    private Job toJobFrom(String job) {
        try {
            return job==null?null:Job.of(job);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(String.format("%s(은)는 올바르지 않은 직업입니다.", job));
        }
    }

    private boolean isDefaultProfileImage(final String profileImage) {
        return getDefaultProfileImages().contains(profileImage);
    }

    private List<String> getDefaultProfileImages() {
        return List.of("https://tofin-bucket.s3.ap-northeast-2.amazonaws.com/users/profile/default/user-icon1.svg",
            "https://tofin-bucket.s3.ap-northeast-2.amazonaws.com/users/profile/default/user-icon2.svg",
            "https://tofin-bucket.s3.ap-northeast-2.amazonaws.com/users/profile/default/user-icon3.svg",
            "https://tofin-bucket.s3.ap-northeast-2.amazonaws.com/users/profile/default/user-icon4.svg");
    }

    private TokenInfoServiceResponse toTokenInfoServiceResponse(final TokenInfo tokenInfo, User user) {
        return TokenInfoServiceResponse.builder()
            .id(user.getId().toLong())
            .accessToken(tokenInfo.getAccessToken())
            .refreshToken(tokenInfo.getRefreshToken())
            .grantType(tokenInfo.getGrantType())
            .build();
    }

    private boolean isDuplicateContact(String contact) {
        return readNormalUserOutputPort.existsByContact(contact);
    }

    private List<ConnectAssetInfoResponse> toConnectAssetInfoListFrom(AssetInfoResponse assetInfo) {
        List<ConnectAssetInfoResponse> connectAssetInfos = new ArrayList<>();

        for(AccountResponse account: assetInfo.getAccounts()) {
            connectAssetInfos.add(ConnectAssetInfoResponse.builder()
                    .cash(account.getCash())
                    .image(account.getLogo())
                    .productType(account.getAccountType())
                    .number(account.getAccountNumber())
                    .name(account.getName())
                .build());
        }

        for(CardResponse card: assetInfo.getCards()) {
            connectAssetInfos.add(ConnectAssetInfoResponse.builder()
                    .number(card.getCardNumber())
                    .name(card.getName())
                    .productType("CARD")
                    .image(card.getImage())
                .build());
        }

        return connectAssetInfos;
    }
}
