package com.megacoffee_jpa_restapi.auth;

import com.megacoffee_jpa_restapi.entity.member.Member;
import com.megacoffee_jpa_restapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
        return new PrincipalDetails(findMember);
    }
}
