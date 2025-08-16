package com.example.demo.features.leave.request.entity;

public enum RequestStatus { // 휴가 신청 전체의 상태
    DRAFT,                         // 임시저장(제출 전)
    SUBMITTED,                     // 제출됨(승인 대기 시작)
    IN_REVIEW,                     // 결재 진행 중(중간 단계)
    APPROVED,                      // 최종 승인 완료
    REJECTED,                      // 반려됨(어느 단계에서든)
    CANCELED                       // 신청자 취소(또는 관리자 취소)
}
