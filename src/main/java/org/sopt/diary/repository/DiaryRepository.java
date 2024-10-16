package org.sopt.diary.repository;

import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();
    boolean existsByTitle(String title);

    List<DiaryEntity> findByCategory(Category category);
}
