package com.pda.userapplication.outadapters.jpa.mapper;

import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.Birth;
import com.pda.userapplication.domains.vo.ImageUrl;
import com.pda.userapplication.domains.vo.Nickname;
import com.pda.userapplication.domains.vo.TofinId;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.outadapters.jpa.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public UserEntity toUserEntity(final User user) {
        if (user == null)
            return null;

        UserEntity.UserEntityBuilder builder = UserEntity.builder();

        builder
            .id(user.getId()!=null?user.getId().toLong():null)
            .tofinId(user.getToFinId().toString())
            .userInfo(user.getUserInfo())
            .profileImage(user.getProfileImage().toString())
            .birth(user.getBirth().toLocalDate())
            .createdAt(user.getCreatedAt())
            .nickname(user.getNickname().toString())
            .job(user.getJob())
            .role(user.getRole());

        return builder.build();
    }

    public User toUser(final UserEntity user) {
        if (user == null)
            return null;

        User.UserBuilder<?, ?> builder = User.builder();

        builder
            .id(UserId.of(user.getId()))
            .toFinId(TofinId.of(user.getTofinId()))
            .userInfo(user.getUserInfo())
            .nickname(Nickname.of(user.getNickname()))
            .job(user.getJob())
            .birth(Birth.of(user.getBirth()))
            .profileImage(ImageUrl.of(user.getProfileImage()))
            .role(user.getRole())
            .createdAt(user.getCreatedAt());

        return builder.build();
    }
}
