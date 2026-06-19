package com.inventory_tracker.backend.security.services;

import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUserName(username)
                       .orElseThrow(
                ()-> new UsernameNotFoundException("user not found with username : " + username )
                       );

        return UserDetailsImpl.build(user);
    }
}
