package nl.project.controllers;

import java.util.Collection;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		try {
			UserDetails userDetails = getUserDetails();
			Collection<?> autho = userDetails.getAuthorities();
			StringBuilder roles = new StringBuilder();
			for (Object auth : autho) {
				roles.append(auth);
				roles.append(", ");
			}
			
			model.addAttribute("username", userDetails.getUsername());
			model.addAttribute("roles", roles);
			
			
			
		} catch (ClassCastException e) {
		}
		return "index";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userpage(Model model) {
		UserDetails userDetails = getUserDetails();
		Collection<?> autho = userDetails.getAuthorities();
		StringBuilder roles = new StringBuilder();
		for (Object auth : autho) {
			roles.append(auth);
			roles.append(", ");
		}
		
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("roles", roles);
		
		return "user";
	}

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String adminpage(Model model) {
		UserDetails userDetails = getUserDetails();
		Collection<?> autho = userDetails.getAuthorities();
		StringBuilder roles = new StringBuilder();
		for (Object auth : autho) {
			roles.append(auth);
			roles.append(", ");
		}
		
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("roles", roles);
		return "admin";
	}

	private UserDetails getUserDetails() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
		
//		System.err.println(userDetails.getPassword());
//		System.err.println(userDetails.getUsername());
//		System.err.println(userDetails.isEnabled());
//		Collection<?> autho = userDetails.getAuthorities();
//		for (Object auth : autho) {
//			System.err.println(auth);
//		}

	}

}
