package com.fishingclub.main.repositories;

import com.fishingclub.main.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Boolean existsByIdentityNumber(String identityNumber);

    @Query("SELECT m FROM Member m WHERE CONCAT(m.name, ' ', m.familyName) LIKE %:fullName%")
    Page<Member> findAllByFullName(@Param("fullName") String fullName, Pageable pageable);
}
