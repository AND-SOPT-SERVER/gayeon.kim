package org.sopt.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    public void save(final Diary diary) {
        final long id = numbering.addAndGet(1);
        storage.put(id, diary.getBody());
    }

    public List<Diary> findAll() {
        final List<Diary> diaries = new ArrayList<>();
        for(long idx = 1; idx <= numbering.longValue(); idx++) {
            if (storage.containsKey(idx)) {
                final String body = storage.get(idx);
                diaries.add(new Diary(idx, body));
            }
        }
        return diaries;
    }

    public void delete(Long id) {
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        storage.remove(id);
    }

    public void revise(Long id, String body) {
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        storage.put(id, body);
    }
}