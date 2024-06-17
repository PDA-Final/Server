package com.pda.userapplication.outadapters.jpa.mapper;

import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.outadapters.jpa.UserDetailEntity;
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
            .backSocialId(normalUser.getSocialId())
            .socialName(normalUser.getSocialName())
            .publicOptions(toPublicOptions(normalUser))
            .user(userMapper.toUserEntity(normalUser))
            .build();
    }

    private String toPublicOptions(NormalUser user) {
        String amountOption = user.isPublicAmount()?"1":"0";
        String percentOption = user.isPublicPercent()?"1":"0";

        return amountOption + percentOption;
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

        return user;
    }
}
