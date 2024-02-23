package com.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ConsoleApp.CarRentalSystem.Register;
import ConsoleApp.CarRentalSystem.Validation;
import com.EnumDetails.*;
import com.ExceptionDetails.InvalidEmailIdException;
/**
 * The Account class represents user account details and provides methods for user registration.
 * This class allows users to register by providing necessary details such as user ID, password, role, etc.
 * It also includes validation for user inputs and email addresses.
 * 
 * @author Sri Prasanna D (Expleo)
 * @since 22 Feb 2024
 */
public class Account {
	int userid;
	private String password;
	private String role;
	private VisibilityStatus status;
	
	//Constructors
	public Account() {}
	public Account(int userid,String password,String role) {
		this.userid=userid;
		this.password=password;
		this.role=role;
		this.status=VisibilityStatus.ACTIVE;
	}
	
	public Account(int userid2) {
		this.userid=userid2;
	}
	
	//Getters and setters
	public int getUserid() {
		return userid;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	public String getStatus() {
		return status.toString();
	}
	public void setStatus(VisibilityStatus status) {
		this.status = status;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	static Register reg=null;//Instance of Register class
     static Scanner sc = new Scanner(System.in);
     
     String ANSI_RESET = "\u001B[0m";
     String ANSI_RED = "\u001B[31m";

    public void registerUser() { //Method to register a user
    	int custid=0;
    		System.out.print("\n\t\t\t----Please enter the following User details-----");
    		 System.out.print("\n\tEnter user ID:");
    		    while (!sc.hasNextInt()) {
    		    	 System.out.println(ANSI_RED + "\t\tInvalid input! Please enter a numeric value for user ID." + ANSI_RESET);
    		    	 System.out.print("\tPlease Re-enter the user id:");
    		    	 sc.next(); // Consume the invalid input
    		    }
    		    int userId = sc.nextInt();
    		    try {
	               	 Validation.checkUserid(userId);//Validate user ID
	            }catch(Exception e) {
	               		registerUser(); // If validation fails, reattempt registration
	             }
    		    System.out.print("\tEnter customer ID:");
    		    while (!sc.hasNextInt()) {
    		    	System.out.println(ANSI_RED + "\t\tInvalid input! Please enter a numeric value for customer ID." + ANSI_RESET);
    		    	System.out.print("\tPlease Re-enter the customer id:");
    		    	sc.next(); // Consume the invalid input
    		    }
    		    custid = sc.nextInt();   		
            
            System.out.print("\tEnter your first name:");
            String firstName = sc.next();
            System.out.print("\tEnter your last name:");
            String lastName = sc.next();
            System.out.print("\tEnter the email address:");
            String email = sc.next();
            Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
            Matcher emailMatcher = emailPattern.matcher(email);
            if (!emailMatcher.matches()) {// Validate email format
            	try {
            		throw new InvalidEmailIdException("Not a valid email address !! please retry the registration process !!");
            	}catch(InvalidEmailIdException e){
            		System.out.println(ANSI_RED +"\t\t"+ e.getMessage() + ANSI_RESET);
            		registerUser(); // If email is invalid, reattempt registration
            	}
            } 
            System.out.print("\tEnter your mobile number:");
            long mobile = sc.nextLong();
            
            Pattern p = Pattern.compile("^[6-9]\\d{9}$");
            Matcher m = p.matcher(String.valueOf(mobile));
            // Check if the input phone number matches the pattern
            if(!m.matches()) {
            	try {
            		throw new InvalidEmailIdException("\tNot a valid phone number !! please retry the registration process !!");
            	}catch(InvalidEmailIdException e){
            		System.out.println(ANSI_RED +"\t\t"+ e.getMessage() + ANSI_RESET);
            		registerUser(); // If phone number is invalid, reattempt registration
            	}
            }
            System.out.print("\tEnter your age:");
            int age = sc.nextInt();
            System.out.print("\tEnter your gender (Male/Female/Transgender):");
            String gender=sc.next();
            System.out.print("\tEnter your city:");
            String city = sc.next();
            System.out.print("\tEnter your state:");
            String state = sc.next();
            System.out.print("\tEnter your country:");
            String country = sc.next();
            System.out.print("\tEnter your license number:");
            long licenseNumber = sc.nextLong();
            System.out.print("\tEnter your address:");
            String address = sc.next();
            
            // Create Customer object with input details
    	    Customer customer = new Customer(userId, custid, firstName, lastName, email, mobile, age, gender, city, state, country, address, licenseNumber);
   
    	    // Register customer using Register class instance
		    reg=new Register();
		    reg.Customer_Registration(customer);
	}
}
