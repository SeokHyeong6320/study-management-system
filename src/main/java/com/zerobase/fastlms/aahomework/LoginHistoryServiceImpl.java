package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService{

    private final LoginHistoryRepository loginHistoryRepository;


    @Override
    public LoginHistoryDto saveLoginHistory(LoginHistory loginHistory) {

        LoginHistory savedHistory = loginHistoryRepository.save(loginHistory);

        return LoginHistoryDto.fromEntity(savedHistory);
    }

    @Override
    public List<LoginHistoryDto> findAllLoginHistory(String userId) {
        return Collections.emptyList();
    }

    @Override
    public LoginHistoryDto findLastLoginHistory(String userId) {
        return null;
    }
}
