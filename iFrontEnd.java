import java.sql.SQLException;
import java.util.ArrayList;

public interface iFrontEnd {
	public boolean addCommand(iCommand cmd);

	public iCommand removeCommand(iCommand cmd);

	public void displayCommands() throws ClassNotFoundException, SQLException;

}
