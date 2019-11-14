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
	final private static String pw = "12345678";

	public static void printDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();

		rs = statement.executeQuery("select * from hotel");

		while (rs.next())
			System.out.println(rs.getInt(1) + "  " + rs.getString(2));

	}

	public static void guestBookRoom() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();
		if (roomnum != 100 && roomnum != 110 && roomnum != 120 && roomnum != 130 && roomnum != 140 && roomnum != 150
				&& roomnum != 160 && roomnum != 170 && roomnum != 180 && roomnum != 190 && roomnum != 200) {
			System.out.println("The room you are trying to book does not exist please try again");
			guestBookRoom();
		}
		System.out.println("Please enter the amount of nights you wish to stay");
		int nights = scanner.nextInt();

		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();
		// java.sql.PreparedStatement ps = connect.prepareStatement("INSERT INTO
		// hotel(vac) where roomNum = ?");
		String query = "UPDATE hotel SET vac = 1, clean = 1, nights = ? WHERE roomNum = ?";
		java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, nights);
		preparedStmt.setInt(2, roomnum);
		preparedStmt.execute();

	}
	
	public static void handleGuest() throws ClassNotFoundException, SQLException {
		guestBookRoom();
		userType = "none";
		welcomeScreen();
	}
}
