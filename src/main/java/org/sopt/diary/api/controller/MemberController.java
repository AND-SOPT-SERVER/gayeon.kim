package org.sopt.diary.api.controller;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.request.JoinRequest;
import org.sopt.diary.api.dto.request.LoginRequest;
import org.sopt.diary.api.dto.response.IdResponse;
import org.sopt.diary.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/luckybicky")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid JoinRequest request) {
        memberService.joinMember(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<IdResponse> signup(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(memberService.login(request));
    }
}
