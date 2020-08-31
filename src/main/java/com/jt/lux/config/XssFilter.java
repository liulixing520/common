package com.jt.lux.config;

import com.alibaba.fastjson.JSON;
import com.jt.lux.util.GenericDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebFilter(
        filterName = "xssFilter",
        urlPatterns = "/*",
        dispatcherTypes = DispatcherType.REQUEST
)
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // no init operation
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        XssAndSqlHttpServletRequestWrapper xssRequest=new XssAndSqlHttpServletRequestWrapper(
                request);
        String method = ((HttpServletRequest) request).getMethod();
        String param = "";
        if ("POST".equalsIgnoreCase(method)) {
            param = this.getBodyString(xssRequest.getReader());
            if(StringUtils.isNotBlank(param)){
                if(xssRequest.checkXSSAndSql(param)){
                    servletResponse.setCharacterEncoding("UTF-8");
                    servletResponse.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = servletResponse.getWriter();

                    ResponseEntity<?> s =  GenericDataResponse.ng("入参异常");
                    out.write(JSON.toJSONString(s.getBody()));
                    return;
                }
            }
        }
        if (xssRequest.checkParameter()) {
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter out = servletResponse.getWriter();

            ResponseEntity<?> s =  GenericDataResponse.ng("入参异常");
            out.write(JSON.toJSONString(s.getBody()));
            return;
        }
        log.info(xssRequest.getRequestURI());
        filterChain.doFilter(xssRequest, servletResponse);


    }

    // 获取request请求body中参数
    public static String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;

    }

    @Override
    public void destroy() {
        // no destroy operation
    }
}
