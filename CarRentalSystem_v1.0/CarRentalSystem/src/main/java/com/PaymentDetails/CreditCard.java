package com.PaymentDetails;

import java.sql.Connection;
import java.sql.Date;

import com.BookingDetails.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ExceptionDetails.InvalidCardNumberException;

public class CreditCard implements Payment{
	Scanner sc=new Scanner(System.in);
	@Override
	public void booking(Connection conn, BookingDetails Bookdata) throws SQLException {
	    System.out.print("Enter your credit card number:");
	    String num = sc.next();
	    System.out.print("Enter your pin number :");
	    int pin = sc.nextInt();
	    
	    //Cardnumber validation using regex
	    final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{16}$");
	    Matcher matcher=CARD_NUMBER_PATTERN.matcher(num);
		
	    if (matcher.matches()) {
	    	 int paymentid = Booking.AutoPaymentID();
		        PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO car.BOOKING_HISTORY (BOOKING_ID, CARID, BOOKING_DATE, PICKUP_DATE, RETURN_DATE, TOTAL_COST, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?)");
		        insertStatement.setInt(1,Bookdata.getBookid());
		        insertStatement.setInt(2, Bookdata.getCarId());
		        insertStatement.setDate(3, Date.valueOf(Bookdata.getCurrentDate()));
		        insertStatement.setDate(4, Date.valueOf(Bookdata.getPickupDate()));
		        insertStatement.setDate(5, Date.valueOf(Bookdata.getReturnDate()));
		        insertStatement.setDouble(6, Bookdata.getTotalPrice());
		        insertStatement.setInt(7, Bookdata.getUserId());
		        insertStatement.executeUpdate();

		        PreparedStatement credit = conn.prepareStatement("INSERT INTO car.PAYMENT(PAYMENTID, MODEOFPAYMENT, STATUS, PAYMENT_DATE) VALUES (?,?,?,?)");
		        credit.setInt(1, paymentid);
		        credit.setString(2, "CREDIT CARD");
		        credit.setString(3, "PAID");
		        credit.setDate(4, Date.valueOf(Bookdata.getCurrentDate()));
		        credit.execute();

		        PreparedStatement car = conn.prepareStatement("Update car.car set status='Rental' where carid=?");
		        car.setInt(1, Bookdata.getCarId());
		        car.execute();

		        System.out.println("Congrats !! Your have reserved a car !!");
	       
	    } else {
	    	 try {
		            throw new InvalidCardNumberException("Provide correct number!! Retry again !!");
		        } catch (InvalidCardNumberException e) {
		            System.out.println(e.getMessage());
		        }
	    }
	}
	
	
	


}
