package com.example.deploypracticeapi.board;

import com.example.deploypracticeapi.model.Pagenation;
import com.example.deploypracticeapi.model.ResponseDto;
import com.example.deploypracticeapi.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "Board")
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final ResponseUtil responseUtil;
    private final BoardService boardService;

    @Operation(summary = "게시판 목록 조회")
    @GetMapping("/list")
    public ResponseDto.ListResponseModel<BoardModelDto.BoardModel> getBoardList(
            @ParameterObject Pagenation pagenation
    ) {
        return responseUtil.getListResponse(boardService.getBoardList(pagenation));
    }

    @Operation(summary = "게시판 상세 조회")
    @GetMapping("/{boardID}")
    public ResponseDto.ResponseModel<BoardModelDto.BoardModel> getBoardDetail(
            @PathVariable("boardID") Long boardID
    ) {
        return responseUtil.getResponse(boardService.getBoardDetail(boardID));
    }

    @Operation(summary = "게시판 등록")
    @PostMapping("")
    public ResponseDto.ResponseModel<String> insertBoard(
            @Valid @RequestBody BoardModelDto.UpdateBoardModel updateBoardModel
    ) {
        return responseUtil.getResponse(boardService.insertBoard(updateBoardModel));
    }

    @Operation(summary = "게시판 수정")
    @PatchMapping("{boardID}")
    public ResponseDto.ResponseModel<String> editBoard(
            @PathVariable("boardID") Long boardID,
            @RequestBody @Validated BoardModelDto.UpdateBoardModel updateBoardModel

    ) {

        return responseUtil.getResponse(boardService.updateBoard(boardID,updateBoardModel));
    }

    @Operation(summary = "게시판 삭제")
    @DeleteMapping("{boardID}")
    public ResponseDto.ResponseModel<String> deleteBoard(
            @PathVariable("boardID") Long boardID
    ) {

        return responseUtil.getResponse(boardService.deleteBoard(boardID));
    }


}
