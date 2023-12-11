package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Boolean existsByIdentityNumber(String identityNumber);
}
