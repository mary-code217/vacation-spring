package com.example.demo.features.user.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.hr.entity.JobGrade;
import com.example.demo.features.hr.entity.JobTitle;
import com.example.demo.features.org.entity.OrgUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "app_user") // 테이블명 지정(예약어 충돌 회피/가독성)
public class UserAccount extends BaseEntity { // 사용자(사원) 계정 엔티티

    @Column(unique = true, nullable = false) // 중복 금지 + null 금지
    private String username; // 로그인 ID (예: "honggd")

    private String password; // 비밀번호 해시(암호화된 문자열)

    private String name;  // 사원 이름 (예: "홍길동")
    private String email; // 이메일 (예: "hong@company.com")
    private String phone; // 연락처 (예: "010-1234-5678")

    private LocalDate hireDay; // 입사일
    private LocalDate retierDate; // 퇴사일(현직이면 null)

    @ManyToOne(fetch = FetchType.LAZY)  // 사용자 ↘
    private OrgUnit orgUnit; // 소속 조직(부서)

    @ManyToOne(fetch =  FetchType.LAZY)
    private JobGrade jobGrade; // 직급(대리/과장 등)

    @ManyToOne(fetch = FetchType.LAZY)
    private JobTitle jobTitle; // 직책(팀장/PO 등)

    @ElementCollection(fetch = FetchType.EAGER)  // 값 타입 컬렉션
    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장(USER/APPROVER/ADMIN)
    private Set<SystemRole> roles = new HashSet<>(); // 시스템 권한 집합

    private boolean active = true; // 계정 활성/비활성 (로그인/사용 가능 여부)
}
