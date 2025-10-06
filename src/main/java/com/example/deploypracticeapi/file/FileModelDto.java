package com.example.deploypracticeapi.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class FileModelDto {

    @Data
    public static class FileModel{

        @Schema(description = "파일 ID")
        private Long fileID;

        @Schema(description = "파일 제목")
        private String title;

        @Schema(description = "파일 내용")
        private String content;

        @Schema(description = "파일 url")
        private String url;

        @Schema(description = "파일 삭제 여부")
        private String delYn;

        @Schema(description = "등록 일시")
        private String regDate;
        @Schema(description = "수정 일시")
        private String updDate;

    }

    @Getter
    @Setter
    public static class UpdateFileModel{

        @Schema(description = "파일 제목")
        private String title;

        @Schema(description = "파일 내용")
        private String content;

        @Schema(description = "업로드할 파일")
        private MultipartFile file;

    }
}
