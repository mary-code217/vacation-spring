package com.example.demo.features.auditlog.entity;

import com.example.demo.common.audit.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audit_log")
@Getter @Setter
public class AuditLog extends BaseEntity {

    @Column(nullable = false)
    private String actor;              // 이 코드는 작업 주체(예: username 또는 사용자ID 문자열)

    @Column(nullable = false)
    private String action;             // 이 코드는 수행된 액션(예: CREATE_REQUEST, APPROVE_STEP)

    @Column(nullable = false)
    private String entityType;         // 이 코드는 대상 엔티티 타입명(예: LeaveRequest)

    @Column(nullable = false)
    private Long entityId;             // 이 코드는 대상 엔티티 PK

    @Lob
    private String detailJson;         // 이 코드는 상세 내용(JSON; 전/후 값 등)

    private String ipAddress;          // 이 코드는 요청 출발 IP(선택)

    private String userAgent;          // 이 코드는 클라이언트 에이전트 정보(선택)
}

