package com.fishingclub.main.services;

import com.fishingclub.main.dto.CompetitionDTO;
import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.exceptions.ResourceBadRequestException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.exceptions.ResourceUnprocessableException;
import com.fishingclub.main.repositories.CompetitionRepository;
import com.fishingclub.main.services.interfaces.ICompetitionService;
import com.fishingclub.main.utils.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            throw new ResourceUnprocessableException("Starting time must be in the same day of the competition.");
        }

        if (competitionDTO.getStartTime().isAfter(competitionDTO.getEndTime())) {
            throw new ResourceUnprocessableException("Ending time cannot be before the starting time");
        }

        Competition competition = modelMapper.map(competitionDTO, Competition.class);

        Competition createdCompetition = competitionRepository.save(competition);

        return modelMapper.map(createdCompetition, CompetitionDTO.class);
    }

    @Override
    public CompetitionDTO update(CompetitionNoRelDTO competitionDTO) {
        if (competitionDTO.getCode().isBlank()) {
            throw new ResourceBadRequestException("You must provide the code of the competition.");
        }

        Competition competition = competitionRepository.findById(competitionDTO.getCode()).orElseThrow(() -> new ResourceNotFoundException("Competition with the code \"" + competitionDTO.getCode() + "\" does not exist."));

        if (!competition.getDate().isEqual(competitionDTO.getDate()) || !competition.getLocation().equals(competitionDTO.getLocation())
        ) {
            throw new ResourceAlreadyExistException("Date and Location cannot be updated.");
        }

        if (!competition.getDate().isEqual(competitionDTO.getStartTime().toLocalDate())) {
            throw new ResourceBadRequestException("Starting time must be in the same day of the competition.");
        }

        if (competitionDTO.getStartTime().isAfter(competitionDTO.getEndTime())) {
            throw new ResourceBadRequestException("Ending time cannot be before the starting time");
        }

        competition.setStartTime(competitionDTO.getStartTime());
        competition.setEndTime(competitionDTO.getEndTime());
        competition.setAmount(competitionDTO.getAmount());

        Competition updatedCompetition = competitionRepository.save(competition);

        return modelMapper.map(updatedCompetition, CompetitionDTO.class);
    }

    @Override
    public CompetitionDTO delete(String id) {
        if (id.isBlank()) {
            throw new ResourceBadRequestException("You must provide the code of the competition.");
        }

        Competition competition = competitionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Competition does not exist."));

        competitionRepository.deleteById(id);

        return modelMapper.map(competition, CompetitionDTO.class);
    }

    @Override
    public CompetitionDTO getOne(String id) {
        if (id.isBlank()) {
            throw new ResourceBadRequestException("You must enter the code of the competition.");
        }

        Competition competition = competitionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Competition does not exist."));

        return modelMapper.map(competition, CompetitionDTO.class);
    }

    @Override
    public Page<CompetitionDTO> getAll(Map<String, Object> params) {
        Pageable pageable = Utilities.managePagination((Integer) params.get("page"), (Integer) params.get("size"), (String) params.get("sortBy"), (String) params.get("sortOrder"));

        Page<Competition> competitions = competitionRepository.findAll(pageable);

        Page<CompetitionDTO> competitionsDTO = competitions.map(c -> modelMapper.map(c, CompetitionDTO.class));

        if (!competitions.hasContent()) {
            String message = "";

            if (competitions.getTotalPages() > 0 && (Integer) params.get("page") + 1 > competitions.getTotalPages()) {
                message = "No competitions found in the page " + ((Integer) params.get("page") + 1) + ".";
            } else {
                message = "No competitions found.";
            }

            throw new ResourceNotFoundException(message);
        }

        return competitionsDTO;
    }
}
