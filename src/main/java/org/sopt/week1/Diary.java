package org.sopt.week1;

import java.time.LocalDate;

public class Diary {
    private Long id;
    private String body;
    private final ReviseInfo reviseInfo;

    public Diary(Long id, String body) {
        this.id = id;
        this.body = body;
        this.reviseInfo = new ReviseInfo();
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void updateBody(String body) {
        this.body = body;
        reviseInfo.updateReviseCount();
    }

    public boolean checkReviseCount() {
        if (reviseInfo.getReviseDate().isBefore(LocalDate.now())) {
            reviseInfo.resetReviseCount();
        }
        return reviseInfo.checkReviseCount();
    }
}
