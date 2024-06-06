package com.rs.fer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;

@Controller
public class UpdateProfileController {
	@Autowired
	FERService ferService;

	@RequestMapping(value = "/showNameInfo", method = RequestMethod.POST)
	public ModelAndView displayNameInfo(HttpServletRequest request) {
		// 1.Get the input
		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		User user = ferService.getUser(userId);
		session.setAttribute("user", user);
		return new ModelAndView("NameInfo");
	}

	@RequestMapping(value = "/showContactInfo", method = RequestMethod.POST)
	public ModelAndView displayContactInfo(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// call the service by passing the input
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));

		return new ModelAndView("ContactInfo");
	}

	@RequestMapping(value = "/showAddressInfo", method = RequestMethod.POST)
	public ModelAndView displayAddressInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobile"));

		Address address = user.getAddress();
		session.setAttribute("address", address);
		return new ModelAndView("AddressInfo");
	}

	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ModelAndView displayEditExpense(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Address address = user.getAddress();

		address.setLine1(request.getParameter("line1"));
		address.setLine2(request.getParameter("line2"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setPinCode(request.getParameter("pinCode"));
		address.setCountry(request.getParameter("country"));
		return new ModelAndView("Review");
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// 2.Call the service by passing the input
		boolean isUpdateProfile = ferService.updateUser(user);

		// 3.Show the status
		if (isUpdateProfile) {
			session.setAttribute("status", "Profile Updated successfully.....! ");
		} else {
			session.setAttribute("status", "Profile Update is failed.....!");
		}
		return new ModelAndView("Dashboard");
	}
}
