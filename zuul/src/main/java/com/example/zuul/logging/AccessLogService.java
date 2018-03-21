package com.example.zuul.logging;

public interface AccessLogService {

    boolean addAccessLog(String context, String accessUser);
}
