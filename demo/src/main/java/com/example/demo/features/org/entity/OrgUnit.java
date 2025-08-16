package com.example.demo.features.org.entity;

import com.example.demo.common.audit.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrgUnit extends BaseEntity { // 조직(부서) 엔티티

    private String name; // 조직명(예 : 개발팀, 기획팀 ...)

    @ManyToOne(fetch = FetchType.LAZY) // 상위 조직과의 다대일 연관관계
    private OrgUnit parent; // 상위 조직(없으면 최상위)

    private Integer sortOrder; // 같은 레벨에서의 정렬 순서 (숫자가 낮을수록 먼저)

}
