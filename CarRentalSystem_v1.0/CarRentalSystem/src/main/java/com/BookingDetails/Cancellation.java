package com.BookingDetails;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

import ConsoleApp.CarRentalSystem.DbConnection;

public class Cancellation {
	Scanner sc=new Scanner(System.in);
	public void CancellationOfCar(int userid) {
		
		LocalDate bookingDate=null;
		int totalprice=0;
		try {
			Connection conn=DbConnection.getConn();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM car.BOOKING_HISTORY WHERE USER_ID = ?");
			statement.setInt(1, userid);
			ResultSet rs = statement.executeQuery();
			System.out.println("List of cars that you have booked :");
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
			System.out.println("BOOKING_ID\t|\tCARID\t|\tBOOKING_DATE\t|\tPICKUP_DATE\t|\tRETURN_DATE\t|\tTOTAL_COST\t|\tUSER_ID");
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
			while (rs.next()) {
				 System.out.println("\t"+rs.getInt(1)+"\t|\t"+rs.getString(2)+"\t|\t"+rs.getDate(3)+"\t|\t"+rs.getDate(4)+"\t|\t"+rs.getDate(5)+"\t|\t"+rs.getString(6)+"\t\t|\t"+rs.getString(7)+"\t|\t");
			 }
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
			System.out.print("Select carId that you want to cancel now ->");int id=sc.nextInt();
			PreparedStatement carSelect = conn.prepareStatement("SELECT booking_date FROM car.BOOKING_HISTORY WHERE carid = ?");
			carSelect.setInt(1, id);
			ResultSet ab=carSelect.executeQuery();
			if (ab.next()) {
                bookingDate = ab.getDate(1).toLocalDate();}
			
			LocalDate endDate = bookingDate.plusDays(7);
			LocalDate currDate=LocalDate.now();
			if (currDate.isAfter(endDate)) {
				System.out.println("You have exceed due time for cancellation !! Payment will not be refunded !!");
			}else {
				System.out.println("Your are within the duetime !! Payment will be refunded !!");
				PreparedStatement price = conn.prepareStatement("SELECT TOTAL_COST FROM car.BOOKING_HISTORY WHERE carid = ?");
				price.setInt(1, id);
				ResultSet pp=price.executeQuery();
				if (pp.next()) {
	                totalprice = pp.getInt(1);}
				System.out.println("Refunded amount : "+totalprice);
				System.out.println("Your car is cancelled successfully  :)");
				
				PreparedStatement updation = conn.prepareStatement("UPDATE car.car SET STATUS='Available' WHERE carid = ?");
				updation.setInt(1, id);
				updation.execute();
				
			}      
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
