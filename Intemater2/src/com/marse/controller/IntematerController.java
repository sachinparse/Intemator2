package com.marse.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.marse.crypto.CryptoUtil;
import com.marse.daofactory.DAOFactory;
import com.marse.model.User;
import com.marse.user.UserDAO;

@Controller
public class IntematerController {

	// login view
	@RequestMapping(value="login.form", method=RequestMethod.POST)
	public ModelAndView loginPage(){
		return new ModelAndView("login");
	}
	
	// authentication logic
	@RequestMapping(value="LoginAuthenticate.form", method=RequestMethod.POST)
	public ModelAndView validateUser(@RequestParam int userId,
									 @RequestParam String password,
									 HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
									 InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
									 IOException{
		
		ModelAndView objModel=new ModelAndView(); 

		// Checking authentication
		
		UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
		
		boolean loginStatus=objUserDAO.authenticateUser(userId, password);
		
		if (loginStatus==true) {
			
			User objUser=new User();
			
			objUser=objUserDAO.getUser(userId);  //  fetched whole record of the current user
			
			HttpSession objSession=request.getSession(false);
			objSession.setAttribute("objUser", objUser);   // kept current user object into HttpSession for session handling
			
			objModel.setViewName("register");
		} else {

			String message="Invalid User Id or password<br> Please check..!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
		}
		
		return objModel;
	}
	
	
	// creating new contact of Customer
	
	@RequestMapping(value="register.form", method=RequestMethod.POST)
	public @ResponseBody ModelAndView createNewCustomer( HttpServletRequest request){
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");

				// bussiness logic
				
				return objModel;    
		        
		        
			}
			
		}// end of else
	}
	
	// new user
	
	@RequestMapping(value="newUser.form", method=RequestMethod.POST)
	public @ResponseBody ModelAndView createNewUser(@RequestParam String firstName,
													@RequestParam String lastName,
													@RequestParam String gender,
													@RequestParam String userMobile1,
													@RequestParam(required=false) String userMobile2,
													@RequestParam String userPassword,
													@RequestParam String userEmail,
													@RequestParam String roll,
													HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");

				// bussiness logic
				
				User objUser=new User();
				
				objUser.setFirstName(firstName);
				objUser.setLastName(lastName);
				objUser.setRoll(roll);
				objUser.setUserEmail(userEmail);
				objUser.setUserMobile1(userMobile1);
				objUser.setUserMobile2(userMobile2);
				objUser.setUserGender(gender);
				objUser.setUserPassword(new CryptoUtil().encrypt(userPassword));
				
				UserDAO objUserDAO=DAOFactory.getInstanceOfUser();

				objUserDAO.addUser(objUser);
				
				// fetching list of all the users
				
				List<User> listOfUser=objUserDAO.listOfUser();
				
				objModel.addObject("listOfUser", listOfUser);
				objModel.setViewName("showUser");
				return objModel;    
		        
		        
			}
			
		}// end of else
	}
	
	// fetching list of users
	@RequestMapping(value="showUser.form", method=RequestMethod.GET)
	public @ResponseBody ModelAndView showUsers(){
	
		ModelAndView objModel=new ModelAndView();
		
		UserDAO objUserDAO=DAOFactory.getInstanceOfUser();

		// fetching list of all the users
		List<User> listOfUser=objUserDAO.listOfUser();
		
		
		/*Iterator<User> it=listOfUser.iterator();
		
		User u=new User();
		while(it.hasNext()){
			
			u=it.next();
			System.out.println(u.getUserId());
			System.out.println(u.getFirstName()+" "+u.getLastName());
			System.out.println(u.getUserGender()+" "+u.getUserEmail());
		}*/
		
		
		objModel.addObject("listOfUser", listOfUser);
		objModel.setViewName("showUser");
		return objModel;
	}
	
}