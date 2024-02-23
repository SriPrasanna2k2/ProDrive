package ConsoleApp.CarRentalSystem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.ExceptionDetails.InvalidChoiceException;
import com.ExceptionDetails.InvalidValidationException;
import com.UserDetails.Account;
import com.UserDetails.Admin;
import com.UserDetails.Customer;

/**
 * The main class for the ProDrive Car Rental System.
 * This class provides the main menu for users to navigate through the application.
 * It allows users to log in as admin, customer, or guest, and performs appropriate actions based on user choice.
 * The class handles user inputs and displays the necessary options to the user.
 * 
 * @author Sri Prasanna D (Expleo)
 * @since 22 Feb 2024
 */


public class DriverCarRentalApp {

	/**
     * The main method that starts the application.
     * 
     * @param args Command-line arguments (not used)
     * @throws InvalidChoiceException   if an invalid choice is provided by the user
     * @throws InvalidValidationException if validation fails during user login
     */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InvalidChoiceException,InvalidValidationException {
		Scanner sc=new Scanner(System.in);
		Admin admin = new Admin(); // Instance of Admin class
        Account acc = new Account(); // Instance of Account class
        Customer cust = new Customer(); // Instance of Customer class
		String choice;
		do {
			System.out.println("\t\t\t+-------------------------------------------------+");
			System.out.println("\t\t\t|               Welcome to the                    |");
			System.out.println("\t\t\t|            ProDrive Car Rental System           |");
			System.out.println("\t\t\t+-------------------------------------------------+");
			System.out.println("\t\t\t|                                                 |");
			System.out.println("\t\t\t|    🚗 At ProDrive, we offer a wide range of      |");
			System.out.println("\t\t\t|    vehicles tailored to suit your needs.        |");
			System.out.println("\t\t\t|                                                 |");
			System.out.println("\t\t\t|    🌟 Whether you're exploring the city or      |");
			System.out.println("\t\t\t|    embarking on a family adventure, we've got   |");
			System.out.println("\t\t\t|    the perfect ride for you.                    |");
			System.out.println("\t\t\t|                                                 |");
			System.out.println("\t\t\t|    🚀 Start your journey with us today!          |");
			System.out.println("\t\t\t|                                                 |");
			System.out.println("\t\t\t+-------------------------------------------------+");
			System.out.println("\t**********************************************************************************************");
			System.out.println("\t*                                                                                            *");
			System.out.println("\t*                    >>>>>>>>>>>>    HOME PAGE    <<<<<<<<<<<<<<           	               *");
			System.out.println("\t*                                                                                            *");
			System.out.println("\t*                                                                                            *");
			System.out.println("\t*                                 1. ADMIN  🛠️                                               *");
			System.out.println("\t*                                 2. CUSTOMER  🧑‍💼                                            *");
			System.out.println("\t*                                 3. GUEST  🕵️                                               *");
			System.out.println("\t*                                 4. EXIT  🚪                                                *");
			System.out.println("\t*                                                                                            *");
			System.out.println("\t**********************************************************************************************");

	        System.out.print("\t\tEnter your choice:");
			    choice = sc.next();
			    switch(choice) {
			    case "1":
			    	
			        System.out.println("\t\t\t*****************Welcome to Admins portal***********************");
			        System.out.println("\t\t\t   Note: Here in this system Admin is a already registered !!  ");
			        String username;
			        String password;
			        int check=0;  //Check flag for login validation
			        do {
			        	System.out.print("\t\tUser ID -->");
				        username = sc.next();
				        System.out.print("\t\tPassword-->");
				        password = sc.next();
				        // Validate login credentials
				    	if((username.compareTo("6152")==0&&password.compareTo("2k20cse147!")==0)||(username.compareTo("4321")==0&&password.compareTo("xyz")==0))
				    	{
				    		admin.adminLogin();
				    	}
				    	else {
				    		try {
				    			throw new InvalidValidationException("\t\tSorry !! You have provided wrong UserId or password !!\n\t\tPlease retry again :)");
				    			
				    		}catch(Exception e) {
				    			System.out.println(e.getMessage());
				    			check=1;
				    		}
				    	}
				    }while(check==1);
				   break;
			        
			    case "2":
			    	// Customer login
			    	cust.customerEntry();
			    	break;
			    
			    case "3":
			    	// Guest view
			    	System.out.println("\n\t--> You are permitted only to view the car details !! To proceed further Register now :) <--");
			    	System.out.print("\t**********************************************************************************************\n");
			        System.out.print("\t*                                                                                            *\n");
			        System.out.print("\t*                              1.View car details                                            *\n");
			        System.out.print("\t*                              2.Go to Home page                                             *\n");
			        System.out.print("\t*                                                                                            *\n");
			        System.out.print("\t**********************************************************************************************\n");
			        System.out.print("\tEnter your choice:");String ch=sc.next();
			        switch(ch) {
			        case "1":
			        	admin.viewCarDetails();
			        	break;
			        case "2":
			        	break;
			        default:
			        	try {
			        		throw new InvalidChoiceException("\tWrong choice provided !! Please provide choice either 1 or 2 !!");
			        	}catch(InvalidChoiceException e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
			    	break;
			    	
			    case "4":
			    	// Exit the application
			    	System.out.println("\t\t----------> Thank you for visiting our application :) <----------------");
			    	System.exit(0);
			    	
			    default:
			    	// Exception rises for invalid choice
			    	try {
			    	throw new InvalidChoiceException("\tYour choice is not allowed !! please provide values from 1-4 !!");
			    	}catch(Exception e) {
			    		System.out.println(e.getMessage());
			    	}
			    }
		}while(true);// Loop indefinitely until user chooses to exit
		

	}

}
