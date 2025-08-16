package com.example.demo.features.approval.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"approver_id", "startDate", "endDate"}))
@Getter @Setter
public class ApprovalDelegation extends BaseEntity {      // 결재권자의 부재 기간에 대한 위임 규칙

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount approver;                           // 이 코드는 원래 결재권자

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount delegate;                           // 이 코드는 위임 받을 사람

    private LocalDate startDate;                            // 이 코드는 위임 시작일

    private LocalDate endDate;                              // 이 코드는 위임 종료일

    private boolean active;                                 // 이 코드는 위임 규칙 활성 여부
}

