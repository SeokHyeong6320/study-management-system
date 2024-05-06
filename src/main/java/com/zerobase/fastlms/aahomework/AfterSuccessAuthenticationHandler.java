package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AfterSuccessAuthenticationHandler
                                extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginHistoryService loginHistoryService;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {


        Object principal = authentication.getPrincipal();

        UserDetails userDetails = (UserDetails) principal;

        String userId = userDetails.getUsername();
        String userAgent = request.getHeader("User-Agent");
        String clientIp = request.getRemoteAddr();

        Member findMember = memberRepository
                .findById(userId).orElseThrow(() -> new RuntimeException
                                ("couldn't find member. userId->" + userId));

        LoginHistory loginHistory = LoginHistory.builder()
                .member(findMember)
                .connectionIp(clientIp)
                .connectionUserAgent(userAgent)
                .build();

        loginHistoryService.saveLoginHistory(loginHistory);

    }
}
