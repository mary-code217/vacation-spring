package com.example.demo.features.leave.balance.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.leave.type.entity.LeaveType;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "leave_balance",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "leaveType_id", "leave_year"}
        )
)
@Getter @Setter
public class LeaveBalance extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount user; // 해당사원 - "이 코드는 사원(계정)"

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveType leaveType; // 어떤 휴가 종류의 잔여인지

    @Column(name = "leave_year", nullable = false)
    private int year; // 기준연도

    private double earnedDays; // 부여/적립된 총 일수(해당 연도 기준)

    private double usedDays; // 사용한 일수 합계

    private double carriedOverDays; // 전년도에서 이월된 일수

    private double remainingDays; // 현재 잔여 일수(조회/정산 캐시)

    @Version
    private Long version; // 낙관적 락 버전(동시 차감 출동 방지)
}
