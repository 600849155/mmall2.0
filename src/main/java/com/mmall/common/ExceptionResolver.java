package com.mmall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geely
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(request.getRequestURI() + " Exception：", ex);
        ModelAndView modelAndView = new ModelAndView(new MappingJacksonJsonView());
//        ModelAndView modelAndView = new ModelAndView();
//        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", ResponseCode.ERROR.getCode());
        modelAndView.addObject("msg", "接口异常,详情请查看日志中的异常信息");
        modelAndView.addObject("data",ex.toString());
        return modelAndView;
    }
}
