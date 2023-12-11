package com.fishingclub.main.services;

import com.fishingclub.main.dto.FishDTO;
import com.fishingclub.main.dto.noRelations.FishNoRelDTO;
import com.fishingclub.main.entities.Fish;
import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.repositories.FishRepository;
import com.fishingclub.main.services.interfaces.IFishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FishService implements IFishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FishService(FishRepository fishRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FishDTO create(FishNoRelDTO f) {
        if (fishRepository.existsById(f.getName())) {
            throw new ResourceAlreadyExistException("Fish with the name \"" + f.getName() + "\" already exists.");
        }

        Fish fish = fishRepository.save(modelMapper.map(f, Fish.class));

        return modelMapper.map(fish, FishDTO.class);
    }

    @Override
    public FishDTO update(FishNoRelDTO f) {
        if (!fishRepository.existsById(f.getName())) {
            throw new ResourceNotFoundException("Fish not found.");
        }

        Fish fish = fishRepository.save(modelMapper.map(f, Fish.class));

        return modelMapper.map(fish, FishDTO.class);
    }

    @Override
    public FishDTO delete(String id) {
        return null;
    }

    @Override
    public FishDTO getOne(String id) {
        return null;
    }

    @Override
    public Page<FishDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
