package com.zerobase.fastlms.loginhistory.dto;

import com.zerobase.fastlms.loginhistory.model.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
