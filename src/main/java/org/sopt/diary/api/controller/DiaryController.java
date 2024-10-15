package org.sopt.diary.api.controller;

import org.sopt.diary.api.dto.request.DiaryPostRequest;
import org.sopt.diary.api.dto.response.DiaryDetailResponse;
import org.sopt.diary.api.dto.response.DiaryIdResponse;
import org.sopt.diary.api.dto.response.DiaryListResponse;
import org.sopt.diary.api.dto.response.DiaryResponse;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/luckybicky")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    public ResponseEntity<DiaryIdResponse> createDiary(@RequestBody final DiaryPostRequest request) {
        validateDiary(request);
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

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable final Long diaryId) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}")
    public ResponseEntity<DiaryResponse> updateDiary(@PathVariable final Long diaryId, @RequestBody final DiaryPostRequest request) {
        validateDiary(request);
        return ResponseEntity.ok(diaryService.updateDiary(diaryId, request));
    }

    private void validateDiary(final DiaryPostRequest request) {
        if (request.title().length() > 30 || request.content().length() > 100 ) {
            throw new IllegalArgumentException("최대 글자수를 초과하였습니다.");
        }
    }
}
