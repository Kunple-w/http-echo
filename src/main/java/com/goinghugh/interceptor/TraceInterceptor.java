package com.goinghugh.interceptor;

import com.goinghugh.constant.Constants;
import com.goinghugh.utils.TraceLogUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * trace id 拦截器
 *
 * @author yongxu wang
 * @date 2019-05-20 15:14
 **/
@Component
public class TraceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(Constants.LOG_TRACE_ID, TraceLogUtils.genTraceId());
        response.setHeader(Constants.HTTP_HEADER_TRACE_ID, MDC.get(Constants.LOG_TRACE_ID));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        MDC.clear();
    }
}
