package com.hackathon.javatemplate.service.impl;

import com.hackathon.javatemplate.auth.constants.ResponseSuccess;
import com.hackathon.javatemplate.auth.entities.User;
import com.hackathon.javatemplate.auth.repository.CustomResponseWithPage;
import com.hackathon.javatemplate.auth.repository.Paging;
import com.hackathon.javatemplate.auth.repository.UserRepository;
import com.hackathon.javatemplate.customResponse.CustomResponse;
import com.hackathon.javatemplate.entities.SampleEntity;
import com.hackathon.javatemplate.exception.CustomServiceValidationException;
import com.hackathon.javatemplate.mapper.SampleEntityMapper;
import com.hackathon.javatemplate.repository.SampleEntityRepository;
import com.hackathon.javatemplate.service.SampleEntityService;
import com.hackathon.javatemplate.vo.requestVo.SampleEntityRequestVo;
import com.hackathon.javatemplate.vo.requestVo.UpdateSampleEntityRequestVo;
import com.hackathon.javatemplate.vo.responseVo.SampleEntityResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.hackathon.javatemplate.auth.constants.ResponseErrors.SAMPLE_ENTITY_NOT_EXIST;
import static com.hackathon.javatemplate.auth.constants.ResponseSuccess.SAMPLE_ENTITY_CREATED;
import static com.hackathon.javatemplate.auth.constants.ServiceConstants.*;
import static org.springframework.http.HttpStatus.OK;

@Service
public class SampleEntityServiceImpl implements SampleEntityService {

    @Autowired
    SampleEntityRepository sampleEntityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SampleEntityMapper sampleEntityMapper;

    @Override
    public CustomResponse<?> addSample(SampleEntityRequestVo sampleEntityRequestVo) {

        User user = sampleEntityMapper.mapUserFromSampleEntity(sampleEntityRequestVo);
        SampleEntity sampleEntity = sampleEntityMapper.mapSampleEntityFromRequest(sampleEntityRequestVo);

        sampleEntity.setUserId(userRepository.save(user).getId());
        sampleEntityRepository.save(sampleEntity);

        Map<String, UUID> response = new HashMap<>();
        response.put(ID, sampleEntity.getUserId());
        return new CustomResponse<>(OK, response, SAMPLE_ENTITY_CREATED);
    }

    @Override
    public CustomResponse<?> updateSample(UpdateSampleEntityRequestVo updateSampleEntityRequestVo) {

        User dbUser = userRepository.findByIdAndIsDisable(updateSampleEntityRequestVo.getId(), false);
        if (dbUser == null) {
            throw new CustomServiceValidationException(SAMPLE_ENTITY_NOT_EXIST);
        }
        SampleEntity dbSampleEntity = sampleEntityRepository.findByUserIdAndIsDisable(updateSampleEntityRequestVo.getId(), false);

        if (dbSampleEntity == null) {
            throw new CustomServiceValidationException(SAMPLE_ENTITY_NOT_EXIST);
        }

        dbUser = sampleEntityMapper.mapUSerFromUpdateRequest(updateSampleEntityRequestVo, dbUser);
        dbSampleEntity = sampleEntityMapper.mapSampleEntityFromUpdateRequest(updateSampleEntityRequestVo, dbSampleEntity);

        userRepository.save(dbUser);
        sampleEntityRepository.save(dbSampleEntity);

        Map<String, UUID> response = new HashMap<>();
        response.put(ID, dbSampleEntity.getUserId());

        return new CustomResponse<>(OK, response, ResponseSuccess.SAMPLE_ENTITY_UPDATED);
    }

    @Override
    public CustomResponse<?> getSampleEntityById(UUID id) {

        SampleEntity dbSampleEntity = sampleEntityRepository.findByUserIdAndIsDisable(id, false);
        if (dbSampleEntity == null) {
            throw new CustomServiceValidationException(SAMPLE_ENTITY_NOT_EXIST);
        }
        SampleEntityResponseVo responseVo = sampleEntityMapper.mapResponseFromDbSampleEntity(dbSampleEntity);
        return new CustomResponse<>(OK, responseVo, ResponseSuccess.SAMPLE_ENTITY_FOUND);
    }

    @Override
    public CustomResponseWithPage<?> getAllSampleEntity(Integer page, Integer pageSize, String firstName, String search) {

        page = (page == null) ? DEFAULT_PAGE : page;
        pageSize = (pageSize == null) ? DEFAULT_PAGE_SIZE : pageSize;

        Paging currentPage;

        List<SampleEntity> dbResponse;
        List<SampleEntityResponseVo> responseVoList;
        Pageable defaultSortingAndPaging = PageRequest.of(page, pageSize);

        if (search != null && !search.isEmpty() && !search.isBlank()){

            search = search.toLowerCase().trim();

            dbResponse = sampleEntityRepository.getAllBySearch(search, defaultSortingAndPaging);
            responseVoList = sampleEntityMapper.mapResponseListFromSampleEntity(dbResponse);
            currentPage = new Paging(page, pageSize, sampleEntityRepository.getCountBySearch(search));
            return new CustomResponseWithPage<>(OK, responseVoList,currentPage, ResponseSuccess.SAMPLE_ENTITY_FOUND);
        }

        firstName = (firstName == null || firstName.isEmpty() || firstName.isBlank()) ? null : (firstName);

        dbResponse = sampleEntityRepository.getAllAndByFilter(firstName, defaultSortingAndPaging);
        responseVoList = sampleEntityMapper.mapResponseListFromSampleEntity(dbResponse);
        currentPage = new Paging(page, pageSize, sampleEntityRepository.getAllAndFilterCount(firstName));

        return new CustomResponseWithPage<>(OK, responseVoList, currentPage, ResponseSuccess.SAMPLE_ENTITY_FOUND);
    }
}
