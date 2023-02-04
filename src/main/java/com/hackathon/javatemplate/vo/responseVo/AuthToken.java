package com.hackathon.javatemplate.vo.responseVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private UUID id;

    private String token_type;

    private String access_token;

    private Long expires_in;

    private String refresh_token;

    private Long refresh_expires_in;

}