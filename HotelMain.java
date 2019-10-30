import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;


public class HotelMain {

	static String userType;
	static HashMap<Integer, Boolean> room = new HashMap<>();
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	final private static String host = "jdbc:mysql://localhost:3306/sys";
	final private static String user = "root";
	final private static String passwd = "12345678";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		welcomeScreen();

	}

	public static void printDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		//connect = DriverManager.getConnection(host +"," + user + "," + passwd);
		connect=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sys","root","12345678");
		statement = connect.createStatement();

		rs = statement.executeQuery("select * from test_table");

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
		System.out.println("1. See what rooms are availiable");
		System.out.println("2. Schedule an employee");
		System.out.println("3. Process payment");
		System.out.println("4. Check guest out");
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
		} else if (choice == 4) {
			checkOut();
			// return;
		}
			else {
			System.out.println("Input is not a user type.");
		}
	}

	public static void guestBookRoom() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		//System.out.println("Please enter your full name:");
		//String name = scanner.next();
		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();
		//System.out.println("Please enter the number of nights you would like to stay:");
		//int numNights = scanner.nextInt();
		//System.out.println("Please enter the day you would like the room (mm/dd/yyyy)");
		//String date = scanner.next();
		room.put(roomnum, true);
		// scanner.close();
		System.out.println(room);

		connect=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sys","root","12345678");
		statement = connect.createStatement();
		//rs = statement.executeQuery("select roomNum from test_table");
		java.sql.PreparedStatement ps = connect.prepareStatement("select count(*) from test_table where roomNum = ?");
		ps.setInt(1,roomnum);
		ResultSet rs = ps.executeQuery();
		int n = 0;
		int s = 0;
		if(rs.next()) {
		n = rs.getInt(1);
		//s = rs.getInt(2);
		}
		if ( n == 0 ) { //The room they want to book is available, so store room in database.
		   // do what ever you need, if the row exists
			 String query = " insert into test_table (roomNum, status)"
				        + " values (?, ?)";
			 java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		     preparedStmt.setInt (1, roomnum);
		     preparedStmt.setInt (2, 0); //0=clean 1=dirty

		     preparedStmt.execute();
		}

		else { //Room is alredy taken, notify guest and restart so they can try a different room
			System.out.println(n);
			System.out.println("The room you are trying to get is booked already");
			guestBookRoom();
		}

	}

	public static void handleGuest() throws ClassNotFoundException, SQLException {
		guestBookRoom();
		userType = "none";
		welcomeScreen();
	}


	public static void checkRoomStatus() throws ClassNotFoundException, SQLException {
		System.out.println("check room status");
		printDB();
		System.out.println(room);
	}

	public static void scheduleEmployee() {
		System.out.println("schedule");
	}

	public static void cashOut() {
		System.out.println("cashout");
	}

	public static void checkOut() throws ClassNotFoundException, SQLException {
		System.out.println("checkout");
//		Scanner scanner = new Scanner(System.in);
//
//		//System.out.println("Please enter your full name:");
//		//String name = scanner.next();
//		System.out.println("Please enter the room you would like to check out from");
//		int roomnum = scanner.nextInt();
//		//System.out.println("Please enter the number of nights you would like to stay:");
//		//int numNights = scanner.nextInt();
//		//System.out.println("Please enter the day you would like the room (mm/dd/yyyy)");
//		//String date = scanner.next();
//		//room.put(roomnum, false);
//		// scanner.close();
//		//System.out.println(room);
//
//		connect=DriverManager.getConnection(
//				"jdbc:mysql://localhost:3306/sys","root","12345678");
//		statement = connect.createStatement();
//		//rs = statement.executeQuery("select roomNum from test_table");
//		java.sql.PreparedStatement ps = connect.prepareStatement("select count(*) from test_table where roomNum = ?");
//		ps.setInt(1,roomnum);
//		ResultSet rs = ps.executeQuery();
//		int n = 0;
//		int s = 0;
//		if(rs.next()) {
//		n = rs.getInt(1);
//		//s = rs.getInt(2);
//		}
//		if ( n == 1 ) { //The room they want to book is available, so store room in database.
//		   // do what ever you need, if the row exists
//			 String query = " delete from test_table (roomNum, status)"
//				        + " values (?, ?)";
//			 java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
//		     preparedStmt.setInt (1, roomnum);
//		     preparedStmt.setInt (2, 1); //0=clean 1=dirty
//
//		     preparedStmt.execute();
//		}
//
//		else { //Room is alredy taken, notify guest and restart so they can try a different room
//			System.out.println(n);
//			System.out.println("That is not the room you s");
//			guestBookRoom();
//		}
	}

	public static void handleEmployee() throws ClassNotFoundException, SQLException {
		employeeMainScreen();
		userType = "none";
		welcomeScreen();

	}

}
