package cn.itcast.service.imp;

import cn.itcast.mapper.SysLogMapper;
import cn.itcast.pojo.SysLog;
import cn.itcast.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImp implements SysLogService {

    @Autowired
   private SysLogMapper sysLogMapper;
    public void saveLog(SysLog sysLog)throws Exception {
        sysLogMapper.saveLog(sysLog);
    }
}
