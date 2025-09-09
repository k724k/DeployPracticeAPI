package com.example.deploypracticeapi.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

@Log4j2
@Getter
@AllArgsConstructor
@ToString
public enum ErrorModel {

    INTERNAL_SERVER(500000, "에러가 발생하였습니다.\\n잠시 후 다시시도해주세요.", HttpStatus.INTERNAL_SERVER_ERROR),
    CUSTOM_STRING(500001, "", HttpStatus.BAD_REQUEST); // ResponseUtil.errorResponseStrCustom()에서 사용

    private final Integer code;
    private final String message;
    private final HttpStatus status;


    /**
     * code 값으로 에러 조회
     * @param code - 코드값
     * @return
     */
    public static ErrorModel findCode(@NotNull Integer code) {
        for (ErrorModel em: values()) {
            if(em.getCode().equals(code)) {
                return em;
            }
        }
        return INTERNAL_SERVER;
    }

    public static ErrorModel findCode(@NotNull String code) {
        try {
            return findCode(Integer.parseInt(code));
        } catch (Exception e) {
            log.error("[ ErrorModel.findCode(String) ] ::: " + e.getMessage());
        }
        return INTERNAL_SERVER;
    }
}
