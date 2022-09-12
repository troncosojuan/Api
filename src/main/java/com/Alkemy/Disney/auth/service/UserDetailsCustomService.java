package com.Alkemy.Disney.auth.service;

import com.Alkemy.Disney.auth.dto.UserDTO;
import com.Alkemy.Disney.auth.entity.UserEntity;
import com.Alkemy.Disney.auth.repository.UserRepository;
import com.Alkemy.Disney.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailServiceImpl emailService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("username or password not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(@Valid UserDTO userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity = this.userRepository.save(userEntity);
        if (userEntity != null) {
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }
}
