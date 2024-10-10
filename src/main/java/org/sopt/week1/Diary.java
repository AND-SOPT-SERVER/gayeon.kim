package org.sopt.week1;

public class Diary {
    private Long id;
    private String body;

    public Diary(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void updateBody(String body) {
        this.body = body;
    }
}
