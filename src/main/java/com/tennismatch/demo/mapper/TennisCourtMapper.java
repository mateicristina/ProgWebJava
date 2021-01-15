package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.TennisCourt;
import com.tennismatch.demo.dto.TennisCourtDto;
import org.mapstruct.Mapper;

@Mapper
public interface TennisCourtMapper extends EntityMapper<TennisCourtDto, TennisCourt> {
}
