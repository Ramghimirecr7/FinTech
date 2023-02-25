package com.peerlender.lendingengine.lendingengine.domain.repository;

import com.peerlender.lendingengine.lendingengine.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
