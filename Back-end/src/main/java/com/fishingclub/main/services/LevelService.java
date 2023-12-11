package com.fishingclub.main.services;

import com.fishingclub.main.dto.LevelDTO;
import com.fishingclub.main.dto.noRelations.LevelNoRelDTO;
import com.fishingclub.main.entities.Level;
import com.fishingclub.main.exceptions.ResourceBadRequestException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.repositories.LevelRepository;
import com.fishingclub.main.services.interfaces.ILevelService;
import com.fishingclub.main.utils.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LevelService implements ILevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Autowired
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
    public LevelDTO update(LevelNoRelDTO l) {
        if (l.getCode() <= 0) {
            throw new ResourceBadRequestException("Code of the level is invalid.");
        }

        Level level = levelRepository.findById(l.getCode()).orElseThrow(() -> new ResourceNotFoundException("Level with the provided code does not exist."));

        level.setDescription(l.getDescription());
        level.setPoints(l.getPoints());

        Level updatedLevel = levelRepository.save(level);

        return modelMapper.map(updatedLevel, LevelDTO.class);
    }

    @Override
    public LevelDTO delete(Integer id) {
        if (id <= 0) {
            throw new ResourceBadRequestException("Code of the level is invalid.");
        }

        Level level = levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Level with the provided code does not exist."));

        levelRepository.deleteById(id);

        return modelMapper.map(level, LevelDTO.class);
    }

    @Override
    public LevelDTO getOne(Integer id) {
        if (id <= 0) {
            throw new ResourceBadRequestException("Code of the level is invalid.");
        }

        Level level = levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Level with the provided code does not exist."));

        return modelMapper.map(level, LevelDTO.class);
    }

    @Override
    public Page<LevelDTO> getAll(Map<String, Object> params) {
        Utilities<LevelDTO, Level, Integer> utils = new Utilities<>(modelMapper, levelRepository);

        return utils.getAllContents(params, LevelDTO.class);
    }
}
