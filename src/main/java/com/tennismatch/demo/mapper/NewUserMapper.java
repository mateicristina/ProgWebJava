package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.NewUser;
import com.tennismatch.demo.dto.NewUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface NewUserMapper extends EntityMapper<NewUserDto, NewUser> {
}