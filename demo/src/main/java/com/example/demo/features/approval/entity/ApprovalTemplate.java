package com.example.demo.features.approval.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.hr.entity.JobGrade;
import com.example.demo.features.hr.entity.JobTitle;
import com.example.demo.features.org.entity.OrgUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ApprovalTemplate extends BaseEntity {     // 결재 라인을 미리 정의해 둔 템플릿

    private String name;                                 // 이 코드는 템플릿 이름(예: ‘연차 기본 결재선’)

    @ManyToOne(fetch = FetchType.LAZY)
    private OrgUnit targetOrg;                           // 이 코드는 적용 조직(없으면 전체)

    @ManyToOne(fetch = FetchType.LAZY)
    private JobTitle targetTitle;                        // 이 코드는 적용 직책(없으면 전체)

    @ManyToOne(fetch = FetchType.LAZY)
    private JobGrade targetGrade;                        // 이 코드는 적용 직급(없으면 전체)

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<ApprovalStepDefinition> steps = new ArrayList<>(); // 이 코드는 단계 정의 목록(순서 포함)
}

