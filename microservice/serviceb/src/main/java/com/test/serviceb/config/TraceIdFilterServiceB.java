package com.test.serviceb.config;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceIdFilterServiceB implements Filter {

    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String traceId = httpRequest.getHeader(TRACE_ID_HEADER);

        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
        }
        // Set the Trace ID as a request attribute for further processing in the service
        httpRequest.setAttribute(TRACE_ID_HEADER, traceId);

        // Continue with the request processing
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}

