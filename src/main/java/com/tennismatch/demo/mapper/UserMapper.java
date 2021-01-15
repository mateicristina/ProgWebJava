package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.User;
import com.tennismatch.demo.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends EntityMapper<UserDto, User> {
}