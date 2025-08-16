package com.example.demo.features.holiday.entity;

import com.example.demo.common.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "holiday", uniqueConstraints = @UniqueConstraint(columnNames = "date"))
@Getter @Setter
public class Holiday extends BaseEntity {
    @Column(nullable = false)
    private LocalDate date;              // 휴일 날짜

    @Column(nullable = false)
    private String name;                 // 휴일 이름(예: 설날, 창립기념일)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HolidayType type = HolidayType.NATIONAL; // 종류: 법정/회사

    private boolean workingDayOverride;  // 예외적으로 근무일로 지정(드물게 필요시)
}


