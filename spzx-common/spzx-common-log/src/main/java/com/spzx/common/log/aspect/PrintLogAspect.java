package com.spzx.common.log.aspect;

import com.alibaba.fastjson2.JSON;
import com.spzx.common.log.annotation.PrintLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class PrintLogAspect {

//    @Around("@annotation(com.spzx.common.log.annotation.PrintLog)")
    @Around("@annotation(printLog)") //使用形參類型當作切入點表達式, 執行時將目標方法的註解對象作為實參傳入
    public Object around(ProceedingJoinPoint joinPoint, PrintLog printLog) {
        //前置日誌打印
        Long start = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestParams = printLog.isPrintRequestParam()? JSON.toJSONString(joinPoint.getArgs()):" ";
        log.info("當前時間：{}， 訪問的模塊：{}， 藉口路徑{}， 請求方式{},請求參數：{}",
                new Date(), printLog.title(), request.getRequestURI(), request.getMethod(), requestParams
                );
        //目標方法執行
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        String requestData = printLog.isPrintResponseData()?JSON.toJSONString(result):" ";
        log.info("當前時間：{}， 響應結果：{}， 耗時{}",
                new Date(), requestData, System.currentTimeMillis() - start

                );

        return result;
        //後置日誌打印
    }
}
