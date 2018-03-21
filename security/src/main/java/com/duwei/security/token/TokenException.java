package com.duwei.security.token;

import com.duwei.commonsspringbootstarter.base.BusinessException;

public class TokenException extends BusinessException {

    public TokenException() {
    }

    public TokenException(String message) {
        super("TokenException:"+message);

    }

}
