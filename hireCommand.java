import java.sql.SQLException;

public class hireCommand implements iCommand{

	@Override
	public boolean execute() throws ClassNotFoundException, SQLException {
		Employee.hireEmp();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". Hire Employee";
		return desc;
	}

}
