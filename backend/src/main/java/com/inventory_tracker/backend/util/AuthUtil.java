package com.inventory_tracker.backend.util;


import com.inventory_tracker.backend.exceptions.APIException;
import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    @Autowired
    private UserRepository userRepository;

    public String getLoggedInEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUserName(authentication.getName()).orElseThrow(
                () -> new APIException("No user Found with username: " + authentication.getName())
        );
        return user.getEmail();
    }

    public Long getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUserName(authentication.getName()).orElseThrow(
                () -> new APIException("No user Found with username: " + authentication.getName())
        );
        return user.getUserId();
    }


    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUserName(authentication.getName()).orElseThrow(
                () -> new APIException("No user Found with username: " + authentication.getName())
        );
    }
}
