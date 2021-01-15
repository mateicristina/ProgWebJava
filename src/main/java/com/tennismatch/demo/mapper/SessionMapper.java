package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.User;
import com.tennismatch.demo.dto.LoginDto;
import org.mapstruct.Mapper;

@Mapper
public interface SessionMapper extends EntityMapper<LoginDto, User>{
}
