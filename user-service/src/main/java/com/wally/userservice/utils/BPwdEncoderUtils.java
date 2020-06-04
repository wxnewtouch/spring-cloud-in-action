package com.wally.userservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BPwdEncoderUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String BCryptPassword(String passWord) {
        return encoder.encode(passWord);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
