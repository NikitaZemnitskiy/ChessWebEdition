package com.zemnitskiy.chess.controller;

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
    public String addUser(String username, String password, String confirmedPassword){
        if(!password.equals(confirmedPassword)){
            return "redirect:/registration?passwordsAreDifferent";
        }
        if(password.length() < 3){
            return "redirect:/registration?shortPassword";
        }
        if(username.length() < 3){
            return "redirect:/registration?shortUsername";
        }
        if (userRepository.findByUsername(username) != null){
            return "redirect:/registration?userAlreadyExist";
        }

        User user = new User(username,password);
        log.info("createUser {}", user);
        user.setPassword(passwordEncoder.encode(password));
        User saved = userRepository.save(user);
        log.info("User {} saved", saved);
        return "redirect:/login";
    }
}
