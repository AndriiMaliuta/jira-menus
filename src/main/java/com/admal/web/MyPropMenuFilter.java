//package com.admal.web;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class MyPropMenuFilter implements Filter {
//    private static final Logger LOG = LoggerFactory.getLogger(MyPropMenuFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpServletResponse wrappedResp = new HttpServletResponseWrapper (response) {
//
//            @Override
//            public PrintWriter getWriter() throws IOException {
//                return super.getWriter();
//            }
//
//        };
//
//        String localAddr = request.getLocalAddr();
//        String remoteAddr = request.getRemoteAddr();
//        String remoteHost = request.getRemoteHost();
//        String serverName = request.getServerName();
//
//        LOG.warn(">>> localAddr " + localAddr);
//        LOG.warn(">>> remoteAddr " + remoteAddr);
//        LOG.warn(">>> remoteHost " + remoteHost);
//        LOG.warn(">>> serverName " + serverName);
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
