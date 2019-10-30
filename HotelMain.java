package hm;

import java.util.*;
import java.util.Scanner;

public class HotelMain {
	static String userType;
	static HashMap<Integer, Boolean> room = new HashMap<>();

	public static void main(String[] args) {
		welcomeScreen();
	}

	public static void welcomeScreen() {
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

	public static void employeeMainScreen() {
		System.out.println("Menu:");
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. See what rooms are availiable");
		System.out.println("2. Schedule an employee");
		System.out.println("3. Process payment");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// scanner.close();
			checkRoomStatus();
			return;
		} else if (choice == 2) {
			// scanner.close();
			scheduleEmployee();
			//return;
		} else if (choice == 3) {
			cashOut();
			//return;
		} else {
			System.out.println("Input is not a user type.");
		}
	}

	public static void guestBookRoom() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter your full name:");
		String name = scanner.next();
		System.out.println("Please enter the room you would like to book");
		int roomnum = scanner.nextInt();
		System.out.println("Please enter the number of nights you would like to stay:");
		int numNights = scanner.nextInt();
		System.out.println("Please enter the day you would like the room (mm/dd/yyyy)");
		String date = scanner.next();
		room.put(roomnum, true);
		//scanner.close();
		System.out.println(room);
	}

	public static void handleGuest() {
		guestBookRoom();
		userType = "none";
		welcomeScreen();
	}

	public static void checkRoomStatus() {
		System.out.println("check room status");
		System.out.println(room);
	}

	public static void scheduleEmployee() {
		System.out.println("schedule");
	}

	public static void cashOut() {
		System.out.println("cashout");
	}

	public static void handleEmployee() {
		employeeMainScreen();
		userType = "none";
		welcomeScreen();

	}

}
