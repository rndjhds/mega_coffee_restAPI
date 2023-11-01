package com.megacoffee_jpa_restapi.repository;

import com.megacoffee_jpa_restapi.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
