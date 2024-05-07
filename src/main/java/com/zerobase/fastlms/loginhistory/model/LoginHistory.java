package com.zerobase.fastlms.loginhistory.model;

import com.zerobase.fastlms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "login_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoginHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginhistory_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member member;

    @Column(name = "login_at")
    @CreatedDate
    private LocalDateTime loginAt;

    @Column(name = "connection_ip")
    private String connectionIp;

    @Column(name = "connection_userAgent")
    private String connectionUserAgent;
}
