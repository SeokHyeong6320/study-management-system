package com.zerobase.fastlms.aahomework;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

    List<LoginHistory> findAllByMemberUserIdOrderByLoginAtDesc(String userId);
}
