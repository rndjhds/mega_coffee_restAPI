package com.megacoffee_jpa_restapi.controller;

import com.megacoffee_jpa_restapi.dto.MemberDto;
import com.megacoffee_jpa_restapi.entity.member.Member;
import com.megacoffee_jpa_restapi.entity.member.MemberType;
import com.megacoffee_jpa_restapi.entity.member.PermitStatus;
import com.megacoffee_jpa_restapi.exception.InvalidMemberException;
import com.megacoffee_jpa_restapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    @PostMapping("/join")
    public MemberDto join(@Valid @RequestBody MemberDto memberDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new InvalidMemberException(errorMap);

        }
        Member member = new Member(memberDto.getId(), bCryptPasswordEncoder.encode(memberDto.getPassword()));
        member.changeMemberInfo(memberDto.getUsername(), memberDto.getEmail());
        member.changeMemberStatus(MemberType.BUYER, PermitStatus.DENIED, "N");
        memberRepository.save(member);
        return memberDto;
    }
}
