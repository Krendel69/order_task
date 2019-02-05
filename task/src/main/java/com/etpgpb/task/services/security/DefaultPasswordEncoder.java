package com.etpgpb.task.services.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DefaultPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return (String) rawPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            return encodedPassword.contentEquals(rawPassword);
        } else {
            return false;
        }
    }
}
