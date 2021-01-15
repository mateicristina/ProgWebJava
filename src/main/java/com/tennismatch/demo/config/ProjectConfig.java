package com.tennismatch.demo.config;

import com.tennismatch.demo.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public NewUserMapper newUserMapper() {
        return new NewUserMapperImpl();
    }

    @Bean
    public PracticeMapper practiceMapper() {
        return new PracticeMapperImpl();
    }

    @Bean
    public TennisBaseMapper tennisBaseMapper() {
        return new TennisBaseMapperImpl();
    }

    @Bean
    public TennisCourtListReservationsMapper tennisCourtListReservationsMapper() {
        return new TennisCourtListReservationsMapperImpl();
    }

    @Bean
    public MatchInvitationMapper matchInvitationMapper() {
        return new MatchInvitationMapperImpl();
    }

    @Bean
    public TennisCourtMapper tennisCourtMapper() {
        return new TennisCourtMapperImpl();
    }
}