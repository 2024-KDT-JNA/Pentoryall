package com.pentoryall.common.exception;

public class CustomException extends RuntimeException {

    String redirectUrl;

    public CustomException(String msg) {
        this(msg, "/");
    }
    public CustomException(String msg, String url) {
        super(msg);
        this.redirectUrl = url;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}