package org.sopt.week1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public void writeDiary(final String body) {
        try {
            checkBodyLength(body);
            Diary diary = new Diary(null, body);
            diaryRepository.save(diary);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDiary(String id) {
        try {
            long diaryId = convertIdToLong(id);
            diaryRepository.delete(diaryRepository.findById(diaryId));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void reviseDiary(String id, String body) {
        try {
            long diaryId = convertIdToLong(id);
            diaryRepository.revise(diaryRepository.findById(diaryId), body);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private long convertIdToLong(String id) {
        try {
            return Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID 값이 유효한 숫자 형식이 아닙니다.");
        }
    }

    private void checkBodyLength(String body) {
        if (body.codePointCount(0, body.length()) > 30) {
            throw new IllegalArgumentException("최대 글자 수는 30자로 제한됩니다.");
        }
    }
}