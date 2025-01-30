package com.test.serviceb.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ServiceBController {

    @GetMapping("/bringHim")
    public String getServiceBResponse(HttpServletRequest request) {
    	String traceId = (String) request.getAttribute("X-Trace-Id");
        // Use traceId for logging, tracking, etc.
        return "Received trace ID: " + traceId;
    }
}
