package com.pda.tofinenums.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Job {
    CIVIL_SERVANT(26, "공무원"),
    MANAGER(25, "경영자"),
    MENTOR(24, "교직자"),
    SOLDIER(23, "군인"),
    BANK(22, "은행"),
    STOCK(21, "증권"),
    COMMITMENT(20, "투신"),
    INSURANCE(19, "보험"),
    OTHER_FINANCIAL(18, "기타금융"),
    ENGINEER(17, "기술자"),
    NSIG_JOB(16, "농/수/임/광공업"),
    COLLEGE(15, "대학생"),
    MARKETING(14, "마케팅"),
    JOBLESS(13, "무직"),
    LAW(12, "법률"),
    SERVICE(11, "서비스업"),
    JOURNALIST(10, "언론인"),
    ARTIST(9,"예술"),
    DISTRIBUTION_INDUSTRY(8, "유통업"),
    MEDICAL(7, "의료인"),
    SELF_EMPLOYMENT(6, "자영업"),
    RELIGIOUS(5,"종교인"),
    IT(4, "IT"),
    SALESMAN(3, "판매원"),
    EMPLOYEE(2, "회사원"),
    STUDENT(1, "초/중/고등학생"),
    OTHER(0,"기타");

    private int code;
    private String korean;

    public static Job of(String korean) {
        for(Job job: Job.values()) {
            if (job.toKorean().equals(korean))
                return job;
        }

        throw new IllegalArgumentException(String.format("Can't find %s in Jobs", korean));
    }

    public static Job of(int code) {
        for(Job job: Job.values()) {
            if (job.toCode() == code)
                return job;
        }

        throw new IllegalArgumentException(String.format("Can't find %d in Jobs", code));
    }

    public int toCode() {
        return code;
    }

    public String toKorean() {
        return korean;
    }
}
