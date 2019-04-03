package cn.itcast.service;

import cn.itcast.pojo.SysLog;

public interface SysLogService {

    /**
     * 将封装好的通知类信息 存入数据库
     * @param sysLog
     */
    void saveLog(SysLog sysLog)throws Exception ;
}
