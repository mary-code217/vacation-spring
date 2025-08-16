package com.example.demo.features.leave.type.entity;

public enum AccrualPolicy { // 휴가를 어떤 방식으로 부여/적립할지 정책 구분
    TENURE_BASED, // 근속연수/입사개월수에 따라 누적(예: 월차)
    FIXED_ANNUAL, // 매년 고정 일수 일괄 부여(예: 연 15일)
    MONTHLY_PRORATED // 월 단위로 비례 부여(입·퇴사월 포함 비례)
}
