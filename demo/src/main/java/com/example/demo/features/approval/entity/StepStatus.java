package com.example.demo.features.approval.entity;

public enum StepStatus {          // 실제 결재 ‘단계’의 상태
    PENDING,                        // 대기 중(담당자 승인 필요)
    APPROVED,                       // 해당 단계 승인됨
    REJECTED,                       // 해당 단계 반려됨
    DELEGATED                       // 대리자에게 위임됨(승인 주체 변경)
}

