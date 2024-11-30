package com.alstom.codingtest.mapper;

import com.alstom.codingtest.domain.request.auth.SignUpRequest;
import com.alstom.codingtest.domain.request.train.CreateTrainRequest;
import com.alstom.codingtest.domain.request.train.UpdateTrainRequest;
import com.alstom.codingtest.domain.response.auth.LogInResponse;
import com.alstom.codingtest.domain.response.train.TrainResponse;
import com.alstom.codingtest.entity.Station;
import com.alstom.codingtest.entity.Train;
import com.alstom.codingtest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    User fromSignUpRequest(SignUpRequest request, String password);

    @Mapping(target = "token", source = "token")
    LogInResponse toLogInResponse(User user, String token);
}
