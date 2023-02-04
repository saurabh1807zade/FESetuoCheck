package com.hackathon.javatemplate.vo.requestvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String userName; //Should be Alpha Numeric without @

    private String password;

    private String email;

    private String phone;

    private List<String> roles;

}
