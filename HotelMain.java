package hm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

import com.mysql.jdbc.*;

public class HotelMain {

	static String userType;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

//	final private static String host = "jdbc:mysql://localhost:3306/sys";
//	final private static String user = "root";
//	final private static String pw = "Isigna918*";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		welcomeScreen();

	}

//	public static void printDB() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.jdbc.Driver");
//		connect = DriverManager.getConnection(host, user, pw);
//		statement = connect.createStatement();
//
//		rs = statement.executeQuery("select * from hotel");
//
//		while (rs.next())
//			System.out.println(rs.getInt(1) + "  " + rs.getString(2));
//
//	}

	public static void welcomeScreen() throws ClassNotFoundException, SQLException {
		loginScreen();
		if (userType == "guest") {
			Guest.handleGuest();
		} else if (userType == "employee") {
			Employee.handleEmployee();;
		}
	}

	public static void loginScreen() {
		Scanner scanner = new Scanner(System.in);
		Boolean loginSuccessful = false;

		System.out.println("Welcome to the hotel management system");
		System.out.println("Sign in as a guest or employee.");

		while (!loginSuccessful) {
			System.out.println("Press 1 for guest or 2 for employee.");
			int choice = scanner.nextInt();
			if (choice == 1) {
				// scanner.close();
				userType = "guest";
				return;
			} else if (choice == 2) {
				// scanner.close();
				userType = "employee";
				return;
			} else {
				System.out.println("Input is not a user type.");
			}
		}
		scanner.close();
	}

}
