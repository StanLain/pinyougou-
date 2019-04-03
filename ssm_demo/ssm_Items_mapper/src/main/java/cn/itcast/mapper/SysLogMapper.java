package cn.itcast.mapper;

import cn.itcast.pojo.SysLog;

public interface SysLogMapper {

    /**
     * 将封装好的通知类信息 存入数据库
     * @param sysLog
     * @throws Exception
     */
    void saveLog(SysLog sysLog)throws Exception;
}
