package com.app.BookEShopping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	// @ResponseBody
	public String showLoginPage() {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLoginPage(ModelMap model, @RequestParam String userid, @RequestParam String pswrd){
		
		RegisterClassService s=new RegisterClassService();
		String category=s.LoginValidation(userid,pswrd);
		System.out.println(category);
		if(category.equals("customer")){
			return "customerfirstpage";
		}
		else if(category.equals("admin")){
			return "adminfirstpage";
		}
		else{
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		

		
		
	}
}