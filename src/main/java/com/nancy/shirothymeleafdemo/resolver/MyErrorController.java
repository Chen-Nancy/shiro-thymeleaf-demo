package com.nancy.shirothymeleafdemo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/31 23:32
 */
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private static final int ERROR_CODE = 404;
    private final ErrorAttributes errorAttributes;

    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("error");
        int status = response.getStatus();
        if (status == ERROR_CODE) {
            view.addObject("message", "资源未找到");
            return view;
        }
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        String message = attr.get("message").toString();
        view.addObject("message", message);
        return view;
    }
}
