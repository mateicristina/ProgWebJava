package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.Practice;
import com.tennismatch.demo.dto.PracticeDto;
import org.mapstruct.Mapper;

@Mapper
public interface PracticeMapper extends EntityMapper<PracticeDto, Practice> {
}
