package com.zemnitskiy.chess.security;


import com.zemnitskiy.chess.entity.User;
import com.zemnitskiy.chess.entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    Logger log = LoggerFactory.getLogger(RegistrationController.class);
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user){
        log.info("createUser {}", user);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        User saved = userRepository.save(user);
        log.info("User {} saved", saved);
        return "redirect:/login";
    }
}
