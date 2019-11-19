
package hm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Employee extends HotelMain {

	static String userType;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	final private static String host = "jdbc:mysql://localhost:3306/sys";
	final private static String user = "root";

	final private static String pw = "12345678";


	public static void employeeTypeScreen() throws ClassNotFoundException, SQLException {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Manager");
		System.out.println("2. HR");
		System.out.println("3. Front Desk");
		System.out.println("4. Security");
		System.out.println("5. Restaurant");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			managerMainScreen();
			return;
		} else if (choice == 2) {
			// scanner.close();
			hrMainScreen();
			// return;
		} else if (choice == 3) {
			employeeMainScreen();
			// return;
		} else if (choice == 4) {
			employeeMainScreen();
		} else if (choice == 5) {
			employeeMainScreen();
		} else {
			System.out.println("Input is not a user type.");
		}
	}

	public static void employeeMainScreen() throws ClassNotFoundException, SQLException {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. See status of each room");
		System.out.println("2. Something");
		System.out.println("3. Process payment");

		System.out.println("4. Checkin");
		System.out.println("5. Checkout");
		System.out.println("6. Check cleanliness status of room");
		// System.out.println("7. Get price");

		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			checkRoomStatus();
			return;
		} else if (choice == 2) {
			// scanner.close();
			System.out.println("Hello world");
			// return;
		} else if (choice == 3) {
			cashOut();
			// return;
		} else if (choice == 4) {

			checkIn();
		} else if (choice == 5) {
			checkOut();
		} else if (choice == 6) {
			cleanStatus();
			
		} else {
			System.out.println("Input is not a user type.");
		}
	}


	private static void checkIn() throws SQLException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();

		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("The room you are trying to book does not exist please try again");
			checkIn();
		}
		System.out.println("Please enter the guest username");
		String uname = scanner2.nextLine();
		System.out.println("Please enter the amount of nights you wish to stay");
		int nights = scanner.nextInt();

		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();
		// java.sql.PreparedStatement ps = connect.prepareStatement("INSERT INTO
		// hotel(vac) where roomNum = ?");
		String query = "UPDATE hotel SET vac = 1, clean = 1, nights = ?, guest='" + uname + "' WHERE roomNum = ?";
		java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, nights);
		preparedStmt.setInt(2, roomnum);
		preparedStmt.execute();

	}


	public static void managerMainScreen() throws ClassNotFoundException, SQLException {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Manage Employee Information");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			manageUser();
			return;
		} else {
			System.out.println("Input is not a user type.");
		}
	}

	public static void hrMainScreen() throws ClassNotFoundException, SQLException {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. Hire an Employee");
		System.out.println("2. Fire an Employee");
		System.out.println("3. Schedule an Employee");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			System.out.println("You're hired!");
			return;
		} else if (choice == 2) {
			// scanner.close();
			System.out.println("You're fired!");
			// return;
		} else if (choice == 3) {
			// scanner.close();
			scheduleEmployee();
			// return;
		} else {
			System.out.println("Input is not a user type.");
		}
	}


	public static void checkOut() throws SQLException, ClassNotFoundException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the room you would like to checkout from?");
		int roomnum = scanner.nextInt();
		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("The room you are trying to checkout of does not exist please try again");
			checkOut();
		}
		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();

		// java.sql.PreparedStatement ps = connect.prepareStatement(" INTO
		// hotel(vac) where roomNum = ?");
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();

		rs = stmt3.executeQuery("SELECT vac FROM hotel WHERE roomNum = " + roomnum + "");
		while (rs.next()) {
			if (rs.getInt(1) == 0) {
				System.out.println("There was no one staying in this room press 1 try again or press 2 to go back");
				Scanner sc = new Scanner(System.in);
				int ch = sc.nextInt();
				if (ch == 1) {
					checkOut();
				}
				if (ch == 2) {
					employeeMainScreen();
				}

			}
		}
		System.out.println("");
		System.out.println("Thank you for staying with us. Your total is $" + getTotalPrice(roomnum));
		System.out.println("");
		System.out.println("Please enter your credit card information...");
		System.out.println("Card number:");
		Scanner sc = new Scanner(System.in);
		String cardNum = sc.next();
		System.out.println("CCV:");
		int ccv = sc.nextInt();
		System.out.println("Experation Date:");
		String expDate = sc.next();
		if (verifyPayment(cardNum, ccv, expDate) == 0) {
			connect = DriverManager.getConnection(host, user, pw);
			Statement stmt = connect.createStatement();
			String g = "";
			rs = stmt3.executeQuery("SELECT guest FROM hotel WHERE roomNum = " + roomnum + "");
			while (rs.next()) {
			g = rs.getString("guest");
			}
			Guest.addPoints(roomnum,g);
			String query = "UPDATE hotel SET vac = 0, clean = 0, nights = 0 WHERE roomNum = ?";
			java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, roomnum);

			preparedStmt.execute();

		} else {
			System.out.println("payment info was not verified, please try again.");
			checkOut();
		}
	}

	private static int verifyPayment(String cardNum, int ccv, String expDate) {
		// TODO Auto-generated method stub
		System.out.println("Is this correct? Enter 1 for 'yes' or 2 for 'no'");
		System.out.println("");
		System.out.println("card num: " + cardNum + " ccv: " + ccv + " experation date: " + expDate);
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		if (v == 1) {
			return 0;
		} else if (v == 2) {
			return 1;

		}
		return 1;

	}

	private static int getTotalPrice(int roomnum) throws SQLException {
		// TODO Auto-generated method stub
		int pr = 0;
		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("The room you are trying to checkout of does not exist please try again");
			getPrice();
		}
		connect = DriverManager.getConnection(host, user, pw);
		PreparedStatement s = (PreparedStatement) connect
				.prepareStatement("SELECT nights FROM hotel WHERE roomNum = ?");
		s.setInt(1, roomnum);
		rs = s.executeQuery();
		while (rs.next()) {
			int pr2 = rs.getInt(1) * 300;
			return pr2;
			// System.out.println("$" + pr);
		}
		return 0;


	}

	public static void getPrice() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the room you are staying in");
		int roomnum = scanner.nextInt();
		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("The room you are trying to checkout of does not exist please try again");
			getPrice();
		}
		connect = DriverManager.getConnection(host, user, pw);
		PreparedStatement s = (PreparedStatement) connect
				.prepareStatement("SELECT nights FROM hotel WHERE roomNum = ?");
		s.setInt(1, roomnum);
		rs = s.executeQuery();
		while (rs.next()) {
			int pr = rs.getInt(1) * 300;
			System.out.println("$" + pr);
		}
	}

	public static void checkRoomStatus() throws ClassNotFoundException, SQLException {
		// System.out.println("check room status");

		Database.printDB();

		// System.out.println(room);
	}

	public static void scheduleEmployee() throws ClassNotFoundException, SQLException {

		int hours = 0;
		System.out.println("Schedule: ");
		printSchedule();

		System.out.println("Who would you like to schedule: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		System.out.println("What day would you like to schedule them: ");
		Scanner sc = new Scanner(System.in);
		String day = sc.next();
		System.out.println("What time would you like to schedule them: ");
		Scanner scan = new Scanner(System.in);
		String time = scan.next();
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();

		String query = "UPDATE schedule SET who = '" + input + "' WHERE day = '" + day + "' AND time = '" + time + "'";
		java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.execute();

		rs = stmt3.executeQuery("SELECT day, time FROM schedule WHERE who = '" + input + "'");
		while (rs.next()) {
			day = rs.getString(1);
			time = rs.getString(2);

			System.out.println(input + " is schduled: " + day + " " + time);
		}
		connect = DriverManager.getConnection(host, user, pw);
		PreparedStatement s = (PreparedStatement) connect
				.prepareStatement("SELECT hours FROM schedule WHERE who = '" + input + "'");
		rs = s.executeQuery();

		int tot = 0;
		while (rs.next()) {
			int pr = rs.getInt(1);
			tot = tot + pr;
		}
		System.out.println(input + " is scheduled " + tot + " hours this week.");


	}

	public static void cleanStatus() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the room you would like to view");
		int roomnum = scanner.nextInt();
		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("That room does not exisist");
			cleanStatus();
		}
		connect = DriverManager.getConnection(host, user, pw);
		PreparedStatement s = (PreparedStatement) connect.prepareStatement("SELECT clean FROM hotel WHERE roomNum = ?");
		s.setInt(1, roomnum);
		rs = s.executeQuery();
		while (rs.next()) {
			if (rs.getInt(1) == 0) {
				System.out.println("Room: " + roomnum + " is clean");
				System.out.println("");
				System.out.println("");

			} else {
				System.out.println("Room: " + roomnum + " is dirty");
				System.out.println("");
				System.out.println("");
			}
		}
	}

	public static void handleEmployee() throws ClassNotFoundException, SQLException {
		employeeTypeScreen();
		userType = "none";
		welcomeScreen();

	}

	public static void cashOut() {
		System.out.println("cashout");
	}

	public static void manageUser() throws ClassNotFoundException, SQLException {
		System.out.println("Which employee do you want to view");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (!input.equals("Alex") && !input.equals("Alex") && !input.equals("Aaron") && !input.equals("Joe")
				&& !input.equals("Jonah") && !input.equals("Sara") && !input.equals("Sue") && !input.equals("Isaac")) {
			System.out.println("That employee does not exist");
			managerMainScreen();
		} else {
			manageUserOptions(input);
		}
	}

	public static void manageUserOptions(String input) throws SQLException, ClassNotFoundException {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. View/Update Pay Monetary/Non-Monetary");
		System.out.println("2. View Employee Value");
		System.out.println("3. View Employee Schedule");
		System.out.println("4. View Employee Personal Information");
		System.out.println("5. View Employee Job Position");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			connect = DriverManager.getConnection(host, user, pw);
			Statement stmt3 = connect.createStatement();
			rs = stmt3.executeQuery("SELECT pay FROM employee WHERE name = '" + input + "'");
			while (rs.next()) {
				int pay = rs.getInt(1);
				System.out.print(input + " makes $" + pay);
			}
			Statement stmt1 = connect.createStatement();
			rs = stmt1.executeQuery("SELECT notMoney FROM employee WHERE name = '" + input + "'");
			while (rs.next()) {
				String ben = rs.getString(1);
				System.out.println(" and gets these benefits " + ben);
			}
			System.out.println("Would you like to change their pay? (yes or no)");
			Scanner sca = new Scanner(System.in);
			String pick = sca.next();
			if (pick.equals("yes")) {
				System.out.println("What would you like to change their pay too: ");
				Scanner scann = new Scanner(System.in);
				int newPay = scann.nextInt();
				String query = "UPDATE employee SET pay = " + newPay + " WHERE name = '" + input + "'";
				java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
				preparedStmt.execute();
			} else {
				System.out.println("You selected no returning to main menu.");
			}
		} else if (choice == 2) {
			connect = DriverManager.getConnection(host, user, pw);
			Statement stmt3 = connect.createStatement();
			rs = stmt3.executeQuery("SELECT value FROM employee WHERE name = '" + input + "'");
			while (rs.next()) {
				int val = rs.getInt(1);
				System.out.println(input + " has a value to this company of $" + val);
			}
		} else if (choice == 3) {
			// scanner.close();

			printSchedule();

			// return;
		} else if (choice == 4) {
			connect = DriverManager.getConnection(host, user, pw);
			Statement stmt3 = connect.createStatement();
			rs = stmt3.executeQuery("SELECT personalInfo FROM employee WHERE name = '" + input + "'");
			while (rs.next()) {
				String pi = rs.getString(1);
				System.out.println(pi);
			}
		} else if (choice == 5) {
			connect = DriverManager.getConnection(host, user, pw);
			Statement stmt3 = connect.createStatement();
			rs = stmt3.executeQuery("SELECT title FROM employee WHERE name = '" + input + "'");
			while (rs.next()) {
				String title = rs.getString(1);
				System.out.println(input + " job title is " + title);
			}
		} else {
			System.out.println("That is not an option to be selected");
		}
	}

}
