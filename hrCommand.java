import java.sql.SQLException;

public class hrCommand implements iCommand{
	@Override
	public boolean execute() throws ClassNotFoundException, SQLException {
		iFrontEnd demo = new hrDisplay();
		demo.displayCommands();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". HR";
		return desc;
	}
}
