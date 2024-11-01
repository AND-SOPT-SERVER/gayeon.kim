package org.sopt.diary.repository;

import java.util.Optional;
import org.sopt.diary.domain.SoptMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<SoptMember, Long> {
    Optional<SoptMember> findByPasswordAndUsername(String password, String username);
}
