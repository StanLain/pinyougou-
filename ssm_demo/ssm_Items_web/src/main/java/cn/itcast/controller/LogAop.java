package cn.itcast.controller;

import cn.itcast.pojo.SysLog;
import cn.itcast.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * AOP切面类完成日志记录
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date beginTime;// 方法执行开始时间
    private Method method;//执行的方法对象（此对象是为了在后置通知中获取访问方法的URL）
    private Class clazz;//执行的方法（有对象才能获取对象类上的URL）

    @Pointcut("execution(* cn.itcast.controller.*.* (..))")
    public void pointCut(){};

    @Before("pointCut()")
    public void getBefore(JoinPoint joinPoint){
        //获取执行方法的类
        clazz = joinPoint.getTarget().getClass();
        //获取方法对象
        String methodName = joinPoint.getSignature().getName();
        //获取当前执行方法的参数
        Object[] args = joinPoint.getArgs();
        if (args!=null){
            Class[] cla=new Class[args.length];
            for (int i=0;i<args.length;i++){
                cla[i]= (Class) args[i];
                try {
                    method = clazz.getMethod(methodName,cla);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                method=clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        beginTime = new Date();
    }

    @After("pointCut()")
    public void getAfter(JoinPoint joinPoint){
        Long time=new Date().getTime()-beginTime.getTime();//执行时长
        String url=null;//获取url
        if (clazz!=null&&method!=null&&clazz!=LogAop.class){
            RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            String[] value = annotation.value();//方法上的url
            RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
            String[] value1 = annotation1.value();
            url=value[0]+value1[0];
        }
        //获取操作用户名
        SecurityContext securityContext= SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        String username = user.getUsername();
        //获取操作用户ip地址
        String ip = request.getRemoteAddr();
        //将所有信息封装到SysLog对象中
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setMethod(method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(beginTime);
        sysLog.setIp(ip);
        try {
            sysLogService.saveLog(sysLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
