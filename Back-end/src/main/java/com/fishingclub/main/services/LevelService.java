package com.fishingclub.main.services;

import com.fishingclub.main.dto.LevelDTO;
import com.fishingclub.main.dto.noRelations.LevelNoRelDTO;
import com.fishingclub.main.entities.Level;
import com.fishingclub.main.repositories.LevelRepository;
import com.fishingclub.main.services.interfaces.ILevelService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Map;

public class LevelService implements ILevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LevelDTO create(LevelNoRelDTO l) {
        Level level = modelMapper.map(l, Level.class);

        Level createdLevel = levelRepository.save(level);

        return modelMapper.map(createdLevel, LevelDTO.class);
    }

    @Override
    public LevelDTO update(LevelNoRelDTO t) {
        return null;
    }

    @Override
    public LevelDTO delete(Integer id) {
        return null;
    }

    @Override
    public LevelDTO getOne(Integer id) {
        return null;
    }

    @Override
    public Page<LevelDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
