package com.multi.db.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.db.model.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
