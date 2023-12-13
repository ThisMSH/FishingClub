package com.fishingclub.main.services;

import com.fishingclub.main.dto.HuntingDTO;
import com.fishingclub.main.dto.noRelations.HuntingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.entities.*;
import com.fishingclub.main.exceptions.ResourceBadRequestException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.exceptions.ResourceUnprocessableException;
import com.fishingclub.main.repositories.*;
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
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HuntingService(HuntingRepository huntingRepository, MemberRepository memberRepository, FishRepository fishRepository, CompetitionRepository competitionRepository, RankingRepository rankingRepository, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.memberRepository = memberRepository;
        this.fishRepository = fishRepository;
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HuntingDTO create(HuntingNoRelDTO h) {
        Hunting hunting;
        Fish fish = fishRepository.findById(h.getFishName().toLowerCase()).orElseThrow(() -> new ResourceNotFoundException("Fish not found."));
        Competition competition = competitionRepository.findById(h.getCompetitionCode()).orElseThrow(() -> new ResourceNotFoundException("Competition not found."));
        Member member = memberRepository.findById(h.getMemberNumber()).orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        if (LocalDateTime.now().isBefore(competition.getStartTime())) {
            throw new ResourceBadRequestException("You cannot add hunted fishes in a competition that did not start yet.");
        }

        if (LocalDateTime.now().isAfter(competition.getEndTime())) {
            throw new ResourceBadRequestException("You cannot add hunted fishes in a competition that already ended.");
        }

        if (h.getWeight() <= fish.getAverageWeight()) {
            throw new ResourceUnprocessableException("The weight of " + fish.getName() + " hunted fish must be at least equal to " + fish.getAverageWeight() + "Kg.");
        }

        Optional<Hunting> optHunting = huntingRepository.findByFishNameAndCompetitionCodeAndMemberNumber(h.getFishName(), h.getCompetitionCode(), h.getMemberNumber());

        RankingKey key = new RankingKey();
        key.setCompetitionCode(h.getCompetitionCode());
        key.setMemberNumber(h.getMemberNumber());

        Ranking ranking = rankingRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("The member that you are trying to save his hunted fishes is not participated in this competition."));

        int score = ranking.getScore();
        score += fish.getLevel().getPoints();
        ranking.setScore(score);
        rankingRepository.save(ranking);

        Hunting hunt;

        if (optHunting.isPresent()) {
            hunt = optHunting.get();

            int newNumOfFish = hunt.getNumberOfFish();
            hunt.setNumberOfFish(++newNumOfFish);

        } else {
            hunt = new Hunting();
            hunt.setMember(member);
            hunt.setCompetition(competition);
            hunt.setFish(fish);

        }
        hunting = huntingRepository.save(hunt);

        return modelMapper.map(hunting, HuntingDTO.class);
    }

    @Override
    public HuntingDTO update(HuntingNoRelDTO h) {
        return null;
    }

    @Override
    public HuntingDTO delete(Integer id) {
        Hunting hunting = huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting not found."));

        RankingKey key = new RankingKey();
        key.setMemberNumber(hunting.getMember().getNumber());
        key.setCompetitionCode(hunting.getCompetition().getCode());

        Ranking ranking = rankingRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("The member that you are trying to save his hunted fishes is not participated in this competition."));

        int score = ranking.getScore();
        score -= hunting.getFish().getLevel().getPoints();
        ranking.setScore(score);
        rankingRepository.save(ranking);

        if (hunting.getNumberOfFish() > 1) {
            int newNumOfFish = hunting.getNumberOfFish();
            hunting.setNumberOfFish(--newNumOfFish);

            hunting = huntingRepository.save(hunting);
        } else {
            hunting.setNumberOfFish(0);
            
            huntingRepository.deleteById(id);
        }

        return modelMapper.map(hunting, HuntingDTO.class);
    }

    @Override
    public HuntingDTO getOne(Integer id) {
        Hunting hunting = huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting not found."));

        return modelMapper.map(hunting, HuntingDTO.class);
    }

    @Override
    public Page<HuntingDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
