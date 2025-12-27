package com.example.deploypracticeapi.memo;

import com.example.deploypracticeapi.jpa.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity,Long> {

    Optional<MemoEntity> findByMemoIDAndDelYn(Long memoId, String delYn);

    Optional<MemoEntity> findByTitleAndDelYn(String title, String delYn);
}
