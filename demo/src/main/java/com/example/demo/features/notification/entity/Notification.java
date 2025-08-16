package com.example.demo.features.notification.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.user.entity.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "notification")
@Getter @Setter
public class Notification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount recipient;    // 이 코드는 수신자(사원 계정)

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel; // 이 코드는 발송 채널(EMAIL/SMS/…)

    @Column(nullable = false)
    private String title;             // 이 코드는 알림 제목

    @Lob
    private String body;              // 이 코드는 알림 본문(메시지 내용)

    private String linkUrl;           // 이 코드는 상세 화면 이동 링크(선택)

    @Enumerated(EnumType.STRING)
    private NotificationStatus status = NotificationStatus.PENDING; // 이 코드는 현재 상태

    private Instant scheduledAt;      // 이 코드는 예약 발송 시각(선택)

    private Instant sentAt;           // 이 코드는 실제 발송 시각

    private Instant readAt;           // 이 코드는 수신자가 읽은 시각(WEB 등)

    private String errorMessage;      // 이 코드는 실패 시 에러 메시지(트러블슈팅용)
}

