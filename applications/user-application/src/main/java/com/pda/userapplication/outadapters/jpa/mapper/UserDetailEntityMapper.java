package com.pda.userapplication.outadapters.jpa.mapper;

import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.outadapters.jpa.entities.user.UserDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailEntityMapper {
    private final UserEntityMapper userMapper;

    public UserDetailEntity toUserDetailEntity(final NormalUser normalUser) {
        if (normalUser == null)
            return null;

        return UserDetailEntity.builder()
            .id(normalUser.getDetailId())
            .contact(normalUser.getContact())
            .backSocialId(normalUser.getBackSocialId())
            .socialName(normalUser.getSocialName())
            .publicOptions(toPublicOptions(normalUser))
            .purpose(normalUser.getPurpose())
            .tendency(toTendency(normalUser))
            .user(userMapper.toUserEntity(normalUser))
            .build();
    }

    private String toPublicOptions(NormalUser user) {
        String amountOption = user.isPublicAmount()?"1":"0";
        String percentOption = user.isPublicPercent()?"1":"0";

        return amountOption + percentOption;
    }

    private String toTendency(NormalUser user) {
        String account = user.isAccountTendency()?"1":"0";
        String card = user.isCardTendency()?"1":"0";
        String loan = user.isLoanTendency()?"1":"0";
        String invest = user.isInvestTendency()?"1":"0";

        return account + card + loan + invest;
    }

    public NormalUser toNormalUser(final UserDetailEntity userDetail) {
        if (userDetail == null) return null;

        NormalUser user = NormalUser.from(userMapper.toUser(userDetail.getUser()));
        user.setDetailId(userDetail.getId());
        user.setContact(userDetail.getContact());
        user.setSocialName(userDetail.getSocialName());
        user.setBackSocialId(userDetail.getBackSocialId());

        String[] options = userDetail.getPublicOptions().split("");
        user.setPublicAmount(options[0].equals("1"));
        user.setPublicPercent(options[1].equals("1"));

        String[] tendencies = userDetail.getTendency().split("");
        user.setAccountTendency(tendencies[0].equals("1"));
        user.setCardTendency(tendencies[1].equals("1"));
        user.setLoanTendency(tendencies[2].equals("1"));
        user.setInvestTendency(tendencies[3].equals("1"));

        user.setPurpose(userDetail.getPurpose());

        return user;
    }
}
