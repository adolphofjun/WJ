package com.pluten.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncoidingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
// 响应类型
        response.setHeader("Access-Control-Allow-Methods","GET，POST，OPTIONS，PUT，DELETE,UPDATE");
// 响应头设置
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
      /*
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");*/
        System.out.println("过撸起=============");
        chain.doFilter(req, res);
    }

    public void destroy() {

    }
}
