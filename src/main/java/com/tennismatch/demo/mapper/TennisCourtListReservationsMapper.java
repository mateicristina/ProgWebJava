package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.TennisCourtListReservations;
import com.tennismatch.demo.dto.TennisCourtListReservationsDto;
import org.mapstruct.Mapper;

@Mapper
public interface TennisCourtListReservationsMapper extends EntityMapper<TennisCourtListReservationsDto, TennisCourtListReservations> {
}
