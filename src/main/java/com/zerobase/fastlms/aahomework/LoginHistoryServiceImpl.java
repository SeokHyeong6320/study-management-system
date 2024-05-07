package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService{

    private final LoginHistoryRepository loginHistoryRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public LoginHistoryDto saveLoginHistory(LoginHistory loginHistory) {

        LoginHistory savedHistory = loginHistoryRepository.save(loginHistory);
        loginHistory.getMember().getHistories().add(loginHistory);

        return LoginHistoryDto.fromEntity(savedHistory);
    }

    @Override
    public List<LoginHistoryDto> findUserLoginHistory(String userId) {
        Member findMember = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException
                        ("couldn't find member. userId->" + userId));

        return findMember.getHistories()
                .stream()
                .map(LoginHistoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    /*
    public LoginHistoryDto findLastLoginHistory(String userId) {
        Member findMember =
                memberRepository
                        .findById(userId).orElseThrow(() ->
                                new RuntimeException
                                        ("couldn't find member. userId->" + userId));

        List<LoginHistory> histories = findMember.getHistories();

        return LoginHistoryDto.fromEntity(histories.get(histories.size() - 1));
    }
    */
}
