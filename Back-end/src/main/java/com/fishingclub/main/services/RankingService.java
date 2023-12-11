package com.fishingclub.main.services;

import com.fishingclub.main.dto.RankingDTO;
import com.fishingclub.main.dto.noRelations.RankingNoRelDTO;
import com.fishingclub.main.embeddables.RankingKey;
import com.fishingclub.main.repositories.RankingRepository;
import com.fishingclub.main.services.interfaces.IRankingService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RankingService implements IRankingService {
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    public RankingService(RankingRepository rankingRepository, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RankingDTO create(RankingNoRelDTO t) {
        return null;
    }

    @Override
    public RankingDTO update(RankingNoRelDTO t) {
        return null;
    }

    @Override
    public RankingDTO delete(RankingKey id) {
        return null;
    }

    @Override
    public RankingDTO getOne(RankingKey id) {
        return null;
    }

    @Override
    public Page<RankingDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
