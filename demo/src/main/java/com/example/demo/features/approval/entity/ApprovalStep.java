package com.example.demo.features.approval.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.leave.request.entity.LeaveRequest;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ApprovalStep extends BaseEntity {            // 신청이 생성될 때 템플릿에서 ‘복제’되는 실체 단계

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveRequest leaveRequest;                      // 이 코드는 소속 휴가 신청

    private int orderIndex;                                 // 이 코드는 단계 순서(템플릿과 동일 순서)

    @Enumerated(EnumType.STRING)
    private StepStatus status = StepStatus.PENDING;         // 이 코드는 단계 상태(PENDING/APPROVED/REJECTED…)

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount approver;                           // 이 코드는 실제 결재 담당자(템플릿 규칙으로 결정)

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount delegatedTo;                        // 이 코드는 위임받은 대리 결재자(있을 때만)

    private LocalDateTime decidedAt;                        // 이 코드는 승인/반려 결정 시각

    private String comment;                                 // 이 코드는 코멘트(사유/메모)
}

