package com.starcor.stb.venom.filter;

import com.google.gson.Gson;
import com.starcor.stb.venom.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(-1000)
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Autowired
    private Gson gson;

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        final String servletPath = httpServletRequest.getServletPath();
        if (servletPath.startsWith("/api/") || servletPath.startsWith("/apiold/")) {
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.code = ApiResponse.CODE.SERVER_ERROR.value;
                apiResponse.msg = "server error";
                httpServletResponse.getWriter().write(gson.toJson(apiResponse));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return new ModelAndView();
        }
        return null;
    }
}
