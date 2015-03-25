package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class Transaction 
{
	// connection
	private static Connection con;
	// name db
	private static String db_name;
	
	public static void connect() throws ClassNotFoundException, SQLException
	{
		// chargement de la class
		Class.forName("org.sqlite.JDBC");
		// chargement de la db
		con = DriverManager.getConnection("jdbc:sqlite:" + db_name);

		Statement requete = con.createStatement();
		
		// evite la synchronisation qui ralenti le processus
		requete.executeUpdate("PRAGMA synchronous = OFF;");
	}
	
	// création des tables
	public static void createTables() throws ClassNotFoundException, SQLException
	{
		String sql_t_identification = "create table IF NOT EXISTS t_identification "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "gsm TEXT UNIQUE,"
				+ "name TEXT,"
				+ "firstname TEXT,"
				+ "adress TEXT,"
				+ "n0 TEXT,"
				+ "zipcode CHAR(8),"
				+ "city TEXT)";
		
		String sql_t_communication = "create table IF NOT EXISTS t_communication "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "num_caller TEXT,"
				+ "num_receiver TEXT,"
				+ "start_time DATETIME,"
				+ "duration INT)";
				
		
		// création de la table identification
		Statement stat = Transaction.getCon().createStatement();
		stat.execute(sql_t_identification);
		// création de la able t_communication
		stat.execute(sql_t_communication);
				
		
	}

	public static Connection getCon() throws SQLException, ClassNotFoundException 
	{
		if(con == null || con.isClosed())
			Transaction.connect();
		
		return con;
		
	}
	
	 public boolean disconnect ()
	    {
	        try
	        {
	            if(con != null)
	                con.close();
	             
	            return true;
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	public static String getDb_name() {
		return db_name;
	}

	public static void setDb_name(String db_name) {
		Transaction.db_name = db_name;
	}
	
	
	
}
