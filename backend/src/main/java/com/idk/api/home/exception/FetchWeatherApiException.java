package com.idk.api.home.exception;

import com.idk.api.common.exception.BusinessException;

public class FetchWeatherApiException extends BusinessException {
    public FetchWeatherApiException() {
        super("기상청 단기예보 Open API 예외가 발생하였습니다.");
    }
}
