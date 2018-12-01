package com.itheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("123456");
        System.out.println(password);
        boolean b = encoder.matches("123456", "$2a$10$AI3xErmz.nBjSiiPHF52Deh52N/2d.SGa.xpU.1sKZnNOQm2QOtZ2");
        System.out.println(b);

    }
}
