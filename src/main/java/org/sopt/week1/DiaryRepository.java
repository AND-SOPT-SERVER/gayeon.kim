package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, Diary> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    protected void save(final String body) {
        final long id = numbering.addAndGet(1);
        storage.put(id, new Diary(id, body));
    }

    protected List<Diary> findAll() {
        final List<Diary> diaries = new ArrayList<>();
        for(long idx = 1; idx <= numbering.longValue(); idx++) {
            if (storage.containsKey(idx)) {
                diaries.add(storage.get(idx));
            }
        }
        return diaries;
    }

    protected void delete(final Diary diary) {
        storage.remove(diary.getId());
    }

    protected void revise(final Diary diary) {
        storage.put(diary.getId(), diary);
    }

    protected Diary findById(final long id) {
        if (!storage.containsKey(id)) {
            throw new InvalidInputException("존재하지 않는 id 입니다.");
        }
        return storage.get(id);
    }

    protected void restore(final Diary diary) {
        storage.put(diary.getId(), diary);
    }
}