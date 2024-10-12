package org.sopt.week1;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TrashBin {
    private final Map<Long, Diary> trashCan = new ConcurrentHashMap<>();

    protected void pile(final Diary diary) {
        trashCan.put(diary.getId(), diary);
    }

    protected Optional<Diary> restore(final long id) {
        if (!trashCan.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(trashCan.get(id));
    }

    protected void remove(final long diaryId) {
        trashCan.remove(diaryId);
    }
}
