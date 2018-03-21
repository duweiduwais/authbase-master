package com.example.zuul.logging;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private  AccessLogsRepository accessLogsRepository;

    @Override
    public boolean addAccessLog(String context, String accessUser) {
        if(!Strings.isNullOrEmpty(context) && !Strings.isNullOrEmpty(accessUser)){
            AccessLogs accessLogs = new AccessLogs(context,accessUser);
            AccessLogs accessLogsR = accessLogsRepository.save(accessLogs);
            return accessLogs != null;
        }
        return false;
    }
}
