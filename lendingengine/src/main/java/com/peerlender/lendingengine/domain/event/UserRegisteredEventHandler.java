package com.peerlender.lendingengine.domain.event;

import com.google.gson.Gson;
import com.peerlender.lendingengine.domain.model.User;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {

    private final UserRepository userRepository;
    private static final Gson gson = new Gson();
    private Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventHandler .class);

    @Autowired

    public UserRegisteredEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleUserRegistration(String userDetails){
        User user = gson.fromJson(userDetails, User.class);
        LOGGER.info("user {} registered", user.getUsername());
        userRepository.save(user);
    }
}
