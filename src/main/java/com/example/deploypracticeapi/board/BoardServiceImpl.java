package com.example.deploypracticeapi.board;

import com.example.deploypracticeapi.jpa.BoardEntity;
import com.example.deploypracticeapi.model.Pagenation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BoardModelDto.BoardModel> getBoardList(Pagenation pagenation) {
        Pageable pageable = pagenation.generatePageable();

        Page<BoardEntity> boardPage = boardRepository.findAll(pageable);
        List<BoardEntity> boardEntities = boardPage.getContent();

        List<BoardModelDto.BoardModel> boardListModelList = new ArrayList<>();
        for (BoardEntity board : boardEntities) {
            BoardModelDto.BoardModel boardListModel = new BoardModelDto.BoardModel();
            boardListModel.setBoardID(board.getBoardID());
            boardListModel.setBoardName(board.getBoardName());
            boardListModel.setTitle(board.getTitle());
            boardListModel.setContent(board.getContent());
            boardListModel.setSubDescr1(board.getSubDescr1());
            boardListModel.setSubDescr2(board.getSubDescr2());
            boardListModel.setSubDescr3(board.getSubDescr3());
            boardListModel.setUseYn(board.getUseYn());
            boardListModel.setDelYn(board.getDelYn());
            boardListModel.setRegDate(board.getRegDate());
            boardListModel.setUpdDate(board.getUpdDate());
            boardListModelList.add(boardListModel);
        }

        return boardListModelList;
    }

    @Override
    public BoardModelDto.BoardModel getBoardDetail(Long boardID){

        BoardEntity entity = boardRepository.findByBoardIDAndDelYn(boardID, "N")
                .orElseThrow(() -> new IllegalArgumentException("boardID 를 확인 필요 boardID : " + boardID));

        return modelMapper.map(entity, BoardModelDto.BoardModel.class);
    }

    @Override
    public String insertBoard(BoardModelDto.UpdateBoardModel updateBoardModel){
        String resMsg = "";
        try {
        var checkBoardName = boardRepository.findByBoardName(updateBoardModel.getBoardName());

            if (checkBoardName.isPresent()) {
                throw new IllegalArgumentException("이미 존재하는 게시판 이름입니다: " + updateBoardModel.getBoardName());
            }

            BoardEntity entity = boardRepository.save(
                    BoardEntity.builder()
                            .boardName(updateBoardModel.getBoardName() != null ? updateBoardModel.getBoardName().trim() : null)
                            .title(updateBoardModel.getTitle() != null ? updateBoardModel.getTitle().trim() : null)
                            .content(updateBoardModel.getContent() != null ? updateBoardModel.getContent().trim() : null)
                            .subDescr1(updateBoardModel.getSubDescr1() != null ? updateBoardModel.getSubDescr1().trim() : null)
                            .subDescr2(updateBoardModel.getSubDescr2() != null ? updateBoardModel.getSubDescr2().trim() : null)
                            .subDescr3(updateBoardModel.getSubDescr3() != null ? updateBoardModel.getSubDescr3().trim() : null)
                            .useYn(updateBoardModel.getUseYn() != null ? updateBoardModel.getUseYn().trim() : null)
                            .build());

            resMsg = "SUCCESS";

        }catch (Exception e){
            log.info("insertBoard Exception ::::: " + e);
            resMsg = e.getMessage();
        }

        return resMsg;
    }

    @Override
    public String updateBoard(Long boardID, BoardModelDto.UpdateBoardModel updateBoardModel){

        String resMsg = "";
        try {
            BoardEntity entity = boardRepository.findByBoardIDAndDelYn(boardID, "N")
                    .orElseThrow(() -> new IllegalArgumentException("boardID 을 확인 필요 boardID : " + boardID));
            if (entity.getBoardName().equals(updateBoardModel.getBoardName())){
                throw new IllegalArgumentException("이미 존재하는 게시판 이름입니다: " + updateBoardModel.getBoardName());
            }

            entity.updateBoard(updateBoardModel);
            boardRepository.save(entity);
            resMsg = "SUCCESS";
        } catch (Exception e) {
            log.info("updateBoard Exception ::::: " + e);
            resMsg = e.getMessage();
        }
        return resMsg;
    }

    @Override
    public String deleteBoard(Long boardID){
        String resMsg = "";
        try {
            BoardEntity entity = boardRepository.findByBoardIDAndDelYn(boardID, "N")
                    .orElseThrow(() -> new IllegalArgumentException("boardID 을 확인 필요 boardID : " + boardID));
            entity.setDelYn();
            boardRepository.save(entity);
            resMsg = "SUCCESS";
        } catch (Exception e) {
            log.info("deleteBoard Exception ::::: " + e);
            resMsg = e.getMessage();
        }
        return resMsg;
    }

}
