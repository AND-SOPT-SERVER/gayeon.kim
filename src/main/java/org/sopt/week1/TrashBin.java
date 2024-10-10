package org.sopt.week1;

import org.sopt.week1.Main.UI.InvalidInputException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrashBin {
    private final Map<Long, String> trashCan = new ConcurrentHashMap<>();

    protected void pile(Diary diary) {
        trashCan.put(diary.getId(), diary.getBody());
    }

    protected Diary restore(long id) {
        if (!trashCan.containsKey(id)) {
            throw new InvalidInputException("삭제되지 않는 id 입니다.");
        }
        return new Diary(id, trashCan.get(id));
    }
}
