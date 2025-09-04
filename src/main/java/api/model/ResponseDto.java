package api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ResponseDto {

    @Getter
    @NoArgsConstructor
    public static class ResponseModel<T> {
        @Schema(description = "결과 코드")
        private Integer code = 0;
        @Schema(description = "에러 메시지")
        private String message = "";
        private T result;

        /**
         * 에러 발생시
         * @param code
         * @param message
         * @param result
         */
        public void error(@NotNull Integer code, @NotNull String message, T result) {
            this.code = code;
            this.message = message;
            this.result = result;
        }

        /**
         * 에러 발생시
         * @param code
         * @param message
         */
        public void error(@NotNull Integer code, @NotNull String message) {
            error(code, message, this.result);
        }

        public void result(T result) {
            this.result = result;
        }
    }

    @Getter
    public static class ListResponseModel<T> {
        @Schema(description = "결과 코드")
        private Integer code = 0;
        @Schema(description = "에러 메시지")
        private String message = "";
        @Schema
        private ListModel<T> result;

        public ListResponseModel(ListModel<T> result) {
            this.result = result;
        }
    }

    @Getter
    public static class ListModel<T> {
        private List<T> list;
        @Schema(description = "총 개수")
        private Integer totalCnt;

        public ListModel(List<T> list, Integer totalCnt) {
            this.list = list;
            this.totalCnt = totalCnt;
        }

        public ListModel(List<T> list, Long totalCnt) {
            this.list = list;
            this.totalCnt = totalCnt.intValue();
        }
    }
}
