package com.zerobase.fastlms.loginhistory.service;

import com.zerobase.fastlms.loginhistory.model.LoginHistory;
import com.zerobase.fastlms.loginhistory.dto.LoginHistoryDto;

import java.util.List;

public interface LoginHistoryService {

    LoginHistoryDto saveLoginHistory(LoginHistory loginHistory);

    List<LoginHistoryDto> findUserLoginHistory(String userId);

}
