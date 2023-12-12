package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, String> {}
