package com.duwei.commonsspringbootstarter.base;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();

    }

    public BusinessException(String message) {
        super("BusinessException:"+message);

    }

}
