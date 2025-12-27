package com.example.deploypracticeapi.board;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

public class BoardModelDto {

    @Getter
    public static class UpdateBoardModel {

        @NotBlank(message = "boardName 은 필수값 입니다.")
        @Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        private String boardName;

        @Schema(description = "게시판 제목")
        private String title;

        @Schema(description = "게시판 내용")
        private String content;

        @Schema(description = "게시판 부가 설명1")
        @Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        private String subDescr1;

        @Schema(description = "게시판 부가 설명2")
        @Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        private String subDescr2;

        @Schema(description = "게시판 부가 설명3")
        @Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        private String subDescr3;

        @NotBlank(message = "useYn 은 필수값 입니다.")
        @Pattern(regexp = "^[YN]$", message = "Y,N 만 입력가능합니다.")
        @Schema(description = "게시판 사용여부", allowableValues = "Y,N", example = "Y")
        private String useYn;

    }

    @Data
    public static class BoardModel {

        @Schema(description = "게시판 ID")
        private Long boardID;

        @Schema(description = "게시판 이름")
        private String boardName;

        @Schema(description = "게시판 제목")
        private String title;

        @Schema(description = "게시판 내용")
        private String content;

        @Schema(description = "게시판 부가 설명1")
        private String subDescr1;
        @Schema(description = "게시판 부가 설명2")
        private String subDescr2;
        @Schema(description = "게시판 부가 설명3")
        private String subDescr3;

        @Schema(description = "게시판 사용여부")
        private String useYn;
        @Schema(description = "게시판 삭제여부")
        private String delYn;

        @Schema(description = "등록 일시")
        private String regDate;
        @Schema(description = "수정 일시")
        private String updDate;
    }

}

