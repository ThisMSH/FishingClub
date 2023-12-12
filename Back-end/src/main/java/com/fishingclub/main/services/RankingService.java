package com.fishingclub.main.services;

import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.entities.Member;
import com.fishingclub.main.entities.Ranking;
import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.exceptions.ResourceUnprocessableException;
import com.fishingclub.main.repositories.CompetitionRepository;
import com.fishingclub.main.repositories.MemberRepository;
import com.fishingclub.main.repositories.RankingRepository;
import com.fishingclub.main.services.interfaces.IRankingService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class RankingService implements IRankingService {
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    public RankingService(RankingRepository rankingRepository, MemberRepository memberRepository, CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RankingDTO create(RankingNoRelDTO t) {
        RankingKey key = new RankingKey();
        key.setCompetitionCode(t.getCompetitionCode());
        key.setMemberNumber(t.getMemberNumber());

        Member member = memberRepository.findById(t.getMemberNumber()).orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        Competition competition = competitionRepository.findById(t.getCompetitionCode()).orElseThrow(() -> new ResourceNotFoundException("Competition not found."));

        if (rankingRepository.existsById(key)) {
            throw new ResourceAlreadyExistException("Member \"" + member.getName() + " " + member.getFamilyName() + "\" is already in this competition.");
        }

        if (competition.getEndTime().isBefore(LocalDateTime.now())) {
            throw new ResourceUnprocessableException("This competition has already ended.");
        }

        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), competition.getStartTime());

        if (hours <= 24) {
            throw new ResourceUnprocessableException("You cannot assign new participants before 24h or less from the start of the competition.");
        }

        Ranking ranking = new Ranking();
        ranking.setId(key);
        ranking.setMember(member);
        ranking.setCompetition(competition);

        Ranking createdRanking = rankingRepository.save(ranking);

        return modelMapper.map(createdRanking, RankingDTO.class);
    }

    @Override
    public RankingDTO update(RankingNoRelDTO t) {
        return null;
    }

    @Override
    public RankingDTO delete(RankingKey id) {
        RankingKey key = new RankingKey();
        key.setCompetitionCode(id.getCompetitionCode());
        key.setMemberNumber(id.getMemberNumber());

        if (!memberRepository.existsById(id.getMemberNumber())) {
            throw new ResourceNotFoundException("Member not found.");
        }

        if (!competitionRepository.existsById(id.getCompetitionCode())) {
            throw new ResourceNotFoundException("Competition not found.");
        }

        Ranking ranking = rankingRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("Ranking not found."));

        rankingRepository.deleteById(key);

        return modelMapper.map(ranking, RankingDTO.class);
    }

    @Override
    public RankingDTO getOne(RankingKey id) {
        RankingKey key = new RankingKey();
        key.setCompetitionCode(id.getCompetitionCode());
        key.setMemberNumber(id.getMemberNumber());

        if (!memberRepository.existsById(id.getMemberNumber())) {
            throw new ResourceNotFoundException("Member not found.");
        }

        if (!competitionRepository.existsById(id.getCompetitionCode())) {
            throw new ResourceNotFoundException("Competition not found.");
        }

        Ranking ranking = rankingRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("Ranking not found."));

        return modelMapper.map(ranking, RankingDTO.class);
    }

    @Override
    public Page<RankingDTO> getAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public void update(String code) {
        int count = 1;
        int temp = 0;

        if (!competitionRepository.existsById(code)) {
            throw new ResourceNotFoundException("Competition not found.");
        }

        List<Ranking> rankingList = rankingRepository.findAllByCompetitionCodeOrderByScoreDesc(code);

        for (int i = 0; i < rankingList.size(); i++) {
            if (i == 0) {
                rankingList.get(i).setRank(count);
                count++;
                continue;
            }

            if (rankingList.get(i - 1).getScore() == rankingList.get(i).getScore()) {
                count--;
                temp++;

                rankingList.get(i).setRank(count);
                count += temp;
                continue;
            }

            rankingList.get(i).setRank(count);
            count++;
            temp = 0;
        }

        rankingRepository.saveAll(rankingList);
    }
}
