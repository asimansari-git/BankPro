package com.screenprog.application.repo;

import com.screenprog.application.model.Account;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT accountNumber, balance, status, openDate FROM Account")
    List<Tuple> findAccountDetailsForFrontend();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    Optional<Account> findByAccountNumberWithLock(@Param("accountNumber") Long accountNumber);

    boolean existsByAccountNumberAndCustomer_CustomerID(Long accountNumber, Long customerId);
}
