package com.sample.loginapp.core.servlets;

import java.io.IOException;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/permcheck",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET })
public class PermissionCheck extends SlingSafeMethodsServlet {

	private static final Logger logger = LoggerFactory.getLogger(PermissionCheck.class);

	@Override
	protected void doHead(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		try {
			String uri = request.getParameter("uri");
			Session session = request.getResourceResolver().adaptTo(Session.class);
			try {
				session.checkPermission(uri, Session.ACTION_READ);
				response.setStatus(SlingHttpServletResponse.SC_OK);
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(SlingHttpServletResponse.SC_FORBIDDEN);
			}

		} catch (Exception e) {
			logger.error("Error in AuthChecker servlet", e);
		}

	}
}
