
public class MenuItem {
	public String name;
	public String foodType;
	public boolean vegetarianOption;
	public double price;
	public int quantity;
	
	public MenuItem(String name, String foodType, boolean vegetarianOption, double price, int quantity) {
		this.name = name;
		this.foodType = foodType;
		this.vegetarianOption = vegetarianOption;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public void consumeFood() {
		quantity--;
	}
	
	public void updatePrice(double newPrice) {
		price = newPrice;
	}
}
