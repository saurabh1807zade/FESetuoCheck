package com.hackathon.javatemplate.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegexValidator {

    public boolean notVerifiedRegex(String value, String pattern) {
        try {
            return !Pattern.compile(pattern).matcher(value).matches();
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }

}
