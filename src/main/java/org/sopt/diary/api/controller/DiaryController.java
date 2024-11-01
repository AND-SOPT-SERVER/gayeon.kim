package org.sopt.diary.api.controller;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.request.DiaryPostRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.api.dto.response.IdResponse;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/luckybicky")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    public ResponseEntity<IdResponse> createDiary(@RequestHeader(HttpHeaders.AUTHORIZATION) final Long id,
                                                  @Valid @RequestBody final DiaryPostRequest request) {
        return ResponseEntity.ok(diaryService.createDiary(id, request));
    }

    @GetMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> getDiary(@RequestHeader(HttpHeaders.AUTHORIZATION) final Long id,
                                                        @PathVariable final Long diaryId) {
        return ResponseEntity.ok(diaryService.getDiary(id,diaryId));
    }

    @GetMapping("/diaries")
    public ResponseEntity<DiaryListResponse> getAllDiary(@RequestHeader(HttpHeaders.AUTHORIZATION) final Long id) {
        return ResponseEntity.ok(diaryService.getDiaryList(id));
    }

    @GetMapping("/diaries/categories")
    public ResponseEntity<DiaryListResponse> getCategories(@RequestHeader(HttpHeaders.AUTHORIZATION) final Long id,
                                                           @RequestParam final String category) {
        return ResponseEntity.ok(diaryService.getCategoryDiaryList(id,category));
    }

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@RequestHeader(HttpHeaders.AUTHORIZATION) final Long id,
                                            @PathVariable final Long diaryId) {
        diaryService.deleteDiary(id,diaryId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryResponse> updateDiary(@RequestHeader(HttpHeaders.AUTHORIZATION) final Long id,
                                                     @PathVariable final Long diaryId,
                                                     @Valid @RequestBody final DiaryPostRequest request) {
        return ResponseEntity.ok(diaryService.updateDiary(id, diaryId, request));
    }
}
