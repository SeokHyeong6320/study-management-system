package com.zerobase.fastlms.aahomework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginHistoryDto {

    private String userId;
    private LocalDateTime loginAt;
    private String connectionIp;
    private String connectionUserAgent;

    public static LoginHistoryDto fromEntity(LoginHistory history) {
        return LoginHistoryDto.builder()
                .userId(history.getMember().getUserId())
                .loginAt(history.getLoginAt())
                .connectionIp(history.getConnectionIp())
                .connectionUserAgent(history.getConnectionUserAgent())
                .build();
    }

}