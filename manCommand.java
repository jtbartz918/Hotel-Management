import java.sql.SQLException;

public class manCommand implements iCommand{
	@Override
	public boolean execute() throws ClassNotFoundException, SQLException {
		Employee.managerMainScreen();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". Manager";
		return desc;
	}
}
