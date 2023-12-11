package com.fishingclub.main.services;

import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.noRelations.MemberNoRelDTO;
import com.fishingclub.main.entities.Member;
import com.fishingclub.main.exceptions.ResourceAlreadyExistException;
import com.fishingclub.main.exceptions.ResourceNotFoundException;
import com.fishingclub.main.repositories.MemberRepository;
import com.fishingclub.main.services.interfaces.IMemberService;
import com.fishingclub.main.utils.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

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
    public MemberDTO update(MemberNoRelDTO m) {
        Member member = memberRepository.findById(m.getNumber()).orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        if (memberRepository.existsByIdentityNumber(m.getIdentityNumber()) && !Objects.equals(member.getIdentityNumber(), m.getIdentityNumber())) {
            throw new ResourceAlreadyExistException("Member with this identity number already exists.");
        }

        Member updatedMember = memberRepository.save(modelMapper.map(m, Member.class));

        return modelMapper.map(updatedMember, MemberDTO.class);
    }

    @Override
    public MemberDTO delete(Integer id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        memberRepository.deleteById(id);

        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public MemberDTO getOne(Integer id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public Page<MemberDTO> getAll(Map<String, Object> params) {
        Page<MemberDTO> members;
        String fullName = params.get("fullName").toString().toLowerCase();
        Pageable pageable = Utilities.managePagination((Integer) params.get("page"), (Integer) params.get("size"), (String) params.get("sortBy"), (String) params.get("sortOrder"));

        if (fullName.isEmpty()) {
            Utilities<MemberDTO, Member, Integer> utils = new Utilities<>(modelMapper, memberRepository);
            members = utils.getAllContents(params, MemberDTO.class);
        } else {
            Page<Member> contents = memberRepository.findAllByFullName(fullName, pageable);
            members = contents.map(c -> modelMapper.map(c, MemberDTO.class));
        }

        return members;
    }
}
