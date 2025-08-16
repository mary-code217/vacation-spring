package com.example.demo.features.leave.request.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"requester_id", "date"}))
@Getter @Setter
public class LeaveRequestSegment extends BaseEntity {      // 휴가를 ‘하루 단위’로 쪼갠 레코드

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveRequest leaveRequest;                      // 이 코드는 소속된 휴가 신청

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount requester;                          // 이 코드는 해당 세그먼트의 사원(신청자)

    private LocalDate date;                                 // 이 코드는 차감 대상 날짜(평일/휴일 정책 반영)

    @Column(nullable = false)
    private Integer minutes;                                // 이 코드는 해당 날짜에 차감하는 분(예: 480=종일, 240=반차, 120=2시간)

    private Double dayPortion;                              // 이 코드는 조회 캐시(분/분모 → 일수 환산, 예: 0.5) 선택 필드
}

