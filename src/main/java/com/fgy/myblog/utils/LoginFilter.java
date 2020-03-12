package com.fgy.myblog.utils;

import com.fgy.myblog.bean.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/back/*")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //1.把request强转为HttpServletRequest类型
        HttpServletRequest req= (HttpServletRequest) request;
        //2.获取请求路径
        String uri=req.getRequestURI();
        //3.获取session值
        HttpSession session=req.getSession();
        UserInfo user= (UserInfo) session.getAttribute("loginInfo");

        //4.判断请求路径是否是登录请求
        if(uri.contains("/back/index")){
            chain.doFilter(request,response);
        }else if (user!=null){//判断是否已经登录
                chain.doFilter(request,response);
            }else{
                req.getRequestDispatcher("/back/login").forward(request,response);
            }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
