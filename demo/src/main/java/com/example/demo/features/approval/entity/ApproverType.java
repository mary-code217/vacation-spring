package com.example.demo.features.approval.entity;

public enum ApproverType {        // 결재자 선택 기준(템플릿 정의용)
    BY_USER,                        // 특정 사용자 ID로 지정
    BY_ROLE,                        // 시스템 역할(APPROVER/ADMIN 등)로 지정
    BY_JOB_TITLE,                   // 직책 기준(예: 팀장)
    BY_JOB_GRADE                    // 직급 기준(예: 과장 이상)
}

