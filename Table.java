
public class Table {
	private boolean open;
	public int seating;
	public int tableNumber;
	public int numberSeated;
	
	public Table(int seating, int tableNumber) {
		this.seating = seating;
		this.tableNumber = tableNumber;
		numberSeated = 0;
		open = true;
	}
	
	public boolean checkAvailability() {
		return open;
	}
	
	public void useTable(int guests) {
		open = false;
		numberSeated = guests;
	}
	
	public void openTable() {
		open = true;
		numberSeated = 0;
	}
}
