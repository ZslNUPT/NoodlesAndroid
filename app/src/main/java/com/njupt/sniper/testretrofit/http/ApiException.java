package com.njupt.sniper.testretrofit.http;

/**
 * author：Zsl
 * date：2016/8/23
 */
public class ApiException extends RuntimeException {

    public static final String INVALID_TOKEN = "invalid_token";

    public ApiException(String error,String error_description) {
        this(getApiExceptionMessage(error,error_description));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     */
    private static String getApiExceptionMessage(String error,String error_description){
        String message = "";
        switch (error) {
            case INVALID_TOKEN:
                message = "认证无效";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }
}

