package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HuntingRepository extends JpaRepository<Hunting, Integer>, JpaSpecificationExecutor<Hunting> {
}
