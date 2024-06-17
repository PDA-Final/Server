package com.pda.userapplication.domains;

import com.pda.tofinenums.user.ServicePurpose;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.format.DateTimeFormatter;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class NormalUser extends User {
    private Long detailId;
    private String backSocialId;
    private boolean publicAmount;
    private boolean publicPercent;
    private String socialName;
    private String contact;
    private boolean accountTendency;
    private boolean cardTendency;
    private boolean loanTendency;
    private boolean investTendency;
    private ServicePurpose purpose;

    public boolean isPublicAsset() {
        return publicAmount || publicPercent;
    }

    public String getSocialId() {
        return getFrontSocialId() + "-" + this.backSocialId;
    }

    public String getFrontSocialId() {
        return getBirth().toLocalDate()
            .format(DateTimeFormatter.ofPattern("yyMMdd"));
    }

    public static NormalUser from(User user) {
        return NormalUser.builder()
            .id(user.getId())
            .role(user.getRole())
            .birth(user.getBirth())
            .job(user.getJob())
            .userInfo(user.getUserInfo())
            .nickname(user.getNickname())
            .profileImage(user.getProfileImage())
            .toFinId(user.getToFinId())
            .build();
    }
}
