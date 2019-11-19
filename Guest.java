package hm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Guest extends HotelMain {
	static String userType;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	final private static String host = "jdbc:mysql://localhost:3306/sys";
	final private static String user = "root";
	final private static String pw = "******";

	public static String HotelUser = "";

	public static void printDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();

		rs = statement.executeQuery("select * from hotel");

		while (rs.next())
			System.out.println(rs.getInt(1) + "  " + rs.getString(2));

	}

	public static void guestBookRoom(String uname) throws SQLException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();
		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("The room you are trying to book does not exist please try again");
			guestBookRoom(uname);
		}
		System.out.println("Please enter the amount of nights you wish to stay");
		int nights = scanner.nextInt();

		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();
		// java.sql.PreparedStatement ps = connect.prepareStatement("INSERT INTO
		// hotel(vac) where roomNum = ?");
		String query = "UPDATE hotel SET vac = 1, clean = 1, nights = ?, guest='"+uname+"' WHERE roomNum = ?";
		java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, nights);
		preparedStmt.setInt(2, roomnum);
		preparedStmt.execute();
		System.out.println("Your room " + roomnum+" has been booked for "+nights);
		HotelMain.welcomeScreen();

	}

	public static void handleGuest() throws ClassNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcom to our hotel, if you are a returning guest press 1, if you are new press 2.");
		int choice = scanner.nextInt();
		if(choice == 1) {

			System.out.println("Please enter your username: ");
			Scanner s = new Scanner(System.in);
			String uname = s.nextLine();
			System.out.println("Please enter your password: ");
			Scanner c = new Scanner(System.in);
			String pass = c.nextLine();
			verify(uname,pass);
			//HotelUser = uname;

		}

		if(choice == 2) {
			createNewAccount();

		}
		guestHomePage();
		//guestBookRoom(HotelUser);
		userType = "none";
		//welcomeScreen();
	}

	private static void guestHomePage() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 to book a room, press 2 to view your rewards");
		int choice = sc.nextInt();
		if(choice == 1) {
			guestBookRoom(HotelUser);
		}
		if(choice ==2) {
			connect = DriverManager.getConnection(host, user, pw);
			Statement stmt = connect.createStatement();

			rs = stmt.executeQuery("SELECT points FROM Guest WHERE uname = '"+HotelUser+"'" );
			while(rs.next()) {
				System.out.println("You have "+rs.getInt(1)+" points left");
			}

		}

	}

	private static void createNewAccount() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to our hotel! We are please you want to join us.");
		System.out.println("To create an account we must first get a little information...");
		System.out.println("Please enter your first name...");
		Scanner s1 = new Scanner(System.in);
		String fname = s1.nextLine();
		System.out.println("Please enter your last name");
		Scanner s2 = new Scanner(System.in);
		String lname = s2.nextLine();
		System.out.println("Please enter a username you would like");
		Scanner s3 = new Scanner(System.in);
		String uname = s3.nextLine();
		System.out.println("Please enter a password");
		Scanner s4 = new Scanner(System.in);
		String pass = s4.nextLine();
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt = connect.createStatement();

		rs = stmt.executeQuery("SELECT COUNT(*) FROM Guest WHERE uname = '"+uname+"'" );
		while(rs.next()) {
			if(rs.getInt(1)!=0) {
				System.out.println("Sorry someone has already taken that user name, please start over");
				createNewAccount();
			}
		}

		Statement stmt2 = connect.createStatement();
		String q = "INSERT INTO Guest (fname, lname, uname, pass) VALUES ('"+fname+"','"+lname+"','"+uname+"','"+pass+"');";
		stmt2.executeUpdate(q);

		System.out.println("Your user name and account has been created, we would like to gather some more information");
		System.out.println("Please enter your email");
		Scanner s5 = new Scanner(System.in);
		String email = s5.nextLine();
		System.out.println("Please enter your phone number");
		Scanner s6 = new Scanner(System.in);
		String phone = s6.nextLine();
		System.out.println("Any special prefrences? (bed size, pool, number of beds, smoking etc...)");
		Scanner s7 = new Scanner(System.in);
		String pref = s7.nextLine();

		Statement stmt3 = connect.createStatement();
		String q1 = "UPDATE Guest SET email = '"+email+"', phone = '"+phone+"', pref = '"+pref+"', points = 0 WHERE uname = '"+uname+"'";


		stmt3.executeUpdate(q1);

		System.out.println("Welcom "+fname+"! So far you have zero points but after your first stay they will add up! You can log in at any time to see how many points you have. Remember, every 10,000 points is a free night!");
		HotelUser=uname;


	}

	private static void verify(String uname, String pass) throws SQLException {
		// TODO Auto-generated method stub
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();

		rs = stmt3.executeQuery("SELECT COUNT(*) FROM Guest WHERE uname = '"+uname+"' and pass = '"+pass+"'" );
		while(rs.next()) {
			if(rs.getInt(1)==1) {
				System.out.println("Welcome back "+uname);
				HotelUser = uname;

			}
			else {
				System.out.println("Sorry the username or password you entered is inccorect");
			}

		}




	}

	public static void addPoints(int roomnum, String g) throws SQLException {
		// TODO Auto-generated method stub
		int nights=0;
		int points=0;
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();

		rs = stmt3.executeQuery("SELECT nights FROM hotel WHERE roomNum = " + roomnum + "");
		while (rs.next()) {
			nights = rs.getInt(1);
		}
		rs = stmt3.executeQuery("SELECT points FROM Guest WHERE uname = '" + g + "'");
		while (rs.next()) {
			points = points+ rs.getInt(1);
		}
		points = points + (nights*10);

		System.out.println("you have "+points+" points");
		String query = "UPDATE Guest SET points = '"+points+"' WHERE uname = '"+g+"'";
		java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.execute();

	}
}
