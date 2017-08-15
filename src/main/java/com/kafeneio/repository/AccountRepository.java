package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.AccountModel;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
AccountModel findByUserId(Long userId);
}
