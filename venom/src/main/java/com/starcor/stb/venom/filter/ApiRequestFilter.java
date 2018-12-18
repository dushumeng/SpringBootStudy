package com.starcor.stb.venom.filter;

import com.starcor.stb.venom.api.ApiHeader;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiRequestFilter extends HandlerInterceptorAdapter {

    private static final String MD5_KEY = "43EepDgproHiZkavZsbRlHLxKPfOxikRQSfmbp1eNurCw3rzqghlfLCFIAE6muPh";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        String servletPath = request.getServletPath();
        // TODO: 2018/12/17 检查输入参数
        if (StringUtils.isNotEmpty(servletPath) && servletPath.startsWith("/api")) {
            ApiHeader apiHeader = ApiHeader.parse(request);
            if (!apiHeader.isValid()){
                response.setContentType("application/json");
                response.getOutputStream().write("{\"code\":-3,\"msg\":\"request is not valid\"}".getBytes());
                return false;
            }
            String midSign = DigestUtils.md5Hex(apiHeader.timestamp + MD5_KEY);
            if (!StringUtils.equals(midSign, apiHeader.sign)) {
                response.setContentType("application/json");
                response.getOutputStream().write("{\"code\":-1}".getBytes());
                return false;
            }
            request.setAttribute("api_header", apiHeader);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }
}
