package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.Money;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BalanceService {

    private final UserRepository userRepository;

    @Autowired
    public BalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional

    public void topUpBalance(final Money money, String authToken){
        User user = userRepository.findById(authToken)
                .orElseThrow(()-> new UserNotFoundException(authToken));
        user.topUp(money);
    }
    @Transactional
    public void withdrawBalance(final Money money, String authToken){
        User user = userRepository.findById(authToken)
                .orElseThrow(()-> new UserNotFoundException(authToken));
        user.withdraw(money);
    }
}
