package com.itheima.service;

import com.itheima.domain.SysLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LogService {
    public void saveLog(SysLog log);
}
