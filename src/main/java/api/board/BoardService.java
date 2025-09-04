package api.board;

import api.model.Pagenation;

import java.util.List;

public interface BoardService {

    List<BoardModelDto.BoardModel> getBoardList(Pagenation pagenation);

    BoardModelDto.BoardModel getBoardDetail(Long boardID);

    String insertBoard(BoardModelDto.UpdateBoardModel updateBoardModel);

    String updateBoard(Long boardID, BoardModelDto.UpdateBoardModel updateBoardModel);

    String deleteBoard(Long boardID);
}
