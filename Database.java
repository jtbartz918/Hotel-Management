import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Database extends HotelMain{
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

	public static void printSchedule() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(host, user, pw);
		statement = connect.createStatement();

		rs = statement.executeQuery("select * from schedule");

		while (rs.next())
			System.out.println(rs.getInt(1) + "  " + rs.getString(2));

	}
}
