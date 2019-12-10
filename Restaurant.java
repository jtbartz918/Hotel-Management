

import java.text.DecimalFormat;
import java.util.Scanner;

public class Restaurant {
	private static boolean open = false;
	private static MenuItem[] menuEntrees = new MenuItem[5];
	private static MenuItem[] menuSides = new MenuItem[5];
	private static MenuItem[] menuDesserts = new MenuItem[5];
	private static MenuItem[] menuNADrinks = new MenuItem[5];
	private static MenuItem[] menuDrinks = new MenuItem[5];
	
	//Number of choices for the day.
	private static int e, s, d, n, a;

	private static Table[] tables = {new Table(2, 1), new Table(2, 2), new Table(4, 3), new Table(4, 4), new Table(4, 5), new Table(6, 6), new Table(8, 7)}; 
	private static double[] bills = new double[7];
	private static String[] orders = new String[7];
	
	private static MenuItem[] entreeChoices = {
		new MenuItem("Steak", "Entree", false, 15.95, 20),
		new MenuItem("Carbonara", "Entree", false, 12.95, 20),
		new MenuItem("Caesar Salad", "Entree", true, 9.95, 20),
		new MenuItem("Cheese Pizza", "Entree", true, 15.95, 20),
		new MenuItem("Shoulder Roast", "Entree", false, 15.95, 20)
	};
	
	private static MenuItem[] sideChoices = {
			new MenuItem("Mac & Cheese", "Side", true, 5.95, 20),
			new MenuItem("Baked Potato", "Side", true, 3.95, 20),
			new MenuItem("Fries", "Side", true, 4.95, 20),
			new MenuItem("Small Side Salad", "Side", true, 3.95, 20),
			new MenuItem("Coleslaw", "Side", true, 1.95, 20)
		};
	
	private static MenuItem[] dessertChoices = {
			new MenuItem("Chocolate Cake", "Dessert", true, 5.95, 20),
			new MenuItem("Oreo Cheesecake", "Dessert", true, 5.95, 20),
			new MenuItem("Carrot Cake", "Dessert", true, 5.95, 20),
			new MenuItem("Brownie a la Mode", "Dessert", true, 5.95, 20),
			new MenuItem("Chocolate Explosion", "Dessert", true, 5.95, 20)
		};
	
	private static MenuItem[] nonAlcoholicChoices = {
			new MenuItem("Soda", "NonAlcoholicDrink", true, 1.95, 20),
			new MenuItem("Sweet Tea", "NonAlcoholicDrink", true, 1.95, 20),
			new MenuItem("Unsweetened Tea", "NonAlcoholicDrink", true, 1.95, 20),
			new MenuItem("Coffee", "NonAlcoholicDrink", true, 1.95, 20),
			new MenuItem("Water", "NonAlcoholicDrink", true, 0.00, 20)
		};
	
	private static MenuItem[] alcoholicChoices = {
			new MenuItem("Old Fashon", "Entree", true, 8.95, 20),
			new MenuItem("Exile Ruthie", "Entree", true, 5.95, 20),
			new MenuItem("Bud Light", "Entree", true, 2.95, 20),
			new MenuItem("House Cocktail", "Entree", true, 7.95, 20),
			new MenuItem("Martini", "Entree", true, 7.95, 20)
		};
	
	static DecimalFormat df = new DecimalFormat("#.00"); 
	
	public static void main(String[] args) {
		runRestaurant();
	}
	
	public void openRestaurant() {
		open = true;
	}
	
	public static void runRestaurant() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		if(!open) {
			System.out.println("The restaurant is not open, would you like to open the restaurant for business?\nEnter 1 for yes or 0 for no");
			choice = scanner.nextInt();
			if(choice == 1) {
				open = true;
			} else {
				System.out.println("The restaurant is not open. Cannot continue with restaurant functions.");
				scanner.close();
				return;
			}
		}
		while(choice != 6) {
			printRestaurantScreen();
			choice = scanner.nextInt();
			if(choice == 1) {
				setMenu();
			} else if (choice == 2) {
				seatGuest();
			} else if (choice == 3) {
				takeOrder(); 
			} else if (choice == 4) {
				billGuest();
			} else if (choice == 5) {
				openTable();
			} else if (choice != 6) {
				System.out.println("Not a valid option, please choose another option.");
			}
		}	
		open = false;
	}
	
	public static void printRestaurantScreen() {
		System.out.println("\n1. Set Menu");
		System.out.println("2. Seat Guest");
		System.out.println("3. Take Order");
		System.out.println("4. Bill Guest");
		System.out.println("5. Open Table");
		System.out.println("6. Return to Employee Screen and Close Restaurant");
		System.out.println("Enter your choice:");
	}
	
	public static void setMenu() {
		chooseEntrees();
		chooseSides();
		chooseDesserts();
		chooseNonAlcoholicDrinks();
		chooseAlcoholicDrinks();
	}
	
	public static void chooseEntrees() {
		e = 0;
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean finished = false;
		while(!finished) {
			System.out.println("Select an entree for the day:");
			//print options
			for(int i = 0; i < entreeChoices.length; i++) {
				System.out.println((i+1) +". " + entreeChoices[i].name + ". Vegetarian option: " + entreeChoices[i].vegetarianOption);
			}
			choice = scanner.nextInt();
			
			//handle choice
			menuEntrees[e] = entreeChoices[(choice-1)];
			e++;
			
			System.out.println("Choose another option? Enter 1 for yes and 0  for no");
			choice = scanner.nextInt();
			if(choice == 0) {
				finished = true;
			}
		}
	}
	
	public static void chooseSides() {
		s = 0;
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean finished = false;
		while(!finished) {
			System.out.println("Select a side for the day:");
			//print options
			for(int i = 0; i < sideChoices.length; i++) {
				System.out.println((i+1) +". " + sideChoices[i].name);
			}
			choice = scanner.nextInt();
			
			//handle choice
			menuSides[s] = sideChoices[(choice-1)];
			s++;
			
			System.out.println("Choose another option? Enter 1 for yes and 0  for no");
			choice = scanner.nextInt();
			if(choice == 0) {
				finished = true;
			}
		}
	}
	
	public static void chooseDesserts() {
		d = 0;
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean finished = false;
		while(!finished) {
			System.out.println("Select a dessert for the day:");
			//print options
			for(int i = 0; i < dessertChoices.length; i++) {
				System.out.println((i+1) +". " + dessertChoices[i].name);
			}
			choice = scanner.nextInt();
			
			//handle choice
			menuDesserts[d] = dessertChoices[(choice-1)];
			d++;
			
			System.out.println("Choose another option? Enter 1 for yes and 0  for no");
			choice = scanner.nextInt();
			if(choice == 0) {
				finished = true;
			}
		}
	}

	public static void chooseNonAlcoholicDrinks() {
		n=0;
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean finished = false;
		while(!finished) {
			System.out.println("Select a non-alcoholic drink for the day:");
			//print options
			for(int i = 0; i < nonAlcoholicChoices.length; i++) {
				System.out.println((i+1) +". " + nonAlcoholicChoices[i].name);
			}
			choice = scanner.nextInt();

			//handle choice
			menuNADrinks[n] = nonAlcoholicChoices[(choice-1)];
			n++;
			
			System.out.println("Choose another option? Enter 1 for yes and 0  for no");
			choice = scanner.nextInt();
			if(choice == 0) {
				finished = true;
			}
		}
	}

	public static void chooseAlcoholicDrinks() {
		a = 0;
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean finished = false;
		while(!finished) {
			System.out.println("Select an alcoholic drink for the day:");
			//print options
			for(int i = 0; i < alcoholicChoices.length; i++) {
				System.out.println((i+1) +". " + alcoholicChoices[i].name);
			}
			choice = scanner.nextInt();

			//handle choice
			menuDrinks[a] = alcoholicChoices[(choice-1)];
			a++;
			
			System.out.println("Choose another option? Enter 1 for yes and 0  for no");
			choice = scanner.nextInt();
			if(choice == 0) {
				finished = true;
			}
		}
	}
	
	public static void seatGuest() {
		System.out.println("Enter number of guests to seat:");
		Scanner scanner = new Scanner(System.in);
		int guests = scanner.nextInt();
		for(int i = 0; i < tables.length; i++) {
			if(tables[i].seating >= guests) {
				if(tables[i].checkAvailability()) {
					System.out.println("Table " + tables[i].tableNumber + " is open and can seat " + tables[i].seating);
				}
			}
		}
		System.out.println("Enter the table number which the guests will sit:");
		int table = scanner.nextInt();
		System.out.println(table);
		table--;
		tables[table].useTable(guests);
		System.out.println(guests + " guest(s) have been seated at table " + (table + 1));
	}
	
	public static void printChoices(String type) {
		if(type.equals("Entrees")) {
			for(int i = 0; i < e; i++) {
				System.out.println((i+1) + ". " + menuEntrees[i].name + ": $" + menuEntrees[i].price);
			}
		} else if(type.equals("Sides")) {
			for(int i = 0; i < s; i++) {
				System.out.println((i+1) + ". " + menuSides[i].name + ": $" + menuSides[i].price);
			}
		} else if(type.equals("Desserts")) {
			for(int i = 0; i < d; i++) {
				System.out.println((i+1) + ". " + menuDesserts[i].name + ": $" + menuDesserts[i].price);
			}
		} else if(type.equals("Drinks")) {
			for(int i = 0; i < n; i++) {
				System.out.println((i+1) + ". " + menuNADrinks[i].name + ": $" + menuNADrinks[i].price);
			}
		} else if(type.contentEquals("AlcoholicDrinks")) {
			for(int i = 0; i < a; i++) {
				System.out.println((i+1) + ". " + menuDrinks[i].name + ": $" + menuDrinks[i].price);
			}
		}
	}

	public static void takeOrder() {
		double total = 0;
		Scanner scanner = new Scanner(System.in);
		
		
		boolean tableUsed = false;
		for(int i = 0; i < tables.length; i++) {
			if(!tables[i].checkAvailability()) {
				tableUsed = true;
				System.out.println("Table " + tables[i].tableNumber + " is currently seating " + tables[i].numberSeated + " guests");
			}
		}
		if(!tableUsed) {
			System.out.println("No guests are currently seated at a table. Cannot take an order.");
			return;
		}
		
		System.out.println("Enter the table to take an order for:");
		int choice;
		int table = scanner.nextInt();
		table--;
		orders[table] = "";
		
		for(int i = 0; i < tables[table].numberSeated; i++) {
			orders[table] += "Guest " + (i+1) + "'s order:\n"; 
			
			printChoices("Entrees");
			System.out.println("Enter entree choice for guest " + (i+1) + "\nEnter '0' for none:");
			choice = scanner.nextInt();
			if(choice != 0) {
				orders[table] += menuEntrees[choice-1].name + "\n";
				total += menuEntrees[choice-1].price;
			} 
			
			printChoices("Sides");
			System.out.println("Enter side choice for guest " + (i+1) + "\nEnter '0' for none:");
			choice = scanner.nextInt();
			if(choice != 0) {
				orders[table] += menuSides[choice-1].name + "\n";
				total += menuSides[choice-1].price;
			} 
			
			printChoices("Desserts");
			System.out.println("Enter dessert choice for guest " + (i+1) + "\nEnter '0' for none:");
			choice = scanner.nextInt();
			if(choice != 0) {
				orders[table] += menuDesserts[choice-1].name + "\n";
				total += menuDesserts[choice-1].price;
			} 
			
			printChoices("Drinks");
			System.out.println("Enter drink choice for guest " + (i+1) + "\nEnter '0' for none:");
			choice = scanner.nextInt();
			if(choice != 0) {
				orders[table] += menuNADrinks[choice-1].name + "\n";
				total += menuNADrinks[choice-1].price;
			} 
			
			printChoices("AlcoholicDrinks");
			System.out.println("Enter alcoholic drink choice for guest " + (i+1) + "\nEnter '0' for none:");
			choice = scanner.nextInt();
			if(choice != 0) {
				orders[table] += menuDrinks[choice-1].name + "\n";
				total += menuDrinks[choice-1].price;
			} 
		}
		System.out.println("Enter any notes for this order:");
		orders[table] += getNotes();
		orders[table] += "\n";
		
		bills[table] = total;
		System.out.println("Order placed:\n" + orders[table]);
	}
	
	public static String getNotes() {
		Scanner sc = new Scanner(System.in);
		String notes = sc.nextLine();
		return notes;
	}
	
	public static void billGuest() {
		boolean tableUsed = false;
		for(int i = 0; i < tables.length; i++) {
			if(!tables[i].checkAvailability()) {
				tableUsed = true;
				System.out.println("Table " + tables[i].tableNumber + " currently owes $" + df.format(bills[i]));
			}
		}
		if(!tableUsed) {
			System.out.println("No guests are currently seated at a table. Cannot bill guests");
			return;
		}
		
		System.out.println("Enter the table you would like to bill:");
		Scanner scanner = new Scanner(System.in);
		int table = scanner.nextInt();
		System.out.println("Bill to guests' room? Enter 1 for yes or 0 for no");
		int answer = scanner.nextInt();
		if(answer == 1) {
			System.out.println("Enter guests room number:");
			answer = scanner.nextInt();
			System.out.println("Table " + table + "'s bill for $" + df.format(bills[table-1]) + " has been billed to room " + answer);
			//Send the bill to the room
			
			bills[table-1] = 0;
		}
	}
	
	public static void openTable() {
		boolean tableUsed = false;
		for(int i = 0; i < tables.length; i++) {
			if(!tables[i].checkAvailability()) {
				tableUsed = true;
				System.out.println("Table " + tables[i].tableNumber + " is currently seating guests");
			}
		}
		if(!tableUsed) {
			System.out.println("No guests are currently seated at a table.");
			return;
		}
		
		System.out.println("Enter table number to open:");
		Scanner scanner = new Scanner(System.in);
		int table = scanner.nextInt();
		System.out.println("Table " + table + " is now available to seat guests");
		table--;
		tables[table].openTable();
	}
}

