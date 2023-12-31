package com.fishingclub.main.services;

import com.fishingclub.main.dto.CompetitionDTO;
import com.fishingclub.main.dto.HuntingDTO;
import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.entities.Member;
import com.fishingclub.main.enums.CompetitionFilterType;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        if (competition.getDate() != null && (!competition.getDate().isEqual(competitionDTO.getDate()) || !competition.getLocation().equals(competitionDTO.getLocation()))
        ) {
            throw new ResourceAlreadyExistException("Date and Location cannot be updated.");
        }

        if (competition.getDate() != null && !competition.getDate().isEqual(competitionDTO.getStartTime().toLocalDate())) {
            throw new ResourceBadRequestException("Starting time must be in the same day of the competition.");
        }

        if (competitionDTO.getStartTime().isAfter(competitionDTO.getEndTime())) {
            throw new ResourceBadRequestException("Ending time cannot be before the starting time");
        }

        competition.setStartTime(competitionDTO.getStartTime());
        competition.setEndTime(competitionDTO.getEndTime());
        competition.setAmount(competitionDTO.getAmount());
        competition.setNumberOfParticipants(competitionDTO.getNumberOfParticipants());

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
        CompetitionDTO competitionDTO = modelMapper.map(competition, CompetitionDTO.class);
        List<RankingDTO> rankingDTOs = competition.getRankings().stream().map(ranking -> modelMapper.map(ranking, RankingDTO.class)).toList();

        for (int i = 0; i < rankingDTOs.size(); i++) {
            MemberDTO member = modelMapper.map(competition.getRankings().get(i).getMember(), MemberDTO.class);
            member.setHuntings(competition.getRankings().get(i).getMember().getHuntings().stream().map(hunting -> modelMapper.map(hunting, HuntingDTO.class)).collect(Collectors.toList()));
            rankingDTOs.get(i).setMember(member);
        }

        competitionDTO.setHuntings(competition.getHuntings().stream().map(hunting -> modelMapper.map(hunting, HuntingDTO.class)).collect(Collectors.toList()));
        competitionDTO.setRankings(rankingDTOs);

        return competitionDTO;
    }

    @Override
    public Page<CompetitionDTO> getAll(Map<String, Object> params) {
        Page<CompetitionDTO> competitions = null;
        Pageable pageable = Utilities.managePagination((Integer) params.get("page"), (Integer) params.get("size"), (String) params.get("sortBy"), (String) params.get("sortOrder"));

        if (params.get("filter") == CompetitionFilterType.ALL) {
            Utilities<CompetitionDTO, Competition, String> utils = new Utilities<>(modelMapper, competitionRepository);
            competitions = utils.getAllContents(params, CompetitionDTO.class);
        } else if (params.get("filter") == CompetitionFilterType.ONGOING) {
            Page<Competition> contents = competitionRepository.findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime.now(), LocalDateTime.now(), pageable);
            competitions = contents.map(c -> modelMapper.map(c, CompetitionDTO.class));
        } else if (params.get("filter") == CompetitionFilterType.DONE) {
            Page<Competition> contents = competitionRepository.findAllByEndTimeBefore(LocalDateTime.now(), pageable);
            competitions = contents.map(c -> modelMapper.map(c, CompetitionDTO.class));
        } else if (params.get("filter") == CompetitionFilterType.INCOMING) {
            Page<Competition> contents = competitionRepository.findAllByStartTimeAfter(LocalDateTime.now(), pageable);
            competitions = contents.map(c -> modelMapper.map(c, CompetitionDTO.class));
        }

        return competitions;
    }
}
