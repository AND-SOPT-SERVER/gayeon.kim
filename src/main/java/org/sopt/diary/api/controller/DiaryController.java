package org.sopt.diary.api.controller;

import jakarta.validation.Valid;
import org.sopt.diary.api.dto.request.DiaryPostRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.api.dto.response.IdResponse;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<IdResponse> createDiary(@Valid @RequestBody final DiaryPostRequest request) {
        return ResponseEntity.ok(diaryService.createDiary(request));
    }

    @GetMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryDetailResponse> getDiary(@PathVariable final Long diaryId) {
        return ResponseEntity.ok(diaryService.getDiary(diaryId));
    }

    @GetMapping("/diaries")
    public ResponseEntity<DiaryListResponse> getAllDiary() {
        return ResponseEntity.ok(diaryService.getDiaryList());
    }

    @GetMapping("/diaries/categories")
    public ResponseEntity<DiaryListResponse> getCategories(@RequestParam final String category) {
        return ResponseEntity.ok(diaryService.getCategoryDiaryList(category));
    }

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable final Long diaryId) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryResponse> updateDiary(@PathVariable final Long diaryId,
                                                     @Valid @RequestBody final DiaryPostRequest request) {
        return ResponseEntity.ok(diaryService.updateDiary(diaryId, request));
    }
}
