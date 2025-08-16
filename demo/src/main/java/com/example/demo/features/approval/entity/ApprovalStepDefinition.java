package com.example.demo.features.approval.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.hr.entity.JobGrade;
import com.example.demo.features.hr.entity.JobTitle;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ApprovalStepDefinition extends BaseEntity {  // 템플릿 상 결재 단계의 ‘설계도’

    @ManyToOne(fetch = FetchType.LAZY)
    private ApprovalTemplate template;                      // 이 코드는 소속 템플릿

    private int orderIndex;                                 // 이 코드는 단계 순서(1,2,3...)

    @Enumerated(EnumType.STRING)
    private ApproverType approverType;                      // 이 코드는 결재자 선정 방식(BY_USER/ROLE/…)

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount approverUser;                       // 이 코드는 BY_USER일 때의 지정 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    private JobTitle approverTitle;                         // 이 코드는 BY_JOB_TITLE일 때의 직책

    @ManyToOne(fetch = FetchType.LAZY)
    private JobGrade approverGrade;                         // 이 코드는 BY_JOB_GRADE일 때의 직급

    private boolean finalStep;                              // 이 코드는 마지막 단계 여부(완료 판단용)
}

