import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;
import com.mysql.jdbc.PreparedStatement;

public class Employee extends HotelMain {

	static String userType;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	private static ResultSet rs2 = null;
	static ArrayList<String> desc = new ArrayList<String>();
	static ArrayList<Integer> call = new ArrayList<Integer>();
	static String description = "";

	final private static String host = "jdbc:mysql://localhost:3306/sys";
	final private static String user = "root";

	final private static String pw = "12345678";

	static ArrayList<String> marketP = new ArrayList<String>();
	static ArrayList<String> hrP = new ArrayList<String>();
	static ArrayList<String> managerP = new ArrayList<String>();
	static ArrayList<String> fdP = new ArrayList<String>();
	static ArrayList<String> securityP = new ArrayList<String>();
	static ArrayList<String> chefP = new ArrayList<String>();
	static ArrayList<String> lgP = new ArrayList<String>();
	static ArrayList<String> valetP = new ArrayList<String>();
	static ArrayList<String> serverP = new ArrayList<String>();

//	public static void employeeTypeScreen() throws ClassNotFoundException, SQLException {

	// System.out.println("Menu:");
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("1. Manager");
//		System.out.println("2. HR");
//		System.out.println("3. Front Desk");
//		System.out.println("4. Security");
//		System.out.println("5. Restaurant");
//		int choice = scanner.nextInt();
//		if (choice == 1) {
//			// scanner.close();
//			managerMainScreen();
//			return;
//		} else if (choice == 2) {
//			// scanner.close();
//			hrMainScreen();
//			// return;
//		} else if (choice == 3) {
//			employeeMainScreen();
//			// return;
//		} else if (choice == 4) {
//			employeeMainScreen();
//		} else if (choice == 5) {
//			Restaurant.runRestaurant();
//		} else {
//			System.out.println("Input is not a user type.");
//		}
//	}

	public static void employeeMainScreen() throws ClassNotFoundException, SQLException {
		description = ". Front Desk";
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
		Statement stmt = connect.createStatement();
		String query2 = "SELECT COUNT(*) FROM Guest WHERE uname = '" + uname + "'";
		rs = stmt.executeQuery(query2);
		while (rs.next()) {
			if (rs.getInt(1) == 1) {
				String query = "UPDATE hotel SET vac = 1, clean = 1, nights = ?, guest='" + uname
						+ "' WHERE roomNum = ?";
				java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
				preparedStmt.setInt(1, nights);
				preparedStmt.setInt(2, roomnum);
				preparedStmt.execute();
			} else {
				System.out.println(
						"That guest username is not in the system, please check the database or ask the guest for thier username agian");
			}
		}

	}

	public static void managerMainScreen() throws ClassNotFoundException, SQLException {
		description = ". Manager";
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

//	public static void hrMainScreen() throws ClassNotFoundException, SQLException {
//		description = ". HR";
//		System.out.println("Menu:");
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("1. Hire an Employee");
//		System.out.println("2. Fire an Employee");
//		System.out.println("3. Schedule an Employee");
//		int choice = scanner.nextInt();
//		if (choice == 1) {
//			// scanner.close();
//			hireEmp();
//			// return;
//		} else if (choice == 2) {
//			// scanner.close();
//			fireEmp();
//			// return;
//		} else if (choice == 3) {
//			// scanner.close();
//			scheduleEmployee();
//			// return;
//		} else {
//			System.out.println("Input is not a user type.");
//		}
//	}

	public static void hireEmp() throws SQLException, ClassNotFoundException {
		System.out.println("New Employee Hiring:");
		System.out.println("Please enter first name: ");
		Scanner scanner = new Scanner(System.in);
		String fname = scanner.next();
		System.out.println("Please enter last name: ");
		Scanner scanner1 = new Scanner(System.in);
		String lname = scanner1.next();
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt7 = connect.createStatement();
		rs = stmt7
				.executeQuery("SELECT COUNT(*) FROM employee WHERE name = '" + fname + "' AND lastN = '" + lname + "'");
		while (rs.next()) {
			if (rs.getInt(1) == 1) {
				System.out.println("That employee already works here. ");
				System.out.println(" ");
				System.out.println(" ");
				hireEmp();

			} else {
				break;
			}
		}
		System.out.println("Please enter job title: ");
		Scanner scanner2 = new Scanner(System.in);
		String title = scanner2.nextLine();
		System.out.println("Please enter agreed upon monetary compensation: ");
		Scanner scanner3 = new Scanner(System.in);
		int moneyPay = scanner3.nextInt();
		System.out.println("How many benefits were agreed upon: ");
		Scanner scanner6 = new Scanner(System.in);
		int nonMon = scanner6.nextInt();
		System.out.println("Please enter agreed upon benefits apart compensation: ");
		Scanner scanner4 = new Scanner(System.in);
		String benefit = scanner4.nextLine();
		System.out.println("Please enter personal information (address, DOB, sex(M/F/other): ");
		Scanner scanner5 = new Scanner(System.in);
		String info = scanner5.nextLine();
		connect = DriverManager.getConnection(host, user, pw);
		String query = "INSERT INTO employee (name, lastN, title, pay, notMoney, personalInfo) VALUES ('" + fname
				+ "', '" + lname + "', '" + title + "', '" + moneyPay + "', '" + benefit + "', '" + info + "')";
		java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.execute();
		int value = 0;
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();
		rs = stmt3.executeQuery("SELECT pay FROM employee WHERE name = '" + fname + "' and lastN = '" + lname + "'");
		while (rs.next()) {
			value = rs.getInt(1);
		}
		value += nonMon * 1000;
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt5 = connect.createStatement();
		String query1 = "UPDATE employee SET value = '" + value + "' WHERE name = '" + fname + "' AND lastN = '" + lname
				+ "'";
		java.sql.PreparedStatement preparedStmt1 = connect.prepareStatement(query1);
		preparedStmt1.execute();
		System.out.println(fname + " " + lname + "'s total compensation is: $" + value);
		System.out.println("");
		System.out.println("");
		System.out.println("The current projects going on are: ");
		System.out.println("Advertising/Commercials (Marketing) ");
		System.out.println("Internship Candidate Reachouts (HR) ");
		System.out.println("Staff Effciency Measurement (Manager) ");
		System.out.println("Customer Satisfaction Survey (FrontDesk) ");
		System.out.println("Guest Safety Training (Security) ");
		System.out.println("Kitchen Appliance Inspections (Chef) ");
		System.out.println("First Aid Certification (Lifeguard) ");
		System.out.println("Appropriate Guest Vehicle Operation (Valet) ");
		System.out.println("Setting Tables (Server) ");
		System.out.println("The current projects going on are: ");
//		for(int i = 0; i < ) {
//			
//		}
		System.out.println("Would you like to add them to a project? (yes/no)");
		Scanner scanner7 = new Scanner(System.in);
		String choice = scanner7.next();
		if (choice.equals("yes")) {
			System.out.println("Would you like to add them to an existing project? (yes/no)");
			Scanner scanner8 = new Scanner(System.in);
			String bool = scanner8.next();
			if (bool.equals("yes")) {
				addgroupProj(fname, lname);
				System.out.println("Menu: ");
				iFrontEnd demo = new displayMenu();
				demo.displayCommands();
				}
			
//			 else if(bool.equals("no")){
//				createGroup();
//				addgroupProj(fname, lname);
//				System.out.println("Menu: ");
//				iFrontEnd demo = new displayMenu();
//				demo.displayCommands();
//			}
		}
}

//	public static void createGroup() {
//		ArrayList<String> list = new ArrayList<String>();
//		// List<String> due = new ArrayList<>();
//		//List<String> pos = new ArrayList<>();
//		int c = 0;
//		// List<String> groupN = new ArrayList<>();
//		System.out.println("What would you like to call this group project? ");
//		Scanner s = new Scanner(System.in);
//		// groupN.add(s.nextLine());
//		String groupN = s.nextLine();
//		System.out.println("What is the due date for this project ");
//		Scanner sc = new Scanner(System.in);
//		// due.add(sc.nextLine());
//		String due = sc.nextLine();
////		System.out.println("What job title is this group for? ");
////		Scanner sca = new Scanner(System.in);
////		pos.add(sca.nextLine());
//		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();		
//		list.add(groupN);
//		list.add(due);
//		lists.add(list);
////		for (ArrayList<String> group:lists) {
////				c++;
////				System.out.println(group);
////		}
//		for (int i = 0; i < lists.size(); i++) { 
//            for (int j = 0; j < lists.get(i).size(); j++) { 
//                System.out.print(lists.get(i).get(j) + " "); 
//            } 
//		}
//	}

	public static void fireEmp() throws SQLException, ClassNotFoundException {
		System.out.println("New Employee Hiring:");
		System.out.println("Which employee is being fired (please enter full name of employee): ");
		Scanner scanner = new Scanner(System.in);
		String fname = scanner.next();
		String lname = scanner.next();
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();
		rs = stmt3
				.executeQuery("SELECT COUNT(*) FROM employee WHERE name = '" + fname + "' and lastN = '" + lname + "'");
		while (rs.next()) {
			if (rs.getInt(1) == 1) {
				removegroupProj(fname, lname);
				connect = DriverManager.getConnection(host, user, pw);
				String query = "DELETE FROM employee WHERE name = '" + fname + "' AND lastN = '" + lname + "'";
				java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
				preparedStmt.execute();
				System.out.println("Menu: ");
				iFrontEnd demo = new displayMenu();
				demo.displayCommands();

			} else {
				System.out.println("That employee is not hired here.");
				System.out.println("Did you want to retype the name or enter a new name? (yes/no) ");
				System.out.println(" ");
				Scanner scan = new Scanner(System.in);
				String ans = scan.next();
				if (ans.equals("yes")) {
					fireEmp();
				}
			}

		}

	}

	public static void addgroupProj(String fname, String lname) throws SQLException {
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();
		rs = stmt3.executeQuery("SELECT title FROM employee WHERE name = '" + fname + "' and lastN = '" + lname + "'");
		while (rs.next()) {
			if (rs.getString(1).equals("Marketing")) {
				marketP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");
				for (int i = 0; i < marketP.size(); i++) {
					System.out.println(marketP.get(i));
				}
			} else if (rs.getString(1).equals("HR")) {
				hrP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < hrP.size(); i++) {
					System.out.println(hrP.get(i));
				}
			} else if (rs.getString(1).equals("Manager")) {
				managerP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");
				for (int i = 0; i < managerP.size(); i++) {
					System.out.println(managerP.get(i));
				}
			} else if (rs.getString(1).equals("FrontDesk")) {
				fdP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < fdP.size(); i++) {
					System.out.println(fdP.get(i));
				}
			} else if (rs.getString(1).equals("Security")) {
				securityP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < securityP.size(); i++) {
					System.out.println(securityP.get(i));
				}
			} else if (rs.getString(1).equals("Chef")) {
				chefP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < chefP.size(); i++) {
					System.out.println(chefP.get(i));
				}
			} else if (rs.getString(1).equals("Lifeguard")) {
				lgP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < lgP.size(); i++) {
					System.out.println(lgP.get(i));
				}
			} else if (rs.getString(1).equals("Valet")) {
				valetP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < valetP.size(); i++) {
					System.out.println(valetP.get(i));
				}
			} else if (rs.getString(1).equals("Server")) {
				serverP.add(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < serverP.size(); i++) {
					System.out.println(serverP.get(i));
				}
			} else {
				break;
			}
		}
	}

	public static void removegroupProj(String fname, String lname) throws SQLException {
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt3 = connect.createStatement();
		rs = stmt3.executeQuery("SELECT title FROM employee WHERE name = '" + fname + "' and lastN = '" + lname + "'");
		while (rs.next()) {
			if (rs.getString(1).equals("Marketing")) {
				marketP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < marketP.size(); i++) {
					System.out.println(marketP.get(i));
				}
			} else if (rs.getString(1).equals("HR")) {
				hrP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < hrP.size(); i++) {
					System.out.println(hrP.get(i));
				}
			} else if (rs.getString(1).equals("Manager")) {
				managerP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < managerP.size(); i++) {
					System.out.println(managerP.get(i));
				}
			} else if (rs.getString(1).equals("FrontDesk")) {
				fdP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < fdP.size(); i++) {
					System.out.println(fdP.get(i));
				}
			} else if (rs.getString(1).equals("Security")) {
				securityP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < securityP.size(); i++) {
					System.out.println(securityP.get(i));
				}
			} else if (rs.getString(1).equals("Chef")) {
				chefP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < chefP.size(); i++) {
					System.out.println(chefP.get(i));
				}
			} else if (rs.getString(1).equals("Lifeguard")) {
				lgP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");
				for (int i = 0; i < lgP.size(); i++) {
					System.out.println(lgP.get(i));
				}
			} else if (rs.getString(1).equals("Valet")) {
				valetP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < valetP.size(); i++) {
					System.out.println(valetP.get(i));
				}
			} else if (rs.getString(1).equals("Server")) {
				serverP.remove(fname + " " + lname);
				System.out.println("Current Members of this Group are: ");

				for (int i = 0; i < serverP.size(); i++) {

					System.out.println(serverP.get(i));
				}
			} else {
				break;
			}
		}
	}

	public static void checkOut() throws SQLException, ClassNotFoundException {
		int deduction = 0;
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
		// System.out.println("Would you like to use any points?");
		// System.out.println("Enter 1 for yes or 2 for no");
		// Scanner sc1 = new Scanner(System.in);
		// int choice = sc1.nextInt();
//		if(choice == 1) {
//			int newTotal=0;
//			newTotal = getTotalPrice(roomnum)-deductPoints(roomnum);
//			System.out.println("After using your points your total is "+ newTotal);
//			//deduction=deductPoints(roomnum);
//		}

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
			Guest.addPoints(roomnum, g);
			String query = "UPDATE hotel SET vac = 0, clean = 0, nights = 0 WHERE roomNum = ?";
			java.sql.PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, roomnum);

			preparedStmt.execute();

		} else {
			System.out.println("payment info was not verified, please try again.");
			checkOut();
		}
	}

	private static int deductPoints(int roomnum) throws SQLException {
		// TODO Auto-generated method stub
		String guest = null;
		// System.out.println("in deduct points");
		connect = DriverManager.getConnection(host, user, pw);
		Statement stmt = connect.createStatement();

		rs2 = stmt.executeQuery("SELECT guest FROM hotel WHERE roomNum = " + roomnum);
		while (rs.next()) {
			guest = rs.getString(guest);
		}
		rs = stmt.executeQuery("SELECT points FROM Guest WHERE uname = '" + guest + "'");
		int points = 0;
		while (rs.next()) {
			points = rs.getInt(1);
			// return getTotalPrice(roomnum)-points;
			return points;
		}
		return 0;

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
				.prepareStatement("SELECT nights, bill FROM hotel WHERE roomNum = '" + roomnum + "'");
		rs = s.executeQuery();
		while (rs.next()) {
			int pr2 = (rs.getInt("nights") * 300) + rs.getInt("bill");
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
		System.out.println("Menu:");
		Scanner scr = new Scanner(System.in);
		System.out.println("1. View Schedule");
		System.out.println("2. Update schedule");
		int choice = scr.nextInt();
		if (choice == 1) {
			// scanner.close();
			Database.printSchedule();
			return;
		} else {
			int hours = 0;
			System.out.println("Schedule: ");
			Database.printSchedule();
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
			String query = "UPDATE schedule SET who = '" + input + "' WHERE day = '" + day + "' AND time = '" + time
					+ "'";
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
		iFrontEnd demo = new displayMenu();
		demo.displayCommands();
		userType = "none";
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

			Database.printSchedule();

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