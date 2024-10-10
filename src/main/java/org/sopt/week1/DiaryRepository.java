package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    protected void save(final Diary diary) {
        final long id = numbering.addAndGet(1);
        storage.put(id, diary.getBody());
    }

    protected List<Diary> findAll() {
        final List<Diary> diaries = new ArrayList<>();
        for(long idx = 1; idx <= numbering.longValue(); idx++) {
            if (storage.containsKey(idx)) {
                final String body = storage.get(idx);
                diaries.add(new Diary(idx, body));
            }
        }
        return diaries;
    }

    protected void delete(final Diary diary) {
        storage.remove(diary.getId());
    }

    protected void revise(final Diary diary) {
        storage.put(diary.getId(), diary.getBody());
    }

    protected Diary findById(final long id) {
        if (!storage.containsKey(id)) {
            throw new InvalidInputException("존재하지 않는 id 입니다.");
        }
        return new Diary(id, storage.get(id));
    }

    protected void restore(final Diary diary) {
        storage.put(diary.getId(), diary.getBody());
    }
}