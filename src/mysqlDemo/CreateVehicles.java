package mysqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateVehicles {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlDemo", "root","root123");

			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();

 			switch (choice) {
			case 1:
				InsertVehicle(scanner, dbconnection);
				break;
			case 2:
				GetAllVehicles(scanner,dbconnection);
				break;
			case 3: 
				GetVehicleById(scanner, dbconnection);
				break;
			case 4:
				GetVehiclesByMake(scanner,dbconnection);
				break;
			case 5:
				UpdateVehicles(scanner,dbconnection);
				break;
			case 6:
				DeleteVehicles(scanner,dbconnection);
				break;
			default:
				System.out.println("Enter the correct choice");
				break;
			}
			dbconnection.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void InsertVehicle(Scanner scanner, Connection dbconnection) throws SQLException {
		System.out.println("enter the vehicleId:");
		int vehicleId = scanner.nextInt();

		System.out.println("Enter the year:");
		int year = scanner.nextInt();

		System.out.println("Enter the model:");
		String model = scanner.next();

		System.out.println("Enter Vehicle by make");
		String make = scanner.next();

		String query = "Insert Into Vehicle(vehiclesId,year,model,make) Values(" + vehicleId + ","
																			  + year + ",'" 
																		  	  + model+ "','"
																  	  		  + make + "')";

		Statement statement = dbconnection.createStatement();
		int rows = statement.executeUpdate(query);

		if(rows > 0) {
		    System.out.println("Record inserted successfully");
		} else {
			System.out.println("Record insertion failed");
		}
		statement.close();
	}
	
	private static void GetAllVehicles(Scanner scanner, Connection dbconnection) throws SQLException {		
		String query = "Select * FROM Vehicle";
		
		Statement statement = dbconnection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		while (rs.next()) {
			System.out.println("VehicleId : " + rs.getInt("VehiclesId"));
	        System.out.println("Year      : " + rs.getInt("year"));
	        System.out.println("Model     : " + rs.getString("model"));
	        System.out.println("Make      : " + rs.getString("make"));
		}
	
		rs.close();
	    statement.close();
	}	
	
	private static void GetVehicleById(Scanner scanner,Connection dbconnection) throws SQLException {
		System.out.println("Enter the VehicleId:");
		int vehicleId = scanner.nextInt();
		
		String query = "Select * FROM Vehicle WHERE VehiclesId =" + vehicleId;
		
		Statement statement = dbconnection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		if (rs.next()) {
			System.out.println("VehicleId : " + rs.getInt("VehiclesId"));
	        System.out.println("Year      : " + rs.getInt("year"));
	        System.out.println("Model     : " + rs.getString("model"));
	        System.out.println("Make      : " + rs.getString("make"));
		}
		else {
			System.out.println("Invalid VehicleId");
		}
		rs.close();
	    statement.close();
	}

	private static void GetVehiclesByMake(Scanner scanner,Connection dbconnection) throws SQLException {
		System.out.println("Enter Vehicles by make:");
		String make = scanner.next();
		
		String query ="Select * FROM Vehicle WHERE make = '"+make+"'";
		
		Statement statement = dbconnection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		if(rs.next()) {
			System.out.println("VehicleId: " +rs.getInt("VehiclesId"));
			System.out.println("year : "  +rs.getInt("year"));
			System.out.println("Model:  " +rs.getString("model"));
			System.out.println("Make  : " +rs.getString("make"));	
		}
		else {
			System.out.println("Invalid make");
		}
	    rs.close();
	    statement.close();
	}
	
	private static void UpdateVehicles(Scanner scanner,Connection dbconnection)throws SQLException{
		System.out.println("Enter the new model:");
		String model = scanner.next();
		
		System.out.println("Enter VehicleId:");
		int vehicleId = scanner.nextInt();
		
		String Query ="Update Vehicle Set model='"+model+"'" + "WHERE vehiclesId='"+vehicleId+"'";

		Statement statement = dbconnection.createStatement();

		int rows = statement.executeUpdate(Query);
		
		if (rows > 0) {
			System.out.println("Record updated successfully");
		}
		else {
			System.out.println("Record update Failed");
		}
		statement.close();
	}
	
	private static void DeleteVehicles(Scanner scanner,Connection dbconnection)throws SQLException{
		System.out.println("Enter VehicleId:");
		int vehicleId = scanner.nextInt();
		
		String query ="Delete From Vehicle WHERE vehiclesId='"+vehicleId+"'";

		Statement statement = dbconnection.createStatement();

		int rows = statement.executeUpdate(query);
		if (rows > 0) {
			System.out.println("Record Deleted Successfully");
		}
		else {
			System.out.println("Record Delete Failed");
		}
		statement.close();
	}
}
