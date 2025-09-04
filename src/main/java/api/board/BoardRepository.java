package api.board;

import api.jpa.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    Optional<BoardEntity> findByBoardIDAndDelYn(Long boardID, String delYn);

    Optional<BoardEntity> findByBoardName(String boardName);


}
