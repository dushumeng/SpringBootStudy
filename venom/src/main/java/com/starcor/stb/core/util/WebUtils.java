package com.starcor.stb.core.util;

import com.starcor.stb.venom.api.ApiHeader;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class WebUtils {

    public static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    /**
     * 获取请求对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取回应对象
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取上下文
     */
    public static ServletContext getContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    /**
     * 设置cookie的值
     */
    public static void addCookie(String name, String value) {
        HttpServletResponse response = getResponse();
        Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie的值
     */
    public static void addCookie(String name, String value, int maxAge) {
        HttpServletResponse response = getResponse();
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的值
     */
    public static String getCookie(String name) {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 获取API头部信息
     */
    public static ApiHeader getApiHeader() {
        return (ApiHeader) getRequest().getAttribute("apiHeader");
    }

    /**
     * list参数缓存：保存
     */
    public static void saveFlash(Object vo) {
        getSession().setAttribute("_flashparam", vo);
    }

    /**
     * list参数缓存：获取
     */
    public static Object getFlash() {
        return getSession().getAttribute("_flashparam");
    }

    /**
     * list参数缓存：写到重定向页面
     */
    public static void outputFlash() {
        RequestContextUtils.getOutputFlashMap(WebUtils.getRequest()).put("bean", getFlash());
        getSession().removeAttribute("_flashparam");
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
