package com.SearchDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ConsoleApp.CarRentalSystem.DbConnection;

public class SearchByLocation implements Search {
	Scanner sc=new Scanner(System.in);
	@Override
	public void searchFilter() {
		System.out.print("\tProvide the Location of your expectations :");
		String location=sc.next();
		
		System.out.println("\tYours Filteration has been generating.....\n\tPlease Wait for a while...!!");
		/* resultSet
                .stream() // Convert ResultSet to Stream
                .forEach(row -> {
                    try {
                        // Access columns by index (1-based)
                        System.out.println(row.getString(1) + " | " + row.getString(2) + " | " + row.getString(3) + " | "
                                + row.getString(4) + " | " + row.getString(5) + " | ");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
*/
		try {
			Connection conn=DbConnection.getConn();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM car.CAR WHERE location=?");
			statement.setString(1, location);
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
