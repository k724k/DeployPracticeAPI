package com.example.deploypracticeapi.memo;

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
@Tag(name = "Memo")
@RequiredArgsConstructor
@RestController
@RequestMapping("/memo")
public class MemoController {

    private final ResponseUtil responseUtil;
    private final MemoService memoService;


    @Operation(summary = "메모 목록 조회")
    @GetMapping("/list")
    public ResponseDto.ListResponseModel<MemoModelDto.MemoModel> getMemoList(
            @ParameterObject Pagenation pagenation
    ) {
        return responseUtil.getListResponse(memoService.getMemoList(pagenation));
    }

    @Operation(summary = "메모 상세 조회")
    @GetMapping("/{memoID}")
    public ResponseDto.ResponseModel<MemoModelDto.MemoModel> getMemoDetail(
            @PathVariable("memoID") Long memoID
    ) {
        return responseUtil.getResponse(memoService.getMemoDetail(memoID));
    }

    @Operation(summary = "메모 등록")
    @PostMapping("")
    public ResponseDto.ResponseModel<String> insertMemo(
            @Valid @RequestBody MemoModelDto.UpdateMemoModel updateMemoModel
    ) {
        return responseUtil.getResponse(memoService.insertMemo(updateMemoModel));
    }

    @Operation(summary = "메모 수정")
    @PatchMapping("{memoID}")
    public ResponseDto.ResponseModel<String> editMemo(
            @PathVariable("memoID") Long memoID,
            @RequestBody @Validated MemoModelDto.UpdateMemoModel updateMemoModel

    ) {
        return responseUtil.getResponse(memoService.updateMemo(memoID, updateMemoModel));
    }

    @Operation(summary = "메모 삭제")
    @DeleteMapping("{memoID}")
    public ResponseDto.ResponseModel<String> deleteMemo(
            @PathVariable("memoID") Long memoID
    ) {

        return responseUtil.getResponse(memoService.deleteMemo(memoID));
    }


}
