package com.course.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.course.server.utils.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
public class LogAspect {

    private static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.course.*..*Controller.*(..))")
    public void controllerPiontcut() {
    }

    @Before("controllerPiontcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        //日志编号
        MDC.put("UUID", UuidUtil.getShortUuid());

        //开始打印日志请求
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        //业务操作
        String nameC;
        if (name.contains("list") && name.contains("query")) {
            nameC = "查询";
        } else if (name.contains("save")) {
            nameC = "新增";
        } else if (name.contains("delete")) {
            nameC = "删除";
        } else if (name.contains("update")) {
            nameC = "修改";
        } else {
            nameC = "操作";
        }

        // 使用反射，获取业务名称
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            LOG.error("未获取到业务名称");
        } catch (SecurityException e) {
            LOG.error("获取业务名称失败", e);
        }

        LOG.info("----------------【{}】{}开始--------------------", businessName, nameC);
        LOG.info("请求地址：{} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法：{}.{}", signature.getDeclaringType(), name);
        LOG.info("远程地址：{}", request.getRemoteAddr());

        //打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0, l = args.length; i < l; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        //排除敏感字段
        String[] excludeProperties = {};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter mySimplePropertyPreFilter = filters.addFilter();
        mySimplePropertyPreFilter.addExcludes(excludeProperties);
        LOG.info("请求参数{}", JSONObject.toJSONString(arguments, mySimplePropertyPreFilter));

    }

    @Around("controllerPiontcut()")
    public Object doAroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        //排除敏感字段
        String[] excludeProperties = {"password"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter mySimplePropertyPreFilter = filters.addFilter();
        mySimplePropertyPreFilter.addExcludes(excludeProperties);
        LOG.info("返回结果{}", JSONObject.toJSONString(result, mySimplePropertyPreFilter));
        LOG.info("---------------------结束 耗时：{}s-----------------------", startTime - System.currentTimeMillis());
        return result;

    }
}

