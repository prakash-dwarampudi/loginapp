package com.sample.loginapp.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@SuppressWarnings("serial")
@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=/bin/logout" })
public class LogoutServlet extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("login-token".equalsIgnoreCase(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					request.getSession().invalidate();
					break;
				}
			}
		}
	}

}
