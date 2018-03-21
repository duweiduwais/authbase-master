package com.example.zuul.logging;

import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogsRepository extends BaseRepository<AccessLogs,Long>{
}
