package com.hackathon.javatemplate.mapper;

import com.hackathon.javatemplate.auth.entities.User;
import com.hackathon.javatemplate.auth.mapper.UserMapper;
import com.hackathon.javatemplate.entities.SampleEntity;
import com.hackathon.javatemplate.vo.requestVo.SampleEntityRequestVo;
import com.hackathon.javatemplate.vo.requestVo.UpdateSampleEntityRequestVo;
import com.hackathon.javatemplate.vo.responseVo.SampleEntityResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.hackathon.javatemplate.auth.enums.Permissions.*;
import static com.hackathon.javatemplate.auth.enums.Permissions.ADMIN_CREATE;
import static com.hackathon.javatemplate.auth.enums.Permissions.ADMIN_READ;
import static java.time.ZoneOffset.*;

@Component
public class SampleEntityMapper {

    @Autowired
    UserMapper userMapper;
    public User mapUserFromSampleEntity(SampleEntityRequestVo requestVo) {

        LocalDateTime localDateTime = LocalDateTime.now(UTC);

        User user = new User();

        user.setUserName(requestVo.getUserName());
        user.setEmail(requestVo.getEmail());
        user.setPhone(requestVo.getPhoneNumber());
        user.setIsActive(true);
        user.setIsDisable(false);
        user.setCreatedAt(localDateTime);

        List<String> permissions = new ArrayList<>();
        permissions.add(ADMIN_CREATE.name);
        permissions.add(ADMIN_READ.name);
        permissions.add(ADMIN_UPDATE.name);
        permissions.add(ADMIN_DELETE.name);
        user.setRoles(userMapper.getRolesFromPermissions(permissions));
        return user;
    }

    public SampleEntity mapSampleEntityFromRequest(SampleEntityRequestVo requestVo) {

        SampleEntity response = new SampleEntity();
        response.setFirstName(requestVo.getFirstName());
        response.setLastName(requestVo.getLastName());
        return response;
    }

    public User mapUSerFromUpdateRequest(UpdateSampleEntityRequestVo requestVo, User dbUser) {

        LocalDateTime localDateTime = LocalDateTime.now(UTC);
        dbUser.setUserName(requestVo.getUserName());
        dbUser.setEmail(requestVo.getEmail());
        dbUser.setPhone(requestVo.getPhoneNumber());
        dbUser.setUpdatedAt(localDateTime);

        return dbUser;
    }

    public SampleEntity mapSampleEntityFromUpdateRequest(UpdateSampleEntityRequestVo requestVo, SampleEntity dbSampleEntity) {

        dbSampleEntity.setFirstName(requestVo.getFirstName());
        dbSampleEntity.setLastName(requestVo.getLastName());
        return dbSampleEntity;
    }

    public SampleEntityResponseVo mapResponseFromDbSampleEntity(SampleEntity dbSampleEntity) {

        SampleEntityResponseVo response = new SampleEntityResponseVo();

        response.setId(dbSampleEntity.getUserId());
        response.setFirstName(dbSampleEntity.getFirstName());
        response.setLastName(dbSampleEntity.getLastName());
        return response;
    }

    public List<SampleEntityResponseVo> mapResponseListFromSampleEntity(List<SampleEntity> dbResponse) {

        List<SampleEntityResponseVo> responseVoList = new ArrayList<>();
        dbResponse.forEach(db ->{
            SampleEntityResponseVo response = new SampleEntityResponseVo();
            response.setLastName(db.getLastName());
            response.setFirstName(db.getFirstName());
            response.setId(db.getUserId());
            responseVoList.add(response);
        });

        return responseVoList;
    }
}
