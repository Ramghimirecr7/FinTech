package com.peerlender.lendingengine.lendingengine.domain.repository;

import com.peerlender.lendingengine.lendingengine.domain.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository extends JpaRepository<LoanApplication, Long> {


}
