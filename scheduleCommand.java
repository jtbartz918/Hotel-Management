
import java.sql.SQLException;

public class scheduleCommand implements iCommand{

	@Override
	public boolean execute() throws ClassNotFoundException, SQLException {
		Employee.scheduleEmployee();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". Schedule Employee";
		return desc;
	}

}