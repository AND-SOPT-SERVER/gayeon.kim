package org.sopt.week1;

import java.util.List;
import org.sopt.week1.Main.UI.InvalidInputException;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public List<Diary> getDiaryList() {
        return diaryRepository.findAll();
    }

    public void writeDiary(final String body) {
        checkBodyLength(body);
        Diary diary = new Diary(null, body);
        diaryRepository.save(diary);
    }

    public void deleteDiary(final String id) {
        long diaryId = convertIdToLong(id);
        diaryRepository.delete(diaryRepository.findById(diaryId));
    }

    public void reviseDiary(final String id, final String body) {
        long diaryId = convertIdToLong(id);
        checkBodyLength(body);
        Diary diary = diaryRepository.findById(diaryId);
        diary.updateBody(body);
        diaryRepository.revise(diary);
    }

    private long convertIdToLong(final String id) {
        try {
            return Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("유효한 숫자가 아닙니다.");
        }
    }

    private void checkBodyLength(final String body) {
        if (body.codePointCount(0, body.length()) > 30) {
            throw new InvalidInputException("최대 글자 수(30자)를 초과하였습니다.");
        }
    }
}