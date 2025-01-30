package com.test.servicea.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ServiceAController {

	@Autowired
	private RestTemplate restTemplate;

	
	@GetMapping("/callServiceA")
	public String callServiceA() {
		return "Response from Service A";
	}

	@GetMapping("/callServiceB")
	public String callServiceB(HttpServletRequest request) {
		// Extract Trace ID from the request attribute (set by the TraceIdFilter)
		String traceId = (String) request.getAttribute("X-Trace-Id");

		// If no trace ID is found, generate a new one
		if (traceId == null) {
			traceId = UUID.randomUUID().toString();
		}

		// Prepare headers and set the trace ID
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Trace-Id", traceId);

		// Create an HttpEntity with the trace ID in the headers
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Make the GET request to ServiceB, passing the trace ID in the headers
		String response = restTemplate.exchange("http://serviceb/bringHim", HttpMethod.GET, entity, String.class).getBody();
		return "Response from Service B: " + response;
	}

}
