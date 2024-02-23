package ConsoleApp.CarRentalSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.ExceptionDetails.InvalidChoiceException;
import com.UserDetails.Admin;
import com.UserDetails.Customer;

public class ProfileActivity {
	static Scanner sc=new Scanner(System.in);
	static Admin admin=new Admin();
	public static void viewProfileDetails() {
		try {
			Connection con=DbConnection.getConn();
			PreparedStatement sts = con.prepareStatement("select * from CAR.customer where customer_id=?");
			System.out.print("\tEnter your customer id :");
			sts.setInt(1, sc.nextInt());
			ResultSet result = sts.executeQuery();
			while (result.next()) {
			    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
			    System.out.println("+\tUserid       =\t" + result.getInt(1));
			    System.out.println("+\tCustomerid   =\t" + result.getString(2));
			    System.out.println("+\tFirstname    =\t" + result.getString(3));
			    System.out.println("+\tLastname     =\t" + result.getString(4));
			    System.out.println("+\tEmail        =\t" + result.getString(5));
			    System.out.println("+\tPhone        =\t" + result.getString(6));
			    System.out.println("+\tAge          =\t" + result.getString(7));
			    System.out.println("+\tAddress      =\t" + result.getString(8));
			    System.out.println("+\tLicenseno    =\t" + result.getString(9));
			    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void editProfileDetails(int choice) {
		try {
			Connection con=DbConnection.getConn();
			System.out.print("Enter your customer id :");int id=sc.nextInt();
			switch(choice) {
			case 1:
				System.out.print("Enter your new email id:");String newEmail=sc.next();
				PreparedStatement sts = con.prepareStatement("update car.customer set email=? where customer_id=?");
				sts.setString(1, newEmail);
				sts.setInt(2, id);
				sts.execute();
				System.out.println("--------->Your Email has been updated successfully<-------------");
				break;
			case 2:
				System.out.print("Enter your new Phone number:");long newPhoneNo=sc.nextLong();
				PreparedStatement sts2 = con.prepareStatement("update car.customer set phoneno=? where customer_id=?");
				sts2.setLong(1, newPhoneNo);
				sts2.setInt(2, id);sts2.execute();
				System.out.println("--------->Your Phone number has been updated successfully<-------------");

				break;
			case 3:
				System.out.print("Enter your current age:");int newAge=sc.nextInt();
				PreparedStatement sts3 = con.prepareStatement("update car.customer set age=? where customer_id=?");
				sts3.setInt(1, newAge);
				sts3.setInt(2, id);sts3.execute();
				System.out.println("--------->Your Age has been updated successfully<-------------");

				break;
			case 4:
				System.out.print("Enter your updated license number:");long newLicenseNo=sc.nextLong();
				PreparedStatement sts5 = con.prepareStatement("update car.customer set licenseno=? where customer_id=?");
				sts5.setLong(1, newLicenseNo);
				sts5.setInt(2, id);sts5.execute();
				System.out.println("--------->Your License number has been updated successfully<-------------");

				break;
				
			case 5:
				System.out.print("Enter your current address:");String newAddress=sc.next();
				PreparedStatement sts7 = con.prepareStatement("update car.customer set address=? where customer_id=?");
				sts7.setString(1, newAddress);
				sts7.setInt(2, id);sts7.execute();
				System.out.println("--------->Your Address has been updated successfully<-------------");

				break;
				
			case 6:
				System.out.print("Enter your current city:");String newCity=sc.next();
				PreparedStatement sts4 = con.prepareStatement("update car.location set city=? where loc_id=?");
				sts4.setString(1, newCity);
				sts4.setInt(2, id);sts4.execute();
				System.out.println("--------->Your City has been updated successfully<-------------");

				break;
				
			case 7:
				System.out.print("Enter your current state:");String newState=sc.next();
				PreparedStatement sts6 = con.prepareStatement("update car.location set state=? where loc_id=?");
				sts6.setString(1, newState);
				sts6.setInt(2, id);sts6.execute();
				System.out.println("--------->Your State has been updated successfully<-------------");

				break;
				
			case 8:
				System.out.print("Enter your current Country:");String newCountry=sc.next();
				PreparedStatement sts8 = con.prepareStatement("update car.location set country=? where loc_id=?");
				sts8.setString(1, newCountry);
				sts8.setInt(2, id);sts8
				.execute();
				System.out.println("--------->Your Country has been updated successfully<-------------");

				break;
			case 9:
				Customer customer=new Customer();
				customer.customerLogin();
				break;
				
			default:
				System.out.println("Your have entered a invaild choice please try again!!");
				Customer c=new Customer();
				c.updateProfileDetails();
				break;
			
			}
			System.out.print("Do you want to continue to edit your profile ? (y/n):");char ch=sc.next().charAt(0);
			if(ch=='y') {
				Customer c=new Customer();
				c.updateProfileDetails();
			}else if(ch=='n') {
				System.out.println("------->Explore more on our system please<--------");
			}else {
				System.out.println("Invalid answer xx");
			}
		}catch(Exception e) {
			
		}
	}
	public void profileManagement() {
		System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.View profile                                                            *\n");
        System.out.print("\t*                  2.Go to previous page                                                     *\n");
        System.out.print("\t*                                                                                            *\n");
	    System.out.print("\t**********************************************************************************************\n");
	    System.out.print("\tEnter your choice:");String ch=sc.next();
	    switch(ch) {
		    case "1":
			{
				//To view the details of the admin
				admin.viewProfile();
				break;
			}
		    case "2":
		    {
		    	admin.adminLogin();
		    	break;
		    }
		    default:
		    	try {
		    	throw new InvalidChoiceException("\tYour choice is not allowed !! please provide values from 1-4 !!");
		    	}catch(Exception e) {
		    		System.out.println(e.getMessage());
		    	}
	    }
	}

}
