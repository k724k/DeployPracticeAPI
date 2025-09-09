package com.example.deploypracticeapi.board;

import com.example.deploypracticeapi.model.Pagenation;

import java.util.List;

public interface BoardService {

    List<BoardModelDto.BoardModel> getBoardList(Pagenation pagenation);

    BoardModelDto.BoardModel getBoardDetail(Long boardID);

    String insertBoard(BoardModelDto.UpdateBoardModel updateBoardModel);

    String updateBoard(Long boardID, BoardModelDto.UpdateBoardModel updateBoardModel);

    String deleteBoard(Long boardID);
}
