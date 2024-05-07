package com.zerobase.fastlms.loginhistory.repository;

import com.zerobase.fastlms.loginhistory.model.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

    List<LoginHistory> findAllByMemberUserIdOrderByLoginAtDesc(String userId);

    LoginHistory findFirstByMemberUserIdOrderByLoginAtAsc(String userId);
}
