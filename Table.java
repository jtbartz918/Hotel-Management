
public class Table {
	private boolean open;
	public int seating;
	public int tableNumber;
	
	public Table(int seating, int tableNumber) {
		this.seating = seating;
		this.tableNumber = tableNumber;
	}
	
	public boolean checkAvailability() {
		return this.open;
	}
	
	public void useTable() {
		this.open = false;
	}
	
	public void openTable() {
		this.open = true;
	}
}
