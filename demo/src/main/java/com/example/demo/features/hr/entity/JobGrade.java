package com.example.demo.features.hr.entity;

import com.example.demo.common.audit.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class JobGrade extends BaseEntity { // 직급(예: 사원, 대리, 과장 등)

    private String code; // 직급 코드 (예: "S1", "M2")
    private String name; // 직급명 (예: "대리", "과장")
    private Integer level; // 직급 레벨(권한/정렬 판단용, 숫자 클수록 상위로 사용 가능)
}
