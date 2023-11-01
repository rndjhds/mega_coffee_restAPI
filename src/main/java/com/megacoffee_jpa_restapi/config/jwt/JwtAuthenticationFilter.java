package com.megacoffee_jpa_restapi.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megacoffee_jpa_restapi.auth.PrincipalDetails;
import com.megacoffee_jpa_restapi.dto.MemberDto;
import com.megacoffee_jpa_restapi.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Member loginMember = null;
        try {
            loginMember = gson.fromJson(request.getReader(), Member.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginMember.getId(), loginMember.getPassword());
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails authResultPrincipal = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject("megaToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 1))) // 잠시 1분으로 수정 다시 10분으로 변경 예정
                .withClaim("id", authResultPrincipal.getMember().getId())
                .withClaim("password", authResultPrincipal.getMember().getPassword())
                .withClaim("username", authResultPrincipal.getMember().getUsername())
                .sign(Algorithm.HMAC512("mega"));

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
