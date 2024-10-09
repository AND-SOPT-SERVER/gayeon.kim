package org.sopt.week1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public void writeDiary(final String body) {
        Diary diary = new Diary(null, body);
        diaryRepository.save(diary);
    }

    public void deleteDiary(String id) {
        try {
            diaryRepository.delete(convertIdToLong(id));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void reviseDiary(String id, String body) {
        try {
            diaryRepository.revise(convertIdToLong(id), body);
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
}