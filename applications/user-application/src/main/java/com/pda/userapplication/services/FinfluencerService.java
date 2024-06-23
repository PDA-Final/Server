package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinenums.user.UserRole;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.FinfluencerUseCase;
import com.pda.userapplication.services.out.CreditOutputPort;
import com.pda.userapplication.services.out.ReadFollowOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.SaveUserOutputPort;
import com.pda.userapplication.services.out.SendUpdateUserOutputPort;
import com.pda.userapplication.services.out.dto.req.UserUpdateOutputRequest;
import com.pda.userapplication.services.out.dto.res.FollowInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FinfluencerService implements FinfluencerUseCase {
    private final SaveUserOutputPort saveUserOutputPort;
    private final ReadUserOutputPort readUserOutputPort;
    private final ReadFollowOutputPort readFollowOutputPort;
    private final SendUpdateUserOutputPort sendUpdateUserOutputPort;
    private final CreditOutputPort creditOutputPort;

    @Transactional
    @Override
    public void becomeFinfluencer(Long userId, String token) {
        // 조건 300, 500크레딧 차감
        User user = readUserOutputPort.getUserByUserId(UserId.of(userId));

        FollowInfoResponse followInfo = readFollowOutputPort.getFollowInfo(user.getId(),Optional.empty());

        log.info(String.valueOf(followInfo.getNumOfFollowers()), "fuck followers");
        if (followInfo.getNumOfFollowers() < 300) throw new BadRequestException("핀플루언서로 등업하기 위해서 300명 이상의 팔로워들이 필요합니다.");
        if (!creditOutputPort.consumeCredit(500L, token))
            throw new BadRequestException("크레딧 차감 실패");

        user.setRole(UserRole.FINFLUENCER);
        sendUpdateUserOutputPort.sendUserOutput(UserUpdateOutputRequest.builder()
                .userId(userId)
                .role(UserRole.FINFLUENCER)
            .build());
        saveUserOutputPort.save(user);
    }
}
