package com.nancy.shirothymeleafdemo.resolver;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * @author chen
 * @date 2020/6/3 1:02
 */
@ControllerAdvice
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(MyHandlerExceptionResolver.class);

    @ExceptionHandler(BindException.class)
    public ModelAndView bindExceptionResolver(BindException e) {
        LOG.error(e.getMessage(), e);
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "参数错误");
        return view;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        LOG.error(e.getMessage(), e);
        ModelAndView view = new ModelAndView();
        if (e instanceof UnknownAccountException) {
            view.addObject("message", "用户不存在");
            view.setViewName("login");
        } else if (e instanceof IncorrectCredentialsException) {
            view.addObject("message", "密码错误");
            view.setViewName("login");
        } else if (e instanceof UnauthorizedException) {
            view.addObject("message", "权限不足");
            view.setViewName("error");
        } else if (e instanceof ConstraintViolationException) {
            view.addObject("message", "参数错误");
            view.setViewName("error");
        } else {
            view.addObject("message", e.getMessage());
            view.setViewName("error");
        }
        return view;
    }
}
