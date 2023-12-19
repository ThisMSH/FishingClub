package com.fishingclub.main.configuration;

import com.fishingclub.main.dto.CompetitionDTO;
import com.fishingclub.main.dto.noRelations.FishNoRelDTO;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.entities.Fish;
import com.fishingclub.main.entities.Level;
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

        return modelMapper;
    }
}
