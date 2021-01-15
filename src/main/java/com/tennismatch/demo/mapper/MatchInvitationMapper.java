package com.tennismatch.demo.mapper;

import com.tennismatch.demo.domain.MatchInvitation;
import com.tennismatch.demo.dto.MatchInvitationDto;
import org.mapstruct.Mapper;

@Mapper
public interface MatchInvitationMapper extends EntityMapper<MatchInvitationDto, MatchInvitation> {
}
