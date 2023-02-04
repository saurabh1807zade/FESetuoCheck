package com.hackathon.javatemplate.controller.sampleEntity;

import com.hackathon.javatemplate.auth.repository.CustomResponseWithPage;
import com.hackathon.javatemplate.customResponse.CustomResponse;
import com.hackathon.javatemplate.service.SampleEntityService;
import com.hackathon.javatemplate.vo.requestVo.SampleEntityRequestVo;
import com.hackathon.javatemplate.vo.requestVo.UpdateSampleEntityRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.hackathon.javatemplate.auth.constants.ApiConstants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(ROOT_URL_AUTH + ROOT_URL_SAMPLE)
public class SampleEntityController {

    @Autowired
    SampleEntityService sampleEntityService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CustomResponse<?> addSample(@RequestBody @Valid SampleEntityRequestVo sampleEntityRequestVo) {
        return sampleEntityService.addSample(sampleEntityRequestVo);
    }

    @PutMapping
    @ResponseStatus(OK)
    public CustomResponse<?> updateSample(@RequestBody @Valid UpdateSampleEntityRequestVo updateSampleEntityRequestVo) {
        return sampleEntityService.updateSample(updateSampleEntityRequestVo);
    }

    @GetMapping(METHOD_BY_ID)
    @ResponseStatus(OK)
    public CustomResponse<?> getSampleEntityById(@PathVariable("id") UUID id) {
        return sampleEntityService.getSampleEntityById(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public CustomResponseWithPage<?> getAllSampleEntity(@RequestParam(required = false) String search,
                                                        @RequestParam(required = false) String firstName,
                                                        @RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer pageSize) {
        return sampleEntityService.getAllSampleEntity(page, pageSize, firstName, search);
    }
}
