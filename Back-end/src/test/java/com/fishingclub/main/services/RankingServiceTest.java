package com.fishingclub.main.services;

import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.CompetitionNoRelDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.entities.Competition;
import com.fishingclub.main.entities.Member;
import com.fishingclub.main.entities.Ranking;
import com.fishingclub.main.enums.IdentityDocumentType;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.repositories.CompetitionRepository;
import com.fishingclub.main.repositories.MemberRepository;
import com.fishingclub.main.repositories.RankingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {
    @Mock
    private RankingRepository rankingRepository;
    @Mock
    private CompetitionRepository competitionRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RankingService rankingService;

    private Ranking ranking;
    private Member member;
    private Competition competition;
    private RankingDTO rankingResponse;
    private RankingNoRelDTO rankingRequest;
    private RankingKey key;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setNumber(1);
        member.setName("El Mahdi");
        member.setFamilyName("Testing");
        member.setNationality("Moroccan");
        member.setAccessionDate(LocalDate.parse("2023-12-10"));
        member.setIdentityDocument(IdentityDocumentType.CIN);
        member.setIdentityNumber("HM123456");

        competition = new Competition();
        competition.setCode("elj-29-12-23");
        competition.setDate(LocalDate.parse("2023-12-29"));
        competition.setStartTime(LocalDateTime.of(2023, 12, 29, 6, 30));
        competition.setEndTime(LocalDateTime.of(2023, 12, 29, 19, 30));
        competition.setNumberOfParticipants(4);
        competition.setAmount(34.5);
        competition.setLocation("El Jadida");

        key = new RankingKey();
        key.setCompetitionCode(competition.getCode());
        key.setMemberNumber(member.getNumber());

        ranking = new Ranking();
        ranking.setId(key);
        ranking.setMember(member);
        ranking.setCompetition(competition);
        ranking.setRank(0);
        ranking.setScore(0);

        rankingResponse = new RankingDTO();
        rankingResponse.setMember(modelMapper.map(member, MemberDTO.class));
        rankingResponse.setCompetition(modelMapper.map(competition, CompetitionNoRelDTO.class));
        rankingResponse.setRank(0);
        rankingResponse.setScore(0);

        rankingRequest = new RankingNoRelDTO();
        rankingRequest.setCompetitionCode("elj-29-12-23");
        rankingRequest.setMemberNumber(1);
    }

    @Test
    void createSuccessfully() {
        when(memberRepository.findById(rankingRequest.getMemberNumber())).thenReturn(Optional.of(member));
        when(competitionRepository.findById(rankingRequest.getCompetitionCode())).thenReturn(Optional.of(competition));
        when(rankingRepository.existsById(any())).thenReturn(false);
        when(rankingRepository.getMembersCount(rankingRequest.getCompetitionCode())).thenReturn(0);
        when(rankingRepository.save(any())).thenReturn(ranking);
        when(modelMapper.map(ranking, RankingDTO.class)).thenReturn(rankingResponse);

        RankingDTO createdRanking = rankingService.create(rankingRequest);

        assertNotNull(createdRanking);
        assertSame(rankingResponse, createdRanking);

        verify(memberRepository).findById(rankingRequest.getMemberNumber());
        verify(competitionRepository).findById(rankingRequest.getCompetitionCode());
        verify(rankingRepository).existsById(any());
        verify(rankingRepository).getMembersCount(rankingRequest.getCompetitionCode());
        verify(rankingRepository).save(any());
        verify(modelMapper).map(ranking, RankingDTO.class);
    }

    @Test
    void createThrowMemberNotFound() {
        when(memberRepository.findById(rankingRequest.getMemberNumber())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rankingService.create(rankingRequest));

        verify(memberRepository).findById(rankingRequest.getMemberNumber());
    }

    @Test
    void createThrowCompetitionNotFound() {
        when(memberRepository.findById(rankingRequest.getMemberNumber())).thenReturn(Optional.of(member));
        when(competitionRepository.findById(rankingRequest.getCompetitionCode())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rankingService.create(rankingRequest));

        verify(memberRepository).findById(rankingRequest.getMemberNumber());
        verify(competitionRepository).findById(rankingRequest.getCompetitionCode());
    }

    @Test
    void getOne() {
        when(memberRepository.existsById(1)).thenReturn(true);
        when(competitionRepository.existsById("elj-29-12-23")).thenReturn(true);
        when(rankingRepository.findById(key)).thenReturn(Optional.of(ranking));
        when(modelMapper.map(ranking, RankingDTO.class)).thenReturn(rankingResponse);

        RankingDTO getRanking = rankingService.getOne(key);

        assertNotNull(getRanking);
        assertEquals(rankingResponse, getRanking);

        verify(memberRepository).existsById(1);
        verify(competitionRepository).existsById("elj-29-12-23");
        verify(rankingRepository).findById(key);
        verify(modelMapper).map(ranking, RankingDTO.class);
    }

    @Test
    void getOneNotFound() {
        when(memberRepository.existsById(1)).thenReturn(true);
        when(competitionRepository.existsById("elj-29-12-23")).thenReturn(true);
        when(rankingRepository.findById(key)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rankingService.getOne(key));

        verify(memberRepository).existsById(1);
        verify(competitionRepository).existsById("elj-29-12-23");
        verify(rankingRepository).findById(key);
    }

    @Test
    void deletedSuccessfully() {
        when(memberRepository.existsById(1)).thenReturn(true);
        when(competitionRepository.existsById("elj-29-12-23")).thenReturn(true);
        when(rankingRepository.findById(key)).thenReturn(Optional.of(ranking));
        when(modelMapper.map(ranking, RankingDTO.class)).thenReturn(rankingResponse);

        RankingDTO deletedRanking = rankingService.delete(key.getCompetitionCode(), key.getMemberNumber());

        assertNotNull(deletedRanking);
        assertEquals(rankingResponse, deletedRanking);

        verify(memberRepository).existsById(1);
        verify(competitionRepository).existsById("elj-29-12-23");
        verify(rankingRepository).findById(key);
        verify(rankingRepository).deleteById(key);
        verify(modelMapper).map(ranking, RankingDTO.class);
    }

    @Test
    void deletedRankingNotFound() {
        when(memberRepository.existsById(1)).thenReturn(true);
        when(competitionRepository.existsById("elj-29-12-23")).thenReturn(true);
        when(rankingRepository.findById(key)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> rankingService.delete(key.getCompetitionCode(), key.getMemberNumber()));

        verify(memberRepository).existsById(1);
        verify(competitionRepository).existsById("elj-29-12-23");
        verify(rankingRepository).findById(key);
    }
}