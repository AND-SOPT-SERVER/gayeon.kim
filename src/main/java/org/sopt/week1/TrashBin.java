package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TrashBin {
    private final Map<Long, String> trashCan = new ConcurrentHashMap<>();

    protected void pile(Diary diary) {
        trashCan.put(diary.getId(), diary.getBody());
    }

    protected Optional<Diary> restore(long id) {
        if (!trashCan.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(new Diary(id, trashCan.get(id)));
    }
}
