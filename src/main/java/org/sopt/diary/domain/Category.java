package org.sopt.diary.domain;

import java.util.Arrays;

public enum Category {
    FOOD("음식"),
    HEALTH("운동"),
    READING("독서"),
    TRAVEL("여행"),
    MUSIC("음악"),
    MOVIE("영화"),
    DEFAULT("기타");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public static Category getEnumCategoryFromStringCategory(String diaryCategory) {
        if (diaryCategory == null || diaryCategory.trim().isEmpty()) {
            return Category.DEFAULT;
        }
        return Arrays.stream(values())
                .filter(c -> c.category.equals(diaryCategory))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카테고리입니다."));
    }
}
