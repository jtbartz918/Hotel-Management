
public class Order {
	public String lineItems;
	public double total;
	public boolean billed;
	public int orderNumber;
	
	public Order(String lineItems, double total, int orderNumber) {
		this.lineItems = lineItems;
		this.total = total;
		this.orderNumber = orderNumber;
		billed = false;
	}
	
	public void completeOrder() {
		this.billed = true;
	}
}
