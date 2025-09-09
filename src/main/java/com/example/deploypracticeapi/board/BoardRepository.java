package com.example.deploypracticeapi.board;

import com.example.deploypracticeapi.jpa.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    Optional<BoardEntity> findByBoardIDAndDelYn(Long boardID, String delYn);

    Optional<BoardEntity> findByBoardName(String boardName);


}
