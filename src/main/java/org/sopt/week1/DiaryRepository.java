package org.sopt.week1;

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

    protected void delete(Long id) {
        storage.remove(id);
    }

    protected void revise(Long id, String body) {
        storage.put(id, body);
    }

    protected long findById(long id) {
        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 ID 입니다.");
        }
        return id;
    }
}