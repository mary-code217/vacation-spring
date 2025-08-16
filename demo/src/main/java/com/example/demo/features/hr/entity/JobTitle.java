package com.example.demo.features.hr.entity;

import com.example.demo.common.audit.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class JobTitle extends BaseEntity { // 직책(예: 팀장, 파트장, PO 등)

    private String code; // 직책 코드 (예: "TL", "PO")

    private String name; // 직책명 (예: "팀장", "프로덕트오너")
}
