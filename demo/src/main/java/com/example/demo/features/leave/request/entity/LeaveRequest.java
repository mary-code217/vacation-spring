package com.example.demo.features.leave.request.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.approval.entity.ApprovalStep;
import com.example.demo.features.leave.type.entity.LeaveType;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class LeaveRequest extends BaseEntity {  // 휴가 신청의 루트 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount requester; // 이 코드는 신청자(사원)

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveType leaveType; // 이 코드는 신청한 휴가 유형(연차,병가 등)

    private LocalDate startDate; // 신청 시작일
    private LocalDate endDate; // 신청 종료일(단일 이면 start와 동일)

    private String reason; // 신청 사유

    private LocalDateTime submittedAt; // 제출 일시

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.DRAFT; // 신청 상태

    private boolean cancelRequested; // 취소 요청 여부
    private String cancelReason; // 취소 사유


    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaveRequestSegment> segments = new ArrayList<>(); // 이 코드는 날짜별/시간별 세그먼트 목록

    @OneToMany(mappedBy = "leaveRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<ApprovalStep> approvalSteps = new ArrayList<>();   // 이 코드는 실제 결재 단계 인스턴스들(진행상태)
}
