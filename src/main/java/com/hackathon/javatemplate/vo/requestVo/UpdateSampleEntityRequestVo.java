package com.hackathon.javatemplate.vo.requestVo;

import com.hackathon.javatemplate.validation.annotations.UpdateSampleEntityRequestValidator;
import lombok.Data;

import java.util.UUID;

@Data
@UpdateSampleEntityRequestValidator
public class UpdateSampleEntityRequestVo {

    private UUID id;

    private String firstName;

    private String lastName;

    private String userName;

    private String phoneNumber;

    private String email;
}
