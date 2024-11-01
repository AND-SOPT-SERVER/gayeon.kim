package org.sopt.diary.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.diary.domain.Category;
import org.sopt.diary.domain.DiaryEntity;
import org.sopt.diary.domain.SoptMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    @Query("SELECT d FROM DiaryEntity d WHERE d.member = :member ORDER BY d.createdAt DESC")
    List<DiaryEntity> findTop10ByOrderByCreatedAtDesc(SoptMember member);
    boolean existsByTitle(String title);

    List<DiaryEntity> findByCategoryAndMember(Category category, SoptMember member);

    @Query("SELECT d FROM DiaryEntity d WHERE d.member = :member ORDER BY d.createdAt DESC")
    Optional<DiaryEntity> findTopByOrderByCreatedAtDesc(SoptMember member);
}
