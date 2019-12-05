
public class Item {
	public String name;
	public String type;
	public String info;
	public double price;
	public int quantity;
	
	public Item(String name, String type, String info, double price, int quantity) {
		this.name = name;
		this.type = type;
		this.info = info;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public void updatePrice(double newPrice) {
		price = newPrice;
	}
	
	public void updateInfo(String newInfo) {
		info = newInfo;
	}
}
