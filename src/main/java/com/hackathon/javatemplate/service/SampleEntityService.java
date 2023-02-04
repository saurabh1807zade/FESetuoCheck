package com.hackathon.javatemplate.service;

import com.hackathon.javatemplate.auth.repository.CustomResponseWithPage;
import com.hackathon.javatemplate.customResponse.CustomResponse;
import com.hackathon.javatemplate.vo.requestVo.SampleEntityRequestVo;
import com.hackathon.javatemplate.vo.requestVo.UpdateSampleEntityRequestVo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface SampleEntityService {
    CustomResponse<?> addSample(SampleEntityRequestVo sampleEntityRequestVo);

    CustomResponse<?> updateSample(UpdateSampleEntityRequestVo updateSampleEntityRequestVo);

    CustomResponse<?> getSampleEntityById(UUID id);

    CustomResponseWithPage<?> getAllSampleEntity(Integer page, Integer pageSize, String firstName, String search);
}
