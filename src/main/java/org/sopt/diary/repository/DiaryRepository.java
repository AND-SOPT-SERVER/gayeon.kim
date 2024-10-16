package org.sopt.diary.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc();
    boolean existsByTitle(String title);

    List<DiaryEntity> findByCategory(Category category);

    @Query("SELECT d FROM DiaryEntity d ORDER BY d.createdAt DESC")
    Optional<DiaryEntity> findTopByOrderByCreatedAtDesc();
}
