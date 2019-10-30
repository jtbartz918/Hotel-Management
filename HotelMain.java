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
	static HashMap<Integer, Boolean> room = new HashMap<>();
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	final private static String host = "jdbc:mysql://localhost:3306/sys";

	final private static String user = "root";
	final private static String pw = "Isigna918*";
	//final private static String con = ho;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		welcomeScreen();

	}

	public static void printDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		//connect = DriverManager.getConnection(host +"," + user + "," + passwd);
		//connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Isigna918*");
		connect = DriverManager.getConnection(host,user,pw);
		statement = connect.createStatement();

		rs = statement.executeQuery("select * from hotel");

		while(rs.next())
			System.out.println(rs.getInt(1)+"  "+rs.getString(2));


	}

	public static void welcomeScreen() throws ClassNotFoundException, SQLException {
		loginScreen();
		if (userType == "guest") {
			handleGuest();
		} else if (userType == "employee") {
			handleEmployee();
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

	public static void employeeMainScreen() throws ClassNotFoundException, SQLException {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. See status of each room");
		System.out.println("2. Schedule an employee");
		System.out.println("3. Process payment");
		System.out.println("4. Checkout");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			checkRoomStatus();
			return;
		} else if (choice == 2) {
			// scanner.close();
			scheduleEmployee();
			// return;
		} else if (choice == 3) {
			cashOut();
			// return;
		}
		else if(choice ==4) {
			checkOut();
		}
		else {
			System.out.println("Input is not a user type.");
		}
	}

	public static void guestBookRoom() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();
		if(roomnum != 100 && roomnum!=110&&roomnum!=120&&roomnum!=130&&roomnum!=140&&roomnum!=150&&roomnum!=160&&roomnum!=170&&roomnum!=180&&roomnum!=190&&roomnum!=200) {
			System.out.println("The room you are trying to book does not exist please try again");
			guestBookRoom();
		}

		connect=DriverManager.getConnection(host,user,pw);
		statement = connect.createStatement();
		//java.sql.PreparedStatement ps = connect.prepareStatement("INSERT INTO hotel(vac) where roomNum = ?");
		 String query = "UPDATE hotel SET vac = 1 WHERE roomNum = ?";
		 java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		 preparedStmt.setInt (1, roomnum);

		 preparedStmt.execute();

	}

	public static void checkOut() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the room you would like to checkout from?");
		int roomnum = scanner.nextInt();
		if(roomnum != 100 && roomnum!=110&&roomnum!=120&&roomnum!=130&&roomnum!=140&&roomnum!=150&&roomnum!=160&&roomnum!=170&&roomnum!=180&&roomnum!=190&&roomnum!=200) {
			System.out.println("The room you are trying to checkout of does not exist please try again");
			checkOut();
		}

		connect=DriverManager.getConnection(host,user,pw);
		statement = connect.createStatement();
		//java.sql.PreparedStatement ps = connect.prepareStatement("INSERT INTO hotel(vac) where roomNum = ?");
		 String query = "UPDATE hotel SET vac = 0 WHERE roomNum = ?";
		 java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		 preparedStmt.setInt (1, roomnum);

		 preparedStmt.execute();

	}

	public static void handleGuest() throws ClassNotFoundException, SQLException {
		guestBookRoom();
		userType = "none";
		welcomeScreen();
	}

	public static void checkRoomStatus() throws ClassNotFoundException, SQLException {
		//System.out.println("check room status");
		printDB();
		//System.out.println(room);
	}

	public static void scheduleEmployee() {
		System.out.println("schedule");
	}

	public static void cashOut() {
		System.out.println("cashout");
	}

	public static void handleEmployee() throws ClassNotFoundException, SQLException {
		employeeMainScreen();
		userType = "none";
		welcomeScreen();

	}

}
