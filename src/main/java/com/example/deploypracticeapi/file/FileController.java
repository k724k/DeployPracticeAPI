package com.example.deploypracticeapi.file;

import com.example.deploypracticeapi.model.ResponseDto;
import com.example.deploypracticeapi.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Log4j2
@Tag(name = "Files")
@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {

    private final ResponseUtil responseUtil;
    private final FileService fileService;

    @Operation(summary = "파일 목록 조회")
    @GetMapping("/list")
    public ResponseDto.ListResponseModel<FileModelDto.FileModel> getFilesList() {
        return responseUtil.getListResponse(fileService.getFileList());
    }

    @Operation(summary = "파일 상세 조회")
    @GetMapping("/{fileID}")
    public ResponseDto.ResponseModel<FileModelDto.FileModel> getFileDetail(
            @PathVariable("fileID") Long fileID
    ) {
        return responseUtil.getResponse(fileService.getFileDetail(fileID));
    }

    @Operation(summary = "파일 등록")
    @PostMapping("")
    public ResponseDto.ResponseModel<String> insertFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        FileModelDto.UpdateFileModel updateFileModel = new FileModelDto.UpdateFileModel();
        updateFileModel.setTitle(title);
        updateFileModel.setContent(content);
        updateFileModel.setFile(file);

        return responseUtil.getResponse(
                fileService.insertFile(updateFileModel)
        );
    }

    @Operation(summary = "파일 삭제")
    @DeleteMapping("{fileID}")
    public ResponseDto.ResponseModel<String> deleteFile(
            @PathVariable("fileID") Long fileID
    ) {
        return responseUtil.getResponse(fileService.deleteFile(fileID));
    }

}
