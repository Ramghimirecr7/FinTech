package com.lending.securityapp.application;

import com.lending.securityapp.repository.UserRepository;
import com.lending.securityapp.user.DTO.UserDTO;
import com.lending.securityapp.user.exception.UserNotFoundException;
import com.lending.securityapp.user.model.User;
import com.lending.securityapp.user.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @PostMapping("/validate")
    public String validateTokenAndGetUsername(@RequestHeader("Authorization") String token){
        return userRepository.findById(token).orElseThrow( UserNotFoundException::new).getUsername();

    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO){
        User user = new User(userDTO.getUsername(),userDTO.getPassword());
        userRepository.save(user);
        notificationService.sendMessage(userDTO);
    }

}
