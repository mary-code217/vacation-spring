package com.example.demo.features.attachment.entity;

import com.example.demo.common.audit.BaseEntity;
import com.example.demo.features.leave.request.entity.LeaveRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attachment")
@Getter @Setter
public class Attachment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaveRequest leaveRequest; // 이 코드는 어떤 휴가 신청의 첨부인지(증빙 연결)

    @Column(nullable = false)
    private String originalName;       // 이 코드는 원본 파일명

    @Column(nullable = false)
    private String storedPath;         // 이 코드는 저장 경로(S3 키/로컬 경로)

    private String contentType;        // 이 코드는 MIME 타입(예: application/pdf)

    private long size;                 // 이 코드는 파일 크기(byte)

    private String checksum;           // 이 코드는 무결성 검사용 해시(SHA-256 등, 선택)
}

