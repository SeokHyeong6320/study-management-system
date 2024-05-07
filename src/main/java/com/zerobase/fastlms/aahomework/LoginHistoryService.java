package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.member.entity.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginHistoryService {

    LoginHistoryDto saveLoginHistory(LoginHistory loginHistory);

    List<LoginHistoryDto> findAllLoginHistory(String userId);

}
