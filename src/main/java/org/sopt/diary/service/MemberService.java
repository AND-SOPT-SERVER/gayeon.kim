package org.sopt.diary.service;

import org.sopt.diary.api.dto.request.JoinRequest;
import org.sopt.diary.api.dto.request.LoginRequest;
import org.sopt.diary.api.dto.response.IdResponse;
import org.sopt.diary.domain.SoptMember;
import org.sopt.diary.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void joinMember(final JoinRequest request) {
        SoptMember member = new SoptMember(request.username(), request.password(), request.nickname(), request.age());
        memberRepository.save(member);
    }

    public IdResponse login(final LoginRequest request) {
        SoptMember member = findMember(request.username(), request.password());
        return IdResponse.of(member.getId());
    }

    private SoptMember findMember(final String username, final String password) {
        return memberRepository.findByPasswordAndUsername(password, username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
