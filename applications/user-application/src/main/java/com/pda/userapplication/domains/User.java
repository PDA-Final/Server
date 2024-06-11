package com.pda.userapplication.domains;

import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import com.pda.userapplication.domains.vo.Birth;
import com.pda.userapplication.domains.vo.ImageUrl;
import com.pda.userapplication.domains.vo.Nickname;
import com.pda.userapplication.domains.vo.TofinId;
import com.pda.userapplication.domains.vo.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private UserId id;
    private TofinId toFinId;
    private String userInfo; // hashed
    private Nickname nickname;
    private ImageUrl profileImage;
    private LocalDateTime createdAt;
    private UserRole role;
    private Job job;
    private Birth birth;
}
