package com.fishingclub.main.services;

import com.fishingclub.main.dto.CompetitionDTO;
import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.exceptions.ResourceBadRequestException;
import com.fishingclub.main.repositories.CompetitionRepository;
import com.fishingclub.main.services.interfaces.ICompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CompetitionService implements ICompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired

    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CompetitionDTO create(CompetitionNoRelDTO competitionDTO) {
        if (this.competitionRepository.existsByDate(competitionDTO.getDate())) {
            throw new ResourceAlreadyExistException("You cannot create 2 or more competitions in the same day.");
        }

        if (!competitionDTO.getDate().isEqual(competitionDTO.getStartTime().toLocalDate())) {
            throw new ResourceBadRequestException("Starting time must be in the same day of the competition.");
        }

        if (competitionDTO.getStartTime().isAfter(competitionDTO.getEndTime())) {
            throw new ResourceBadRequestException("Ending time cannot be before the starting time");
        }

        Competition competition = modelMapper.map(competitionDTO, Competition.class);

        Competition createdCompetition = competitionRepository.save(competition);

        return modelMapper.map(createdCompetition, CompetitionDTO.class);
    }

    @Override
    public CompetitionDTO update(CompetitionNoRelDTO competitionDTO) {
        return null;
    }

    @Override
    public CompetitionDTO delete(String id) {
        return null;
    }

    @Override
    public CompetitionDTO getOne(String id) {
        return null;
    }

    @Override
    public Page<CompetitionDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
