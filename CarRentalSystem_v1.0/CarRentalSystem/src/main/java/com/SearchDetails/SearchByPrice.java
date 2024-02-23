package com.SearchDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ConsoleApp.CarRentalSystem.DbConnection;

public class SearchByPrice implements Search{
	Scanner sc=new Scanner(System.in);
	@Override
	public void searchFilter() {
		System.out.print("\tProvide the minimum price of your expectations :");
		double minPrice=sc.nextDouble();
		System.out.print("\tProvide the maximum price of your expectations :");
		double maxPrice=sc.nextDouble();
		System.out.println("\tYours Filteration has been generating.....\n\tPlease Wait for a while...!!");
		try {
			Connection conn=DbConnection.getConn();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM car.CAR WHERE RENTAL_PRICE>=? AND RENTAL_PRICE<=?");
			statement.setDouble(1, minPrice);
			statement.setDouble(2, maxPrice);
			ResultSet resultSet = statement.executeQuery();
			// Convert ResultSet to List
            List<String> rows = new ArrayList<>();
            System.out.println("\t\t----------------------------------------------------------------------------------------");
			System.out.println("\t\tCar ID\t\tBrand\t\tModel\t\tRental Price\t\tStatus\t\tLocation");
            System.out.println("\t\t----------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                String row = "\t\t"+resultSet.getString(1) + "\t|\t" + resultSet.getString(2) + "\t|\t"
                             + resultSet.getString(3) + "\t|\t" + resultSet.getString(4) + "\t|\t"
                             + resultSet.getString(5) + "\t|\t";
                rows.add(row);
            }
            
            // Convert List to Stream and print each row
            rows.stream().forEach(System.out::println);
            System.out.println("\t\t----------------------------------------------------------------------------------------");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
