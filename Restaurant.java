
import java.util.Scanner;

public class Restaurant {
	private boolean open = false;
	private Object Menu;
	private Table[] tables = {new Table(2, 1), new Table(2, 2), new Table(4, 3), new Table(4, 4), new Table(4, 5), new Table(6, 6), new Table(8, 7)}; 
	
	public void openRestaurant() {
		open = true;
	}
	
	public void runRestaurant() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		if(!open) {
			System.out.println("The restaurant is not open, would you like to open the restaurant for business?\nEnter 1 for yes or 0 for no");
			choice = scanner.nextInt();
			if(choice == 1) {
				open = true;
			} else {
				System.out.println("The restaurant is not open. Cannot continue");
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
		scanner.close();
	}
	
	public void printRestaurantScreen() {
		System.out.println("1. Set Menu");
		System.out.println("2. Seat Guest");
		System.out.println("3. Take Order");
		System.out.println("4. Bill Guest");
		System.out.println("5. Open Table");
		System.out.println("6. Return to Employee Screen");
		System.out.println("Enter your choice:");
	}
	
	public void setMenu() {
		chooseEntrees();
		chooseSides();
		chooseDesserts();
		chooseNonAlcoholicDrinks();
		chooseAlcoholicDrinks();
	}
	
	public MenuItem chooseEntrees() {
		return null;
	}
	
	public MenuItem chooseSides() {
		
		return null;
	}
	
	public MenuItem chooseDesserts() {
		
		return null;
	}

	public MenuItem chooseNonAlcoholicDrinks() {
		
		return null;
	}

	public MenuItem chooseAlcoholicDrinks() {
		
		return null;
	}
	
	public void seatGuest() {
		System.out.println("Enter number of guests to seat:");
		Scanner scanner = new Scanner(System.in);
		int guests = scanner.nextInt();
		for(int i = 0; i < tables.length; i++) {
			if(tables[i].seating <= guests) {
				if(tables[i].checkAvailability()) {
					System.out.println("Table " + tables[i].tableNumber + "is open and can seat " + tables[i].seating);
				}
			}
		}
	}
	
	public void takeOrder() {
		
	}
	
	public void billGuest() {
		
	}
	
	public void openTable() {
		System.out.println("Enter table number to open:");
	}
}
