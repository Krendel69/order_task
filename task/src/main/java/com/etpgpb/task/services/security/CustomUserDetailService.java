package com.etpgpb.task.services.security;

import com.etpgpb.task.model.User;
import com.etpgpb.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {


    UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthorities(user)) {
        };
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName().toString()));
    }
}
