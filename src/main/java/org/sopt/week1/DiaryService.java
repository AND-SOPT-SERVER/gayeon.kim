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
        diaryRepository.delete(Long.valueOf(id));
    }

    public void reviseDiary(String id, String body) {
        try {
            Long diaryId = Long.valueOf(id);
            diaryRepository.revise(diaryId, body);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

}