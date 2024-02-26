package com.fishingclub.main.services.interfaces;

import com.fishingclub.main.dto.JwtAuthResponse;
import com.fishingclub.main.dto.MemberDTO;
import com.fishingclub.main.dto.noRelations.MemberNoRelDTO;
import com.fishingclub.main.dto.noRelations.SignInDTO;

public interface IMemberService extends GenericService<MemberDTO, MemberNoRelDTO, Integer> {
    JwtAuthResponse signIn(SignInDTO s);
}
