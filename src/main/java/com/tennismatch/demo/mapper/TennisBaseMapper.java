package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.TennisBase;
import com.tennismatch.demo.dto.TennisBaseDto;
import org.mapstruct.Mapper;

@Mapper
public interface TennisBaseMapper extends EntityMapper<TennisBaseDto, TennisBase>{
}
