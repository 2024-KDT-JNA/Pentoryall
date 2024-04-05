package com.pentoryall.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

    private Boolean success;
    private String message;
    private Object response;

    public CommonResponse(Boolean success) {
        this(success, null, null);
    }

    public CommonResponse(Boolean success, String message) {
        this(success, message, null);
    }

    public CommonResponse(Boolean success, Object response) {
        this(success, null, response);
    }

    public CommonResponse(Boolean success, String message, Object response) {
        this.success = success;
        this.message = message;
        this.response = response;
    }
}
