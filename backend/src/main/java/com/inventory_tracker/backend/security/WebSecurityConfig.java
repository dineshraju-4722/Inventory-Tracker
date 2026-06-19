package com.inventory_tracker.backend.security;

import com.inventory_tracker.backend.model.AppRole;
import com.inventory_tracker.backend.model.Role;
import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.repositories.RoleRepository;
import com.inventory_tracker.backend.repositories.UserRepository;
import com.inventory_tracker.backend.security.jwt.AuthEntryPointJwt;
import com.inventory_tracker.backend.security.jwt.AuthTokenFilter;
import com.inventory_tracker.backend.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticaionJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setU
       return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authconfig){
        return authconfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.csrf(csrf->csrf.disable())
                .exceptionHandling( exception -> exception.authenticationEntryPoint(unauthorizedHandler) )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/user/**").hasRole("USER")
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticaionJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository,PasswordEncoder passwordEncoder){
        return args -> {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseGet(() -> {
                        Role newUserRole = new Role(AppRole.ROLE_USER);
                        return roleRepository.save(newUserRole);
                    });
            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                    .orElseGet( () -> {
                        Role newAdminRole = new Role(AppRole.ROLE_ADMIN);
                        return roleRepository.save(newAdminRole);
                    });



//            Set<Role> userRoles = Set.of(userRole);
//            Set<Role> sellerRoles = Set.of(sellerRole);
//            Set<Role> adminRoles = Set.of(userRole,adminRole);
//
//            if(!userRepository.existsByUserName("user")){
//                User user = new User("user", "user@gmail.com", passwordEncoder.encode("user"));
//                userRepository.save(user);
//            }
//            if(!userRepository.existsByUserName("seller")){
//                User user = new User("seller", "seller@gmail.com", passwordEncoder.encode("seller"));
//                userRepository.save(user);
//            }
//            if(!userRepository.existsByUserName("admin")){
//                User user = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin"));
//                userRepository.save(user);
//            }
//
//            userRepository.findByUserName("user").ifPresent(user -> {
//                user.setRoles(userRoles);
//                userRepository.save(user);
//            });
//            userRepository.findByUserName("admin").ifPresent(admin -> {
//                admin.setRoles(adminRoles);
//                userRepository.save(admin);
//            });
//            userRepository.findByUserName("seller").ifPresent(seller -> {
//                seller.setRoles(sellerRoles);
//                userRepository.save(seller);
//            });

        };
    }
}
