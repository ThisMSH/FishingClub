package com.fishingclub.main.services;

import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.noRelations.MemberNoRelDTO;
import com.fishingclub.main.entities.Member;
import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.repositories.MemberRepository;
import com.fishingclub.main.services.interfaces.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberService implements IMemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MemberDTO create(MemberNoRelDTO m) {
        if (memberRepository.existsByIdentityNumber(m.getIdentityNumber())) {
            throw new ResourceAlreadyExistException("Member with this identity number already exists.");
        }

        Member member = modelMapper.map(m, Member.class);

        Member createdMember = memberRepository.save(member);

        return modelMapper.map(createdMember, MemberDTO.class);
    }

    @Override
    public MemberDTO update(MemberNoRelDTO t) {
        return null;
    }

    @Override
    public MemberDTO delete(Integer id) {
        return null;
    }

    @Override
    public MemberDTO getOne(Integer id) {
        return null;
    }

    @Override
    public Page<MemberDTO> getAll(Map<String, Object> params) {
        return null;
    }
}
