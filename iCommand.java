import java.sql.SQLException;

public interface iCommand {
	public boolean execute() throws ClassNotFoundException, SQLException;
	public String getDesc();
}
