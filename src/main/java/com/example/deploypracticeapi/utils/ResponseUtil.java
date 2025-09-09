package com.example.deploypracticeapi.utils;

import com.example.deploypracticeapi.model.ErrorModel;
import com.example.deploypracticeapi.model.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResponseUtil {
    /**
     * 에러 발생
     * @param errorModel
     * @return
     */
    public ResponseDto.ResponseModel<Object> errorResponse(ErrorModel errorModel) {
        ResponseDto.ResponseModel<Object> response = new ResponseDto.ResponseModel<Object>();
        response.error(errorModel.getCode(), errorModel.getMessage());
        return response;
    }

    /**
     * 에러 발생 (result 커스텀)
     * @param errorModel
     * @param result
     * @return
     */
    public <T> ResponseDto.ResponseModel<T> errorResponseCustom(ErrorModel errorModel, T result) {
        ResponseDto.ResponseModel<T> response = new ResponseDto.ResponseModel<T>();
        response.error(errorModel.getCode(), errorModel.getMessage(), result);
        return response;
    }

    /**
     * 에러 발생 (String 커스텀)
     * @param errMsg
     * @return
     */
    public <T> ResponseDto.ResponseModel<T> errorResponseStrCustom(String errMsg) {
        ResponseDto.ResponseModel<T> response = new ResponseDto.ResponseModel<T>();
        response.error(ErrorModel.CUSTOM_STRING.getCode(), errMsg, null);
        return response;
    }

    /**
     * 성공적인 응답 처리
     * @param result
     * @return
     * @param <T>
     */
    public <T> ResponseDto.ResponseModel<T> getResponse(T result) {
        ResponseDto.ResponseModel<T> response = new ResponseDto.ResponseModel<>();
        response.result(result);
        return response;
    }

    /**
     * (null) 응답처리
     * @return
     * @param <T>
     */
    public <T> ResponseDto.ResponseModel<T> getResponse() {
        return new ResponseDto.ResponseModel<>();
    }

    /**
     * 리스트 응답 처리
     *
     * @param list
     * @param totalCnt
     * @param <T>
     * @return
     */
    public <T> ResponseDto.ListResponseModel<T> getListResponse(List<T> list, Integer totalCnt) {
        return new ResponseDto.ListResponseModel(new ResponseDto.ListModel(list, totalCnt));
    }

    /**
     * 리스트 응답 처리
     * @param list
     * @return
     * @param <T>
     */
    public <T> ResponseDto.ListResponseModel<T> getListResponse(List<T> list) {
        if(list == null) {
            list = new ArrayList<>();
        }
        return new ResponseDto.ListResponseModel(new ResponseDto.ListModel(list, list.size()));
    }

    /**
     * 리스트 응답 처리
     * @param listModel
     * @return
     * @param <T>
     */
    public <T>ResponseDto.ListResponseModel<T> getListResponse(ResponseDto.ListModel<T> listModel) {
        return new ResponseDto.ListResponseModel<>(listModel);
    }

    /**
     * 빈리스트 응답 처리
     * @return
     * @param <T>
     */
    public <T> ResponseDto.ListResponseModel<T> getListResponse() {
        return new ResponseDto.ListResponseModel(new ResponseDto.ListModel(new ArrayList<>(), 0));
    }
}