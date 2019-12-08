import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class displayMenu extends HotelMain implements iFrontEnd {

	public ArrayList<iCommand> comm = new ArrayList<iCommand>();
	public fdCommand fd = new fdCommand();
	public hrCommand hr = new hrCommand();
	public restCommand rest = new restCommand();
	public manCommand man = new manCommand();
	public int counter = 0;
	public boolean foo = true;
	
	 
	@Override
	public boolean addCommand(iCommand cmd) {
		// TODO Auto-generated method stub
		comm.add(cmd);
		return false;
	}

	@Override
	public iCommand removeCommand(iCommand cmd) {
		comm.remove(cmd);
		return null;
	}
	
	public void rmC() {
		System.out.println("");
		System.out.println("Do you want to remove a command? (answer this first yes/no)");
		Scanner s = new Scanner(System.in);
		String dec = s.next();
		if(dec.equals("yes")) {
			System.out.println("Which one? (Enter number)");
			Scanner sc = new Scanner(System.in);
			int rm = s.nextInt();
			if(rm < counter + 1) {
				removeCommand(comm.get(rm-1));
				counter = 0;
				for(iCommand cmd : comm) {
					counter++;
					System.out.println(counter + cmd.getDesc());
				}
				
			}
		}
		else {
			foo = false;
			counter = 0;
			for(iCommand cmd : comm) {
				counter++;
				System.out.println(counter + cmd.getDesc());
			}
		}
	}

	@Override
	public void displayCommands() throws ClassNotFoundException, SQLException {
		addCommand(man);
		addCommand(hr);
		addCommand(fd);
		addCommand(rest);
		for(iCommand cmd : comm) {
			counter++;
			System.out.println(counter + cmd.getDesc());
		}
		while(foo) {
			rmC();
		}
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		for (int j = 0; j < comm.size(); j++) {
			comm.get(choice - 1).execute();
		}
	}

}
