package com.example.demo.features.leave.type.entity;

import com.example.demo.common.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leave_type")
@Getter @Setter
public class LeaveType extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String code;                     // 유형 코드: ANNUAL, SICK 등

    @Column(nullable = false)
    private String name;                     // 유형 이름: 연차, 병가 등

    @Enumerated(EnumType.STRING)
    private AccrualPolicy accrualPolicy;     // 부여 정책: FIXED_ANNUAL 등

    @Column(nullable = false)
    private Integer defaultDaysPerYear = 0;  // 기본 연간 부여일수

    // ---------- SFR-103 연계: 차감 규칙 ----------
    @Column(nullable = false)
    private Integer minutesPerDay = 480;     // 1일=480분(회사 기준)
    @Column(nullable = false)
    private Boolean allowHalfDay = true;     // 반차 허용(0.5일=240분)
    @Column(nullable = false)
    private Integer minUnitMinutes = 120;    // 최소 2시간
    @Column(nullable = false)
    private Integer unitStepMinutes = 120;   // 2시간 단위 스텝
    @Column(nullable = false)
    private Boolean allowTimeUnit = true;    // 시간 단위 신청 허용 여부

    // ---------- SFR-102: 차감 여부/증빙 ----------
    @Column(nullable = false)
    private Boolean deductFromBalance = true; // 잔여에서 차감? (경조/특별휴가 등은 false 가능)
    private Integer requireDocWhenDaysGte;    // n일 이상이면 증빙 필수 (null이면 미사용)
    private Boolean requireDocAlways = false; // 항상 증빙 필요 여부(유형 정책)

    // 선택: UI/비즈니스 설명
    private String description;

    @Version
    private Long version;
}

