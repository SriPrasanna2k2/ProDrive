package com.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.BookingDetails.Booking;
import com.ExceptionDetails.InvalidChoiceException;
import com.ExceptionDetails.InvalidValidationException;

import ConsoleApp.CarRentalSystem.Car;
import ConsoleApp.CarRentalSystem.DbConnection;
import ConsoleApp.CarRentalSystem.ProfileActivity;
import ConsoleApp.CarRentalSystem.Register;

/**
 * The Admin class represents the administrator of the car rental system.
 * It provides functionalities such as managing cars, customers, bookings, profiles, etc.
 * Admin can perform operations like adding, updating, removing cars/customers, viewing car details, etc.
 * Admin authentication is required for certain critical operations.
 * 
 * @author Sri Prasanna (Expleo)
 * @since 22 Feb 2024  
*/
public class Admin extends User{
	static Scanner sc=new Scanner(System.in);
	private String email;
	private int age;
	private long phoneNumber;
	private LinkedList<Car> carList;

    // Constructor
    public Admin() {
        carList = new LinkedList<Car>();
    }

    public Admin(String email, int age, long phoneNumber) {
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
        carList = new LinkedList<Car>();
    }


    //Getters
	public String getEmail() {
		return email;
	}
	public int getAge() {
		return age;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	//Setters
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	Car car=new Car();
	Booking booking=new Booking();
	Customer customer=new Customer();
	ProfileActivity profile=new ProfileActivity();
	
	/**
     * Method to handle admin login.
     * Admin can perform various operations like car management, customer management, etc.
     */
	 public void adminLogin() {
		boolean checkadmin = true;
	    		System.out.println("\t\tWelcome Admin!! what you want to do !!");
	    		while(checkadmin)
	    		{
	    			System.out.print("\t**********************************************************************************************\n");
	    	        System.out.print("\t*                                                                                            *\n");
	    	        System.out.print("\t*                  1.Car Management                                                          *\n");
	    	        System.out.print("\t*                  2.Customer Management                                                     *\n");
	    	        System.out.print("\t*                  3.Booking Management                                                      *\n");
	    	        System.out.print("\t*                  4.Profile Management                                                      *\n");
	    	        System.out.print("\t*                  5.LOGOUT                                                                  *\n");
	    	        System.out.print("\t*                                                                                            *\n");
	    	        System.out.print("\t**********************************************************************************************\n");
	    	        System.out.print("\t\tEnter your choice :");String option=sc.next();
	    	        switch(option) {
	    	        case "1":
	    	        	car.carManagement();
	    	        	break;
	    	        case "2":
	    	        	customer.customerManagement();
	    	        	break;
	    	        case "3":
	    	        	booking.bookingManagement();
	    	        	break;
	    	        case "4":
	    	        	profile.profileManagement();
	    	        	break;
	    	        case "5":
	    	        	checkadmin = false;
    					System.out.println("\t\txx - Logged out successfully - xx");
    					break;
	    	        default:
    					try {
    						throw new InvalidChoiceException("\tYour choice is not Allowed !! Please provide choice as from 1 to 10 !!");
    					}catch(Exception e) {
    						System.out.println(e.getMessage());
    						
    					}
	    	        }
	    	        
	    		}//end of while
	    		
	}
	    	
	/**
	* Method to add a new car to the system.
	*/    	        
	public void addCar() {
		try {
			System.out.println("\tEnter the following details!!");
			System.out.print("\tEnter the car id:");
			int carId=sc.nextInt();
			System.out.print("\tEnter the name of the brand:");
			String brand=sc.next();
			System.out.print("\tEnter the model name:");
			String model=sc.next();
			System.out.print("\tEnter the rental price for one day:");
			double pricePerDay=sc.nextDouble();
			System.out.print("\tEnter the location of the car:");
			String loc=sc.next();
			Car car = new Car(carId, brand, model, pricePerDay,loc);
	        carList.add(car);
	        Register r=new Register();
	        r.CarRegistration(car);
			
		}catch(InputMismatchException e1) {
			System.out.println("Wrong input provided !!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
    /**
     * Method to update details of an existing car.
     */
	public void updateCar() {
			
			try {
				int count =0;
				System.out.print("\tEnter the car id to update the details:");
				int carId=sc.nextInt();
				
				Connection con=DbConnection.getConn();
				Statement ss=con.createStatement();
				
				// Query to check if the car exists and is active
				ResultSet rs=ss.executeQuery("Select count(*) from car.car where carid="+carId+"and visibility='Active'");
				if (rs.next()) {
	                count = rs.getInt(1);
	               
	            }
				
				// If car exists and is active, allow updates
				if(count>0) {
					System.out.println("\t\t\t****What details u have to update now ADMIN!!*****");
				 	System.out.println("\t\t\t\t*****************************");
			        System.out.println("\t\t\t\t*                           *");
			        System.out.println("\t\t\t\t*  1. Brand name            *");
			        System.out.println("\t\t\t\t*  2. Model name            *");
			        System.out.println("\t\t\t\t*  3. Rental price per day  *");
			        System.out.println("\t\t\t\t*  4. Available status      *");
			        System.out.println("\t\t\t\t*  5. Location of car       *");
			        System.out.println("\t\t\t\t*  6. Visibility of car     *");
			        System.out.println("\t\t\t\t*  7. Go to previous page   *");
			        System.out.println("\t\t\t\t*                           *");
			        System.out.println("\t\t\t\t*****************************");
			        System.out.print("\t\tEnter your option:");String ch=sc.next();
			        switch(ch) {
			        case "1":
			        	System.out.print("\t\tProvide brand name to update:");String brand=sc.next();
			        	PreparedStatement sts = con.prepareStatement("update car.car set brand=? where carid=? and visibility='Active'");
			        	sts.setString(1, brand);
			        	sts.setInt(2, carId);
			        	sts.execute();
			        	System.out.println("\t\tBrand name updated successfully!!");
			        	break;
			        	
			        case "2":
			        	System.out.print("\t\tProvide model name to update:");String model=sc.next();
			        	PreparedStatement sts1 = con.prepareStatement("update car.car set model=? where carid=? and visibility='Active'");
			        	sts1.setString(1, model);
			        	sts1.setInt(2, carId);
			        	sts1.execute();
			        	System.out.println("\t\tModel name updated successfully!!");
			        	break;
			        case "3":
			        	System.out.print("\t\tProvide rental price per day to update:");double price=sc.nextDouble();
			        	PreparedStatement sts2 = con.prepareStatement("update car.car set rental_price=? where carid=? and visibility='Active'");
			        	sts2.setDouble(1, price);
			        	sts2.setInt(2, carId);
			        	sts2.execute();
			        	System.out.println("\t\tRental price updated successfully!!");
			        	break;
			        case "4":
			        	System.out.print("\t\tProvide current status to update:");String status=sc.next();
			        	PreparedStatement sts3 = con.prepareStatement("update car.car set status=? where carid=? and visibility='Active'");
			        	sts3.setString(1, status);
			        	sts3.setInt(2, carId);
			        	sts3.execute();
			        	System.out.println("\t\tStatus of the car updated successfully!!");
			        	break;
			        case "5":
			        	System.out.print("\t\tProvide new location to update:");String loc=sc.next();
			        	PreparedStatement sts4 = con.prepareStatement("update car.car set location=? where carid=? and visibility='Active'");
			        	sts4.setString(1, loc);
			        	sts4.setInt(2, carId);
			        	sts4.execute();
			        	System.out.println("\t\tLocation of the car updated successfully!!");
			        	break;
			        	
			        case "6":
			        	System.out.print("\t\tProvide Visibility(Active/Inactive) to update:");String visible=sc.next();
			        	PreparedStatement sts5 = con.prepareStatement("update car.car set visibility=? where carid=? and visibility='Active'");
			        	sts5.setString(1, visible);
			        	sts5.setInt(2, carId);
			        	sts5.execute();
			        	System.out.println("\t\tVisibility of the car updated successfully!!");
			        	break;  
			        case "7":
			        	break;
			        default:
			        	try {
			        		throw new InvalidChoiceException("Wrong option provided !! please provide option from 1-7 !!");
			        	}catch(Exception e) {
			        		System.out.println(e.getMessage());
			        	}
			        }
				}else {
					System.out.println("\tCar not found or Inactive !!!");
				}
			}catch(InputMismatchException e1) {
				System.out.println("\tWrong input provided !!");
				updateCar();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	/**
	 * Method to remove a car from the system.
	 */
	public void removeCar() {
		System.out.print("\tEnter the car id to remove from the system:");
		
		try {
			int carId=sc.nextInt();
			Connection con=DbConnection.getConn();
			PreparedStatement sts = con.prepareStatement("update car.car set visibility='INACTIVE' where carid=?");
			sts.setInt(1, carId);
			sts.execute();
			System.out.println("\tCar with ID number :"+carId+" has been INACTIVTED now!!");
			
		}
		catch(InputMismatchException e1) {
			System.out.println("\nWrong input provided !!");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to view details of all cars in the system.
	 */
	public void viewCarDetails() {
		List<Car> carList=new ArrayList<Car>();
		try {
			Connection con=DbConnection.getConn();
			Statement sts=con.createStatement();
			ResultSet rs=sts.executeQuery("select * from CAR.car ");
			while (rs.next()) {
	            int carId = rs.getInt(1);
	            String brand=rs.getString(2);
	            String model=rs.getString(3);
	            double rentalprice=rs.getDouble(4);
	            String status=rs.getString(5);
	            String location=rs.getString(6);
	            

	            carList.add(new Car(carId,brand,model,rentalprice,status,location));
	     }
			System.out.println("\t\t-----------------------------------------------------------------------------------");
			System.out.println ("\t\t CARID |    BRAND     |      MODEL   |    STATUS    | RENTAL_PRICE  |  LOCATION  |");
			System.out.println("\t\t-----------------------------------------------------------------------------------");

								
		 for (Car i : carList) {
			 System.out.println("\t\t"+i);
		 }
			System.out.println("\t\t-----------------------------------------------------------------------------------");

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to add a new user to the system (Admin operation).
	 */
	public void addUser() {
		
		try {
			System.out.print("\tProvide new user id:");
			int userId=sc.nextInt();
			System.out.print("\t(Note:Your must password contains:\n\t\tAt least one digit (1, 2, 3,...)\n\t\tAt least one lowercase letter (s)\n\t\tAt least one uppercase letter (S, P)\n\t\tAt least 8 characters in total)\n\tProvide password for this id:");
			String password;
			password=sc.next();
			
			// Validating password strength
			Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
			Matcher passwordMatcher = passwordPattern.matcher(password);

			String cpd;
			if(!passwordMatcher.matches()){
				try {
					throw new InvalidValidationException("\tProvide Strong password !! Please retry again :)");
				}catch(InvalidValidationException e) {
					System.out.println(e.getMessage());
					addUser();
				}
		           
		     }

			while(passwordMatcher.matches())
			{
				System.out.print("\tConfirm Password:");
				cpd=sc.next();
				if(password.compareTo(cpd)==0)
						break;
			}
			
			
			Connection con=DbConnection.getConn();
			Statement st=con.createStatement();
			
			// Inserting user credentials into the database 
			st.executeUpdate("insert into CAR.Login_Credentials values('"+userId+"','"+password+"','customer','ACTIVE')");
			System.out.println("\tNew customer added Succesfully!!");
		}
		catch(InputMismatchException e1) {
			System.out.println("\tWrong input provided !!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to remove a user from the system (Admin operation).
	 */
	public void removeUser() {
		
		try {
			int count=0;
			System.out.print("\tEnter user id to remove their credentials:");int id=sc.nextInt();
			
			Connection con=DbConnection.getConn();
			Statement ss=con.createStatement();
			
			// Checking if user exists
			ResultSet rs=ss.executeQuery("Select count(*) from car.login_credentials where user_id="+id);
			if (rs.next()) {
	            count = rs.getInt(1);
	        }
			if(count>0) {
				PreparedStatement sts = con.prepareStatement("update car.login_credentials set visibility='INACTIVE' where user_id=?");
				sts.setInt(1, id);
				sts.execute();
				System.out.println("\tUser with ID number:"+id+" has been Inactived now");
			}else {
				System.out.println("\tData not found on this Car ID!!");
			}
		}
		catch(InputMismatchException e1) {
			System.out.println("\tWrong input provided !!");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to activate a customer's credentials (Admin operation).
	 */
	public void activeCustomer() {
		try {
			int count=0;
			System.out.print("\tEnter user id to active their credentials:");int id=sc.nextInt();
			
			Connection con=DbConnection.getConn();
			Statement ss=con.createStatement();
			 // Checking if user exists and is inactive
			ResultSet rs=ss.executeQuery("Select count(*) from car.login_credentials where user_id="+id+"and visibility='INACTIVE'");
			if (rs.next()) {
	            count = rs.getInt(1);
	        }
			if(count>0) {
				PreparedStatement sts = con.prepareStatement("update car.login_credentials set visibility='ACTIVE' where user_id=?");
				sts.setInt(1, id);
				sts.execute();
				System.out.println("\tUser with ID number:"+id+" has been actived now!!");
			}else {
				System.out.println("\tData not found on this Car ID or already in active status!!");
			}
		}
		catch(InputMismatchException e1) {
			System.out.println("\tWrong input provided !!");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to view details of all customers in the system.
	 */
	public void viewCustomers() {
		List<Customer> customerList=new ArrayList<Customer>();
		try {
			Connection con=DbConnection.getConn();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from car.Customer");
			 while (rs.next()) {
		            int userId = rs.getInt(1);
		            int custId=rs.getInt(2);
		            String firstName=rs.getString(3);
		            String lastName=rs.getString(4);
		            String email=rs.getString(5);
		            long phoneNo=rs.getLong(6);
		            int age=rs.getInt(7);
		            String address=rs.getString(8);
		            long licenseNo=rs.getLong(9);
		            String gender=rs.getString(10);
		            

		            customerList.add(new Customer(userId, custId, firstName,lastName,email,phoneNo,age,address,licenseNo,gender));
		     }
			System.out.println("\t\t--------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\tUSER_ID	 CUSTOMER_ID	FIRSTNAME	LASTNAME	EMAIL	 PHONENO 	AGE	  ADDRESS	 LICENSENO	  GENDER");
			System.out.println("\t\t--------------------------------------------------------------------------------------------------------------------------------");

			 for (Customer i : customerList) {
				 System.out.println("\t\t|"+i);
			 }
				System.out.println("\t\t----------------------------------------------------------------------------------------------------------------------------");

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	
	/**
	 * Method to view booking history of all transactions.
	 */
	public void viewBookingHistory() {
		try {
			Connection con=DbConnection.getConn();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from car.Booking_History");
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
			System.out.println("BOOKING_ID\t|\tCARID\t|\tBOOKING_DATE\t|\tPICKUP_DATE\t|\tRETURN_DATE\t|\tTOTAL_COST\t|\tUSER_ID");
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
			while (rs.next()) {
				 System.out.println("\t"+rs.getInt(1)+"\t|\t"+rs.getString(2)+"\t|\t"+rs.getDate(3)+"\t|\t"+rs.getDate(4)+"\t|\t"+rs.getDate(5)+"\t|\t"+rs.getString(6)+"\t\t|\t"+rs.getString(7)+"\t|\t");
			 }
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Method to view profile of the admin.
	 */
	public void viewProfile() {
		try {
			Connection con=DbConnection.getConn();
			PreparedStatement sts = con.prepareStatement("select * from CAR.admin where admin_id=?");
			System.out.print("Enter your admin id :");
			sts.setInt(1, sc.nextInt());
			ResultSet result = sts.executeQuery();
			while (result.next()) {
			    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
			    System.out.println("+\tUserid       =\t" + result.getInt(1));
			    System.out.println("+\tAdminid      =\t" + result.getString(2));
			    System.out.println("+\tName         =\t" + result.getString(3));
			    System.out.println("+\tPhone        =\t" + result.getString(4));
			    System.out.println("+\tEmail        =\t" + result.getString(5));
			    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Method for admin to approve access request.
	 * @param bookid The booking ID for which access is requested.
	 * @return True if access is approved, false otherwise.
	 */
	public boolean accessRequest(int bookid) {
		System.out.println("Access needed from the admin to proceed for further payment process !!");
		System.out.println("------> Admin Authentication Required ! <------");
		System.out.print("\t\tUser ID -->");
        String username = sc.next();
        System.out.print("\t\tPassword-->");
        String password = sc.next();
    	if((username.compareTo("1234")==0&&password.compareTo("abc")==0)||(username.compareTo("4321")==0&&password.compareTo("xyz")==0))
	    {
			try {
		    	Connection con=DbConnection.getConn();
		    	// Retrieve pending access requests
		    	 PreparedStatement statement = con.prepareStatement("SELECT * FROM car.ACCESS_NOTIFICATION WHERE STATUS = 'PENDING'");
			      ResultSet resultSet = statement.executeQuery();// Admin view pending access requests
			      while(resultSet.next()) {
			    	  System.out.println(resultSet.getInt(1)+"\t|\t"+resultSet.getString(2)+"\t|\t"+resultSet.getString(3));
			    	  
			      }
			      
			      System.out.println("\tWhat do u want to do now ADMIN ? \n1.Approve pending Request\n2.Deny pending request");
			      System.out.print("Enter your choice:");String ch=sc.next();
			      switch(ch) {
			      case "1":
			    	// Update notification status to 'APPROVED'
				        PreparedStatement approveStatement = con.prepareStatement("UPDATE car.ACCESS_NOTIFICATION SET STATUS = 'APPROVED' WHERE BOOKID = ?");
				        approveStatement.setInt(1, bookid);
				        approveStatement.executeUpdate();
				        System.out.println("Access request approved successfully.");
				        break;
			      case "2":// Admin denies access request
			    	// Update notification status to 'DENIED'
				        PreparedStatement denyStatement = con.prepareStatement("UPDATE car.ACCESS_NOTIFICATION SET STATUS = 'DENIED' WHERE BOOKID = ?");
				        denyStatement.setInt(1, bookid);
				        denyStatement.executeUpdate();
				        System.out.println("Access request denied.");
				        break;
			      default: 
			    	  try {
			    		  throw new InvalidChoiceException("Please provide a correct choice !! ");
			    	  }catch(InvalidChoiceException e) {
			    		  System.out.println(e.getMessage());
			    	  }
			      }
			      
			      String status="";
			      PreparedStatement statement1 = con.prepareStatement("SELECT STATUS FROM car.ACCESS_NOTIFICATION WHERE BOOKID = ?");
		        statement1.setInt(1, bookid);
		        ResultSet rs=statement1.executeQuery();
		        if(rs.next()) {
		        	status=rs.getString("status");
		        }
		        if(status.equals("APPROVED") ){
		        	return true;
		        }else {
		        	return false;
		        }
		        
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		        // Handle any exceptions
		    }
			
    	}else {
    		try {
    			throw new InvalidValidationException("\t\tSorry !! You have provided wrong UserId or password !!\n\t\tPlease retry again :)");
    		}catch(Exception e) {
    			System.out.println(e.getMessage());
    			accessRequest(bookid);
    		}
    	}
		return false;
	}

	
	

	

}
