package com.automation.home.others;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Utility {
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servReq = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return servReq.getRequest(); 
	}

}
