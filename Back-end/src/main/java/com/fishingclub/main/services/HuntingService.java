package com.fishingclub.main.services;

import com.fishingclub.main.dto.HuntingDTO;
import com.fishingclub.main.dto.noRelations.HuntingNoRelDTO;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.entities.Fish;
import com.fishingclub.main.entities.Hunting;
import com.fishingclub.main.entities.Member;
import com.fishingclub.main.exceptions.ResourceBadRequestException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.exceptions.ResourceUnprocessableException;
import com.fishingclub.main.repositories.CompetitionRepository;
import com.fishingclub.main.repositories.FishRepository;
import com.fishingclub.main.repositories.HuntingRepository;
import com.fishingclub.main.repositories.MemberRepository;
import com.fishingclub.main.services.interfaces.IHuntingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class HuntingService implements IHuntingService {
    private final HuntingRepository huntingRepository;
    private final MemberRepository memberRepository;
    private final FishRepository fishRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HuntingService(HuntingRepository huntingRepository, MemberRepository memberRepository, FishRepository fishRepository, CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.memberRepository = memberRepository;
        this.fishRepository = fishRepository;
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HuntingDTO create(HuntingNoRelDTO h) {
        Hunting hunting;
        Fish fish = fishRepository.findById(h.getFishName()).orElseThrow(() -> new ResourceNotFoundException("Fish not found."));
        Competition competition = competitionRepository.findById(h.getCompetitionCode()).orElseThrow(() -> new ResourceNotFoundException("Competition not found."));
        Member member = memberRepository.findById(h.getMemberNumber()).orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        if (competition.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ResourceBadRequestException("You cannot add hunted fishes in a competition that did not start yet.");
        }

        if (competition.getEndTime().isAfter(LocalDateTime.now())) {
            throw new ResourceBadRequestException("You cannot add hunted fishes in a competition that already ended.");
        }

        if (h.getWeight() <= fish.getAverageWeight()) {
            throw new ResourceUnprocessableException("The weight of " + fish.getName() + " hunted fish must be at least equal to " + fish.getAverageWeight() + "Kg.");
        }

        Optional<Hunting> optHunting = huntingRepository.findByFishNameAndCompetitionCodeAndMemberNumber(h.getFishName(), h.getCompetitionCode(), h.getMemberNumber());

        if (optHunting.isPresent()) {
            Hunting hunt = optHunting.get();

            int newNumOfFish = hunt.getNumberOfFish();
            hunt.setNumberOfFish(++newNumOfFish);

            hunting = huntingRepository.save(hunt);
        } else {
            hunting = huntingRepository.save(modelMapper.map(h, Hunting.class));
        }

        return modelMapper.map(hunting, HuntingDTO.class);
    }

    @Override
    public HuntingDTO update(HuntingNoRelDTO h) {
        return null;
    }

    @Override
    public HuntingDTO delete(Integer id) {
        return null;
    }

    @Override
    public HuntingDTO getOne(Integer id) {
        return null;
    }

    @Override
    public Page<HuntingDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
