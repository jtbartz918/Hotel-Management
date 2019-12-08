import java.sql.SQLException;

public class restCommand implements iCommand{
	@Override
	public boolean execute() throws SQLException {
		Restaurant.runRestaurant();
		return false;
	}

	@Override
	public String getDesc() {
		String desc = ". Restaurant";
		return desc;
	}
}
