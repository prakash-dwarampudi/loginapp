package com.sample.loginapp.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;


@Model(adaptables=SlingHttpServletRequest.class)
public class LoggedInUser {
	
	private String name;
	
	@Inject
	SlingHttpServletRequest request;
	
	@PostConstruct
	public void postConstruct(){
		try {
			ResourceResolver resResolver = request.getResourceResolver();
			Session session = resResolver.adaptTo(Session.class);
			String userId = session.getUserID();
			
			UserManager userManager = resResolver.adaptTo(UserManager.class);
			User user = (User) userManager.getAuthorizable(userId);
			
			this.name = user.getID();
		} catch (RepositoryException e) {
			name = e.getMessage();
			e.printStackTrace();
		}
		
	}

	public String getName() {
		return name;
	}

}
