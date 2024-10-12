package org.sopt.week1;

import java.time.LocalDate;

public class ReviseInfo {
    private static final int REVISE_LIMIT = 2;
    private LocalDate reviseDate;
    private int reviseCount;

    public ReviseInfo() {
        this.reviseDate = LocalDate.now();
        this.reviseCount = 0;
    }

    public LocalDate getReviseDate() {
        return reviseDate;
    }

    public void updateReviseCount() {
        reviseCount++;
        reviseDate = LocalDate.now();
    }

    public void resetReviseCount() {
        reviseCount = 0;
    }

    public boolean checkReviseCount() {
        return reviseCount < REVISE_LIMIT;
    }
}
