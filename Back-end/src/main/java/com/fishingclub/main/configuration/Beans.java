package com.fishingclub.main.configuration;

import com.fishingclub.main.dto.CompetitionDTO;
import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.FishNoRelDTO;
import com.fishingclub.main.entities.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<Integer, Level> toLevel = new AbstractConverter<>() {
            protected Level convert(Integer code) {
                Level level = new Level();
                level.setCode(code);
                return level;
            }
        };

        modelMapper.addConverter(toLevel);

        modelMapper.typeMap(FishNoRelDTO.class, Fish.class)
            .addMappings(mapper -> {
                mapper.map(FishNoRelDTO::getLevelCode, Fish::setLevel);
            });

        modelMapper.typeMap(Competition.class, CompetitionDTO.class)
                .addMappings(mapper -> {
                    mapper.skip(CompetitionDTO::setRankings);
                    mapper.skip(CompetitionDTO::setHuntings);
                });

        modelMapper.typeMap(Ranking.class, RankingDTO.class)
                .addMappings(mapper -> {
                    mapper.skip(RankingDTO::setMember);
                });

        modelMapper.typeMap(Member.class, MemberDTO.class)
                .addMappings(mapper -> {
                    mapper.skip(MemberDTO::setHuntings);
                });

        return modelMapper;
    }
}
