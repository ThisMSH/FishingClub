package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FishRepository extends JpaRepository<Fish, String>, JpaSpecificationExecutor<Fish> {
}
