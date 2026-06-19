package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.model.AppRole;
import com.inventory_tracker.backend.model.Role;
import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.repositories.RoleRepository;
import com.inventory_tracker.backend.repositories.UserRepository;
import com.inventory_tracker.backend.security.jwt.JwtUtils;
import com.inventory_tracker.backend.security.request.LoginRequest;
import com.inventory_tracker.backend.security.request.SignupRequest;
import com.inventory_tracker.backend.security.response.MessageResponse;
import com.inventory_tracker.backend.security.response.UserInfoResponse;
import com.inventory_tracker.backend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        }
        catch(AuthenticationException exception){
            Map<String,Object> map = new HashMap<>();
            map.put("message","Bad Credintials");
            map.put("status",false);
            return new ResponseEntity<Object>(
                    map, HttpStatus.NOT_FOUND
            );
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
//        assert principal != null;
//        String jwtToken = jwtUtils.generateTokenFromUsername(principal);

        ResponseCookie cookie = jwtUtils.generateCookie(principal);
        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        UserInfoResponse loginResponse = new UserInfoResponse(principal.getId(), principal.getUsername(), roles);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(loginResponse);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        if(userRepository.existsByUserName(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Username already taken")
            );
        }
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Email already taken")
            );
        }
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
                );
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if(strRoles==null){
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseThrow(
                    ()-> new RuntimeException("Error: Role not found")
            );
            roles.add(userRole);
        }
        else{
            strRoles.forEach( role -> {
                switch(role){
                    case "admin" :
                        Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error : Role does not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error : Role does not found"));
                        roles.add(userRole);
                        break;
                }
            } );
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok
                (new MessageResponse("User Registered Successfully"));
    }

    @GetMapping("/username")
    public String username(Authentication authentication){
        return authentication!=null?authentication.getName():null;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(Authentication authentication){
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = principal.getAuthorities().stream()
                .map( auth -> auth.getAuthority())
                .collect(Collectors.toList());
        UserInfoResponse loginResponse = new UserInfoResponse(
                principal.getId(),
                principal.getUsername(),
                roles
        );
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOutUser(){
        ResponseCookie cookie = jwtUtils.cleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body(new MessageResponse("You have been signout successfully"));
    }
}
