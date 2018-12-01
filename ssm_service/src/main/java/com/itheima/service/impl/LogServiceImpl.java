package com.itheima.service.impl;

import com.itheima.dao.LogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public void saveLog(SysLog log) {
        logDao.saveLog(log);
    }
}
