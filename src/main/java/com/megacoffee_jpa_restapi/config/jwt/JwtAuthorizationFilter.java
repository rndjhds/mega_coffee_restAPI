package com.megacoffee_jpa_restapi.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.megacoffee_jpa_restapi.auth.PrincipalDetails;
import com.megacoffee_jpa_restapi.entity.member.Member;
import com.megacoffee_jpa_restapi.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request,response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ","");
        String id = JWT.require(Algorithm.HMAC512("mega")).build().verify(jwtToken).getClaim("id").asString();

        if(StringUtils.hasText("id")){
            Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException());

            PrincipalDetails principalDetails = new PrincipalDetails(member);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}
