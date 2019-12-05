
import java.text.DecimalFormat;
import java.util.Scanner;

public class SupplyManagement {
	static DecimalFormat df = new DecimalFormat("#.00");
	
	public static boolean suppliesInit = false;
	
	//Supply categories 
	private static MenuItem[] entreeChoices = new MenuItem[15];
	private static MenuItem[] sideChoices = new MenuItem[15];
	private static MenuItem[] dessertChoices = new MenuItem[15];
	private static MenuItem[] nonAlcoholicChoices = new MenuItem[15];
	private static MenuItem[] alcoholicChoices = new MenuItem[15];
	private static Item[] cleaningSupplies = new Item[15];
	private static Item[] frontDeskSupplies = new Item[15];
	private static Item[] otherSupplies = new Item[15];
	private static Order[] orders = new Order[15];
	private static int numO = 0;
	private static int orderNumber = 1000;
	private static double totalHotelBilled = 0;
	//Current number of items in the system
	private static int ec, sc, dc, nc, ac, c, f, o;
	
	public static void main(String[] args) {
		handleSupplyPage();
	}
	
	public static void printSupplyScreen() {
		System.out.println("\n1. View Supplies");
		System.out.println("2. Orders");
		System.out.println("3. Update Stock");
		System.out.println("4. Updated Item Information");
		System.out.println("5. Add a New Item to the System");
		System.out.println("6. Remove an Item from the System");
		System.out.println("7. Exit Supply Management");
		System.out.println("Enter your choice:");
	}
	
	public static void printOrderScreen() {
		System.out.println("1. Place an Order");
		System.out.println("2. Finalize an Order");
		System.out.println("3. View Orders");
		System.out.println("4. Return to Supply Menu");
	}
	
	public static void handleSupplyPage() {
		if(!suppliesInit) {
			initSupplies();
		}
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		while(choice != 7) {
			printSupplyScreen();
			choice = scanner.nextInt();
			if(choice == 1) {
				viewSuppliesWithStock();
			} else if (choice == 2) {
				handleOrders();
			} else if (choice == 3) {
				updateStock();
			} else if (choice == 4) {
				updateItemInfo();
			} else if (choice == 5) {
				newItem();
			} else if (choice == 6) {
				removeItem();
			} else if (choice != 7) {
				System.out.println("Not a valid option, please choose another option.");
			}
		}	
	}
	
	public static void handleOrders() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		while(choice != 4) {
			printOrderScreen();
			choice = scanner.nextInt();
			if(choice == 1) {
				placeOrder();
			} else if(choice == 2) {
				finalizeOrder();
			} else if(choice == 3) {
				viewOrders();
			} else if(choice != 4) {
				System.out.println("Not a valid option, please choose another option.");
			}
		}
	}
	
	public static void initSupplies() {
		//Init entrees
		entreeChoices[0] = new MenuItem("Steak", "Entree", false, 15.95, 2.00, 20);
		entreeChoices[1] = new MenuItem("Carbonara", "Entree", false, 12.95, 2.00, 20);
		entreeChoices[2] = new MenuItem("Caesar Salad", "Entree", true, 9.95, 2.00, 20);
		entreeChoices[3] = new MenuItem("Cheese Pizza", "Entree", true, 15.95, 2.00, 20);
		entreeChoices[4] = new MenuItem("Shoulder Roast", "Entree", false, 15.95, 2.00, 20);
		ec = 5;
		//Init sides
		sideChoices[0] = new MenuItem("Mac & Cheese", "Side", true, 5.95, 2.00, 20);
		sideChoices[1] = new MenuItem("Baked Potato", "Side", true, 3.95, 2.00,20);
		sideChoices[2] = new MenuItem("Fries", "Side", true, 4.95, 2.00, 20);
		sideChoices[3] = new MenuItem("Small Side Salad", "Side", true, 3.95, 2.00, 20);
		sideChoices[4] = new MenuItem("Coleslaw", "Side", true, 1.95, 2.00, 20);
		sc = 5;
		//Init desserts
		dessertChoices[0] = new MenuItem("Chocolate Cake", "Dessert", true, 5.95, 2.00, 20);
		dessertChoices[1] = new MenuItem("Oreo Cheesecake", "Dessert", true, 5.95, 2.00, 20);
		dessertChoices[2] = new MenuItem("Carrot Cake", "Dessert", true, 5.95, 2.00, 20);
		dessertChoices[3] = new MenuItem("Brownie a la Mode", "Dessert", true, 5.95, 2.00, 20);
		dessertChoices[4] = new MenuItem("Chocolate Explosion", "Dessert", true, 5.95, 2.00, 20);
		dc = 5;
		//Init na drinks
		nonAlcoholicChoices[0] = new MenuItem("Soda", "NonAlcoholicDrink", true, 1.95, 2.00, 20);
		nonAlcoholicChoices[1] = new MenuItem("Sweet Tea", "NonAlcoholicDrink", true, 1.95, 2.00, 20);
		nonAlcoholicChoices[2] = new MenuItem("Unsweetened Tea", "NonAlcoholicDrink", true, 1.95, 2.00, 20);
		nonAlcoholicChoices[3] = new MenuItem("Coffee", "NonAlcoholicDrink", true, 1.95, 2.00, 20);
		nonAlcoholicChoices[4] = new MenuItem("Water", "NonAlcoholicDrink", true, 0.00, 2.00, 20);
		nc = 5;
		//Init drinks
		alcoholicChoices[0] = new MenuItem("Old Fashon", "Drink", true, 8.95, 2.00, 20);
		alcoholicChoices[1] = new MenuItem("Exile Ruthie", "Drink", true, 5.95, 2.00, 20);
		alcoholicChoices[2] = new MenuItem("Bud Light", "Drink", true, 2.95, 2.00, 20);
		alcoholicChoices[3] = new MenuItem("House Cocktail", "Drink", true, 7.95, 2.00, 20);
		alcoholicChoices[4] = new MenuItem("Martini", "Drink", true, 7.95, 2.00, 20);
		ac = 5;
		//Init cleaning supplies
		cleaningSupplies[0] = new Item("Bleach", "Cleaning", "Use cation when using Bleach", 3.95, 10);
		cleaningSupplies[1] = new Item("Mop", "Cleaning", "These are standard mops with wooden handles", 14.95, 5);
		cleaningSupplies[2] = new Item("Broom", "Cleaning", "These are the standard indoor brooms", 9.95, 5);
		c = 3;
		//Init front desk supplies
		frontDeskSupplies[0] = new Item("Spare Room Key", "FrontDesk", "Use these if a guest loses their intial room key", 0.95, 150);
		frontDeskSupplies[1] = new Item("Ballpoint Pen", "FrontDesk", "Bic Ballpoint Pen. Comes in packs of 10", 5.95, 3);
		frontDeskSupplies[2] = new Item("Sticky Notes", "FrontDesk", "3M Sticky Notes", 3.95, 0);
		f = 3;
		//Init other supplies
		otherSupplies[0] = new Item("Bath Towel", "Other", "Basic bath towels", 9.95, 43);
		otherSupplies[1] = new Item("Mini Shampoo", "Other", "Comes in packs of 50", 6.95, 20);
		otherSupplies[2] = new Item("Bar Soap", "Other", "Comes in packs of 20", 3.95, 10);
		otherSupplies[3] = new Item("Employee Uniforms", "Other", "Valet uniforms are the only one in stock right now", 49.95, 2);
		o = 4;
		//Set suppliesInit
		suppliesInit = true;
	}
	
	public static void viewSuppliesWithStock() {
		int itemNum = 1;
		System.out.println("Restaurant Supplies:");
		for(int i = 0; i < ec; i++) {
			System.out.println(itemNum + ". " + entreeChoices[i].name + " has a current stock of: " + entreeChoices[i].quantity);
			itemNum++;
		}
		for(int i = 0; i < sc; i++) {
			System.out.println(itemNum + ". " + sideChoices[i].name + " has a current stock of: " + sideChoices[i].quantity);
			itemNum++;
		}
		for(int i = 0; i < dc; i++) {
			System.out.println(itemNum + ". " + dessertChoices[i].name + " has a current stock of: " + dessertChoices[i].quantity);
			itemNum++;
		}
		for(int i = 0; i < nc; i++) {
			System.out.println(itemNum + ". " + nonAlcoholicChoices[i].name + " has a current stock of: " + nonAlcoholicChoices[i].quantity);
			itemNum++;
		}
		for(int i = 0; i < ac; i++) {
			System.out.println(itemNum + ". " + alcoholicChoices[i].name + " has a current stock of: " + alcoholicChoices[i].quantity);
			itemNum++;
		}
		System.out.println("\nCleaning Supplies:");
		for(int i = 0; i < c; i++) {
			System.out.println(itemNum + ". " + cleaningSupplies[i].name + " has a current stock of: " + cleaningSupplies[i].quantity);
			itemNum++;
		}
		System.out.println("\nFront Desk Supples:");
		for(int i = 0; i < f; i++) {
			System.out.println(itemNum + ". " + frontDeskSupplies[i].name + " has a current stock of: " + frontDeskSupplies[i].quantity);
			itemNum++;
		}
		System.out.println("\nOther Supplies:");
		for(int i = 0; i < o; i++) {
			System.out.println(itemNum + ". " + otherSupplies[i].name + " has a current stock of: " + otherSupplies[i].quantity);
			itemNum++;
		}
	}
	
	public static void viewSupplies() {
		int itemNum = 1;
		System.out.println("Restaurant Supplies:");
		for(int i = 0; i < ec; i++) {
			System.out.println(itemNum + ". " + entreeChoices[i].name);
			itemNum++;
		}
		for(int i = 0; i < sc; i++) {
			System.out.println(itemNum + ". " + sideChoices[i].name);
			itemNum++;
		}
		for(int i = 0; i < dc; i++) {
			System.out.println(itemNum + ". " + dessertChoices[i].name);
			itemNum++;
		}
		for(int i = 0; i < nc; i++) {
			System.out.println(itemNum + ". " + nonAlcoholicChoices[i].name);
			itemNum++;
		}
		for(int i = 0; i < ac; i++) {
			System.out.println(itemNum + ". " + alcoholicChoices[i].name);
			itemNum++;
		}
		System.out.println("\nCleaning Supplies:");
		for(int i = 0; i < c; i++) {
			System.out.println(itemNum + ". " + cleaningSupplies[i].name);
			itemNum++;
		}
		System.out.println("\nFront Desk Supples:");
		for(int i = 0; i < f; i++) {
			System.out.println(itemNum + ". " + frontDeskSupplies[i].name);
			itemNum++;
		}
		System.out.println("\nOther Supplies:");
		for(int i = 0; i < o; i++) {
			System.out.println(itemNum + ". " + otherSupplies[i].name);
			itemNum++;
		}
	}
	
	public static void placeOrder() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean orderFinished = false;
		String items = "";
		double total = 0;
		System.out.println("Welcome to the order creation page.");
		System.out.println("Would you like to create and order?");
		System.out.println("Enter 1 for yes and 0 for no:");
		choice = scanner.nextInt();
		if(choice != 1) {
			return;
		}
		while(!orderFinished) {
			viewSuppliesWithStock();
			System.out.println("Enter the number of the item you would like to purchase:");
			choice = scanner.nextInt();
			//Get the item that will be updated.
			choice--;
			int index = -1;
			Item item = null;
			MenuItem mItem = null;
			if(choice < ec) {
				index = choice;
				mItem = entreeChoices[index];
			} else {
				choice -= ec;
			}
			if(choice < sc && index == -1) {
				index = choice;
				mItem = sideChoices[index];
			} else {
				choice -= sc;
			}
			if(choice < dc && index == -1) {
				index = choice;
				mItem = dessertChoices[index];
			} else {
				choice -= dc;
			}
			if(choice < nc && index == -1) {
				index = choice;
				mItem = nonAlcoholicChoices[index];
			} else {
				choice -= nc;
			}
			if(choice < ac && index == -1) {
				index = choice;
				mItem = alcoholicChoices[index];
			} else {
				choice -= ac;
			}
			if(choice < c && index == -1) {
				index = choice;
				item = cleaningSupplies[index];
			} else {
				choice -= c;
			}
			if(choice < f && index == -1) {
				index = choice;
				item = frontDeskSupplies[index];
			} else {
				choice -= f;
			}
			if(choice < o && index == -1) {
				index = choice;
				item = otherSupplies[index];
			} 
			if (index == -1) {
				System.out.println("Item not found");
			}
			String name;
			if(mItem != null) {
				name = mItem.name;
			} else {
				name = item.name;
			}
			System.out.println("How many " + name + " would you like ot purchase? Enter 0 if you do not want to purchase this item.");
			int quantity = scanner.nextInt();
			if(quantity != 0) {
				if(mItem != null) {
					total += mItem.costPerUnit * quantity;
					items += mItem.name + ": " + quantity + "\n";
					mItem.quantity += quantity;
				} else {
					total += item.price * quantity;
					items += item.name + ": " + quantity + "\n";
					item.quantity += quantity;
				}
			} else {
				System.out.println(name + " was not added to the order");
			}
			System.out.println("The order is currently:\n" + items);
			System.out.println("Do you want to add another item to the order?");
			System.out.println("Enter 1 for yes and 0 for no:");
			choice = scanner.nextInt();
			if(choice == 0) {
				orderFinished = true;
			}
		}
		Order order = new Order(items, total, orderNumber);
		orderNumber++;
		System.out.println("\nOrder #" + order.orderNumber);
		System.out.println(order.lineItems);
		System.out.println("Total: $" + order.total);
		System.out.println("Would you like to finalize this order now?");
		System.out.println("Enter 1 for yes and 0 for no");
		choice = scanner.nextInt();
		orders[numO] = order;
		numO++;
		if(choice == 1) {
			order.completeOrder();
			System.out.println("Order complete! The Hotel was billed for $" + df.format(order.total) + ". The Hotel has currently been billed for $" + df.format(totalHotelBilled) + " for supply purchases.");
		} else {
			System.out.println("The order is complete but has not been billed. You can finalize and order from the 'Finalize an Order' menu option.");
		}
	}
	
	public static void finalizeOrder() {
		Scanner scanner = new Scanner(System.in);
		Order[] o = new Order[15];
		boolean needFinalized = false;
		for(int i = 0; i < numO; i++) {
			if(!orders[i].billed) {
				System.out.println(i + ". Order " + orders[i].orderNumber + " has not been billed and has a total of " + orders[i].total + ".");
				needFinalized = true;
			}
		}
		if(!needFinalized) {
			System.out.println("No orders need to be finalized.");
			return;
		}
		System.out.println("Enter the line number of the order you want to finalize:");
		int choice = scanner.nextInt();
		int index = choice;
		System.out.println("Order #" + o[choice].orderNumber + " will be finalized for $" + df.format(o[choice].total) + ".");
		System.out.println("Do you want to finalize this order?");
		System.out.println("Enter 1 for yes and 0 for no");
		choice = scanner.nextInt();
		if(choice == 1) {
			o[index].completeOrder();
			totalHotelBilled += o[index].total;
			System.out.println("The Hotel was billed for $" + df.format(o[index].total) + ". The Hotel has currently spent $" + df.format(totalHotelBilled) + " on supplies."); 
			System.out.println("Do you want to finalize another order?");
		} else {
			System.out.println("Order not finalized");
			System.out.println("Do you want to finalize a different order?");
		}
		System.out.println("Enter 1 for yes and 0 for no:");
		if(choice == 1) {
			finalizeOrder();
		}
	}
	
	public static void viewOrders() {
		if(numO == 0) {
			System.out.println("No orders to view at this time.");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		boolean found = false;
		Order order = null;
		int choice;
		while(!found) {
			System.out.println("ORDERS:");
			for(int i = 0; i < numO; i++) {
				System.out.println(orders[i].orderNumber);
			}
			System.out.println("Enter the order number you would like to view:");
			choice = scanner.nextInt();
			for(int i = 0; i < numO; i++) {
				if(orders[i].orderNumber == choice) {
					found = true;
					order = orders[i];
				}
			}
			if(!found) {
				System.out.println("Order not found.");
			}
		}
		System.out.println("\nOrder #" + order.orderNumber);
		System.out.println(order.lineItems);
		String billed;
		if(order.billed) {
			billed = "yes";
		} else {
			billed = "no";
		}
		System.out.println("Total: " + df.format(order.total));
		System.out.println("Billed: " + billed);
		System.out.println("\nDo you want to view a different order?");
		System.out.println("Enter 1 for yes and 0 for no:");
		choice = scanner.nextInt();
		if(choice == 1) {
			viewOrders();
		}
	}
	
	public static void updateStock() {
		Scanner scanner = new Scanner(System.in);
		viewSuppliesWithStock();
		System.out.println("What item do you want to update stock for?");
		System.out.println("Enter the number of the item:");
		int choice = scanner.nextInt();
		//Get the item that will be updated.
		choice--;
		int index = -1;
		Item item = null;
		MenuItem mItem = null;
		if(choice < ec) {
			index = choice;
			mItem = entreeChoices[index];
		} else {
			choice -= ec;
		}
		if(choice < sc && index == -1) {
			index = choice;
			mItem = sideChoices[index];
		} else {
			choice -= sc;
		}
		if(choice < dc && index == -1) {
			index = choice;
			mItem = dessertChoices[index];
		} else {
			choice -= dc;
		}
		if(choice < nc && index == -1) {
			index = choice;
			mItem = nonAlcoholicChoices[index];
		} else {
			choice -= nc;
		}
		if(choice < ac && index == -1) {
			index = choice;
			mItem = alcoholicChoices[index];
		} else {
			choice -= ac;
		}
		if(choice < c && index == -1) {
			index = choice;
			item = cleaningSupplies[index];
		} else {
			choice -= c;
		}
		if(choice < f && index == -1) {
			index = choice;
			item = frontDeskSupplies[index];
		} else {
			choice -= f;
		}
		if(choice < o && index == -1) {
			index = choice;
			item = otherSupplies[index];
		} 
		if (index == -1) {
			System.out.println("Item not found");
		}
		String name;
		if(mItem != null) {
			name = mItem.name;
		} else {
			name = item.name;
		}
		int q = -1;
		if(mItem != null) {
			System.out.println("The current stock of " + name + " is " + mItem.quantity + ".");
			System.out.println("Do you want to update this. Remember: if you need more please place an order instead.");
			System.out.println("Enter 1 for yes and 0 for no");
			choice = scanner.nextInt();
			if(choice == 1) {
				while(q < 0) {
					q = getNewItemQuantity();
					mItem.quantity = q;
					if(q < 0) {
						System.out.println("Quantity cannot be less than 0! Enter the new quantity:");
					}
				}
			}
		} else {
			System.out.println("The current stock of " + name + " is " + item.quantity + ".");
			System.out.println("Do you want to update this. Remember: if you need more please place an order instead.");
			System.out.println("Enter 1 for yes and 0 for no");
			choice = scanner.nextInt();
			if(choice == 1) {
				while(q < 0) {
					q = getNewItemQuantity();
					item.quantity = q;
					if(q < 0) {
						System.out.println("Quantity cannot be less than 0! Enter the new quantity:");
					}
				}
			}
		}
		if(q != -1) {
			System.out.println(name + "'s new quantity is " + q);
		}
		System.out.println("Do you want to update the quantity of another item?");
		System.out.println("Enter 1 for yes and 0 for no");
		choice = scanner.nextInt();
		if(choice == 1) {
			updateStock();
		}
	}
	
	public static void newItem() {
		String name = getNewItemName();
		String type = getNewItemType();
		if(type.toLowerCase().equals("restaurant")) {
			handleRestaurantSupply(name);
		} else {
			String info = getNewItemInfo();
			double price = getNewItemPrice();
			int quantity = getNewItemQuantity();
			Item newItem = new Item(name, type, info, price, quantity);
		}
	}
	
	public static void handleRestaurantSupply(String name) {
		String foodType = getFoodType();
		boolean vegOp = true; 
		if(foodType.equals("Entree") || foodType.equals("Side")) {
			vegOp = getVegOption();
		}
		double price = getSellingPrice();
		double costPerUnit = getNewItemPrice();
		int quantity = getNewItemQuantity();
		MenuItem newItem = new MenuItem(name, foodType, vegOp, price, costPerUnit, quantity);
		System.out.println(newItem);
		
	}
	
	public static void updateItemInfo() {
		viewSupplies();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of the item you would like to edit:");
		int choice = scanner.nextInt();
		//Get the item that will be updated.
		choice--;
		int index = -1;
		Item item = null;
		MenuItem mItem = null;
		if(choice < ec) {
			index = choice;
			mItem = entreeChoices[index];
		} else {
			choice -= ec;
		}
		if(choice < sc && index == -1) {
			index = choice;
			mItem = sideChoices[index];
		} else {
			choice -= sc;
		}
		if(choice < dc && index == -1) {
			index = choice;
			mItem = dessertChoices[index];
		} else {
			choice -= dc;
		}
		if(choice < nc && index == -1) {
			index = choice;
			mItem = nonAlcoholicChoices[index];
		} else {
			choice -= nc;
		}
		if(choice < ac && index == -1) {
			index = choice;
			mItem = alcoholicChoices[index];
		} else {
			choice -= ac;
		}
		if(choice < c && index == -1) {
			index = choice;
			item = cleaningSupplies[index];
		} else {
			choice -= c;
		}
		if(choice < f && index == -1) {
			index = choice;
			item = frontDeskSupplies[index];
		} else {
			choice -= f;
		}
		if(choice < o && index == -1) {
			index = choice;
			item = otherSupplies[index];
		} 
		if (index == -1) {
			System.out.println("Item not found");
		}
		String name;
		if(mItem != null) {
			name = mItem.name;
		} else {
			name = item.name;
		}
		if(mItem != null) {
			System.out.println("You are about to edit " + name + ". Do you wish to continue?");
			System.out.println("Enter 1 for yes and 0 for no:");
			choice = scanner.nextInt();
			if(choice == 1) {
				System.out.println("Do you want to edit the name of " + name + "?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					name = getNewItemName();
					mItem.name = name;
				}
				String not = "";
				if(!mItem.vegetarianOption) {
					not = "not";
				}
				System.out.println(name + " is " + not + " a vegetarian option. Do you want to change this?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					mItem.vegetarianOption = !mItem.vegetarianOption;
				}
				System.out.println(name + " is currently sold for " + df.format(mItem.price) + ". Do you want to change this?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					mItem.price = getSellingPrice();
				}
				System.out.println(name + " is currently purchased for " + df.format(mItem.costPerUnit) + ". Do you want to change this?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					mItem.costPerUnit = getNewItemPrice();
				}
				System.out.println(name + " has been updated. If you need to update quantities, please use the 'Update Stock' menu option");
			}
		} else {
			System.out.println("You are about to edit " + name + ". Do you wish to continue?");
			System.out.println("Enter 1 for yes and 0 for no:");
			choice = scanner.nextInt();
			if(choice == 1) {
				//Ask what they want to edit
				System.out.println("Do you want to edit the name of " + name + "?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					name = getNewItemName();
					item.name = name;
				}
				System.out.println("Do you want to edit the description of " + name +" is:\n" + item.info + "\nDo you want to change it?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					item.info = getNewItemInfo();
				}
				System.out.println(name + " is currently purchased for " + df.format(item.price) + ". Do you want to change this?");
				System.out.println("Enter 1 for yes and 0 for no:");
				choice = scanner.nextInt();
				if(choice == 1) {
					item.price = getNewItemPrice();
				}
				System.out.println(name + " has been updated. If you need to update quantities, please use the 'Update Stock' menu option");
			}
		}
		System.out.println("Do you want to edit any more items?");
		System.out.println("Enter 1 for yes and 0 for no:");
		choice = scanner.nextInt();
		if(choice == 1) {
			updateItemInfo();
		}
	}
	
	public static String getNewItemName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the name of the item?");
		String name = scanner.nextLine();
		return name;
	}
	
	public static String getNewItemType() {
		Scanner scanner = new Scanner(System.in);
		int type = 0;
		while(type < 1 || type > 4) {
			System.out.println("What is the type of the item?");
			System.out.println("1. Restuarant Menu Item");
			System.out.println("2. Cleaning Supplies");
			System.out.println("3. Front Desk Supplies");
			System.out.println("4. Other Supplies");
			type = scanner.nextInt();
			if(type == 1) {
				return "Restaurant";
			} else if(type == 2) {
				return "Cleaning";
			} else if(type == 3) {
				return "FrontDesk";
			} else if(type == 4) {
				return "Other";
			} else {
				System.out.println("Not a vaild choice. Enter a number between 1 and 4.");
			}
		}
		return "Not an option";
	}
	
	public static String getNewItemInfo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the description of the item?");
		String info = scanner.nextLine();
		return info;
	}
	
	public static double getNewItemPrice() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the price to purchase this item?");
		double price = scanner.nextDouble();
		return price;
	}
	
	public static int getNewItemQuantity() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the available quantity of the item?");
		int quantity = scanner.nextInt();
		return quantity;
	}
	
	public static String getFoodType() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		while(choice < 1 || choice > 5) {
			System.out.println("What is the menu item type for this restaurant item?");
			System.out.println("1. Entree");
			System.out.println("2. Side");
			System.out.println("3. Dessert");
			System.out.println("4. Drink");
			System.out.println("5. Alcoholic Drink");
			System.out.println("Enter the number of your choice:");
			choice = scanner.nextInt();
			if(choice == 1) {
				return "Entree";
			} else if (choice == 2) {
				return "Side";
			} else if (choice == 3) {
				return "Dessert";
			} else if (choice == 4) {
				return "NonAlcoholicDrink";
			} else if (choice == 5) {
				return "Drink";
			}
			System.out.println("Input is not an option, enter your choice from 1 to 5.");
		}
		return "not an option";
	}
	
	public static boolean getVegOption() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Is this entree vegeterian?");
		System.out.println("Enter 1 for yes and 0 for no:");
		int choice = scanner.nextInt();
		if(choice == 1) {
			return true;
		}
		return false;
	}
	
	public static double getSellingPrice() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the price we sell this menu item for?");
		double price = scanner.nextDouble();
		return price;
	}
	
	public static void removeItem() {
		Scanner scanner = new Scanner(System.in);
		viewSupplies();
		System.out.println("Which item would you like to delete from the system?");
		System.out.println("Enter the item number:");
		int choice = scanner.nextInt();
		//Get the item that will be removed.
		choice--;
		int index = -1;
		Item[] items = null;
		Item item = null;
		MenuItem[] mItems = null;
		MenuItem mItem = null;
		if(choice < ec) {
			index = choice;
			mItem = entreeChoices[index];
			mItems = entreeChoices;
		} else {
			choice -= ec;
		}
		if(choice < sc && index == -1) {
			index = choice;
			mItem = sideChoices[index];
			mItems = sideChoices;
		} else {
			choice -= sc;
		}
		if(choice < dc && index == -1) {
			index = choice;
			mItem = dessertChoices[index];
			mItems = dessertChoices;
		} else {
			choice -= dc;
		}
		if(choice < nc && index == -1) {
			index = choice;
			mItem = nonAlcoholicChoices[index];
			mItems = nonAlcoholicChoices;
		} else {
			choice -= nc;
		}
		if(choice < ac && index == -1) {
			index = choice;
			mItem = alcoholicChoices[index];
			mItems = alcoholicChoices;
		} else {
			choice -= ac;
		}
		if(choice < c && index == -1) {
			index = choice;
			item = cleaningSupplies[index];
			items = cleaningSupplies;
		} else {
			choice -= c;
		}
		if(choice < f && index == -1) {
			index = choice;
			item = frontDeskSupplies[index];
			items = frontDeskSupplies;
		} else {
			choice -= f;
		}
		if(choice < o && index == -1) {
			index = choice;
			item = otherSupplies[index];
			items = otherSupplies;
		} 
		if (index == -1) {
			System.out.println("Item not found");
		}
		String name;
		if(mItem != null) {
			name = mItem.name;
		} else {
			name = item.name;
		}
		System.out.println("Are you sure you want to remove " + name + " from the system?");
		System.out.println("Enter 1 for yes and 0 for no.");
		choice = scanner.nextInt();
		if(choice == 1) {
			//Remove the item
			if(mItem != null) {
				if(mItem.foodType.equals("Entree")) {
					for(int i = index; i < (ec - 1) ; i++) {
						entreeChoices[i] = entreeChoices[i+1];
					}
					ec--;
					entreeChoices[ec] = null;
				} else if(mItem.foodType.equals("Side")) {
					for(int i = index; i < (sc - 1); i++) {
						sideChoices[i] = sideChoices[i+1];
						sc--;
						sideChoices[sc] = null;
					}
				} else if(mItem.foodType.equals("Dessert")) {
					for(int i = index; i < (dc - 1); i++) {
						dessertChoices[i] = dessertChoices[i+1];
						dc--;
						dessertChoices[dc] = null;
					}
				} else if(mItem.foodType.equals("NonAlcoholicDrink")) {
					for(int i = index; i < (nc - 1); i++) {
						nonAlcoholicChoices[i] = nonAlcoholicChoices[i+1];
						nc--;
						nonAlcoholicChoices[nc] = null;
					}
				} else if(mItem.foodType.equals("Drink")) {
					for(int i = index; i < (ac - 1); i++) {
						alcoholicChoices[i] = alcoholicChoices[i+1];
						ac--;
						alcoholicChoices[ac] = null;
					}
				}
			} else {
				if(item.type.equals("Cleaning")) {
					for(int i = index; i < c; i++) {
						cleaningSupplies[i] = cleaningSupplies[i+1];
						c--;
						cleaningSupplies[c] = null;
					}
				} else if(item.type.equals("FrontDesk")) {
					for(int i = index; i < f; i++) {
						frontDeskSupplies[i] = frontDeskSupplies[i+1];
						f--;
						frontDeskSupplies[f] = null;
					}
				} else if(item.type.equals("Other")) {
					for(int i = index; i < f; i++) {
						otherSupplies[i] = otherSupplies[i+1];
						f--;
						otherSupplies[f] = null;
					}
				}
			}
			System.out.println("The item has been removed from the system. Do you want to delete another item?");
			System.out.println("Enter 1 for yes and 0 for no.");
			choice = scanner.nextInt();
			if(choice == 1) {
				removeItem();
			}
		} else {
			System.out.println("Item will not be removed from the system. Do you want to choose a different item?");
			System.out.println("Enter 1 for yes and 0 for no.");
			choice = scanner.nextInt();
			if(choice == 1) {
				removeItem();
			}
		}
	}
	
	public static MenuItem[] getEntrees() {
		return entreeChoices;
	}
	
	public static MenuItem[] getSides() {
		return sideChoices;
	}
	
	public static MenuItem[] getDesserts() {
		return dessertChoices;
	}
	
	public static MenuItem[] getNADrinks() {
		return nonAlcoholicChoices;
	}
	
	public static MenuItem[] getDrinks() {
		return alcoholicChoices;
	}
}
