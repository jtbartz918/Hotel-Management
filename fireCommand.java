import java.sql.SQLException;

public class fireCommand implements iCommand{

	@Override
	public boolean execute() throws ClassNotFoundException, SQLException {
		Employee.fireEmp();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". Fire Employee";
		return desc;
	}

}