import java.sql.SQLException;

public class fdCommand implements iCommand{

	@Override
	public boolean execute() throws ClassNotFoundException, SQLException {
		Employee.employeeMainScreen();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". Front Desk";
		return desc;
	}

}
