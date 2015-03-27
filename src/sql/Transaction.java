package sql;

import gui.Intervention;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		// active les foreign key
		requete.executeUpdate("PRAGMA foreign_key = ON;");
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
		
		String sql_t_interventions = "create table IF NOT EXISTS t_intervention "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "num TEXT UNIQUE,"
				+ "user TEXT)";
		
		String sql_t_fichiers = "create table IF NOT EXISTS t_fichiers "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "path TEXT,"
				+ "num_ref TEXT,"
				+ "FOREIGN KEY(num_ref) REFERENCES t_intervention(num))";
				
		
		// création de la table identification
		Statement stat = Transaction.getCon().createStatement();
		stat.execute(sql_t_identification);
		// création de la able t_communication
		stat.execute(sql_t_communication);
		// créatyin de la table t_intervention
		stat.execute(sql_t_interventions);
		// création de la table t_fichier
		stat.execute(sql_t_fichiers);
				
		
	}
	
	// ajout du fichier importer dans la table
	// path : nom du fichier
	// num  : numero d'intervention associé
	public static void insertFile(String path,String num)
	{
		String sql = "insert into t_fichiers (path,ref_inter) values (?,?)";
		
		
	}
	
	public static List<Intervention> getListIntervention() throws ClassNotFoundException, SQLException
	{
		String sql = "select id,num,user from t_intervention";
		Statement ps = Transaction.getCon().createStatement();
		ResultSet result = ps.executeQuery(sql);
		
		List<Intervention> list = new ArrayList<Intervention>();
		
		while(result.next())
		{
			Intervention i = new Intervention();
			i.setId(result.getInt("id"));
			i.setNum(result.getString("num"));
			i.setUser(result.getString("user"));
			list.add(i);
			
		}
		
		
		return list;
	
	}
	
	public static void insertNewIntervention(String numero,String user) throws ClassNotFoundException, SQLException
	{
		// insertion d'une nouvelle intervention
		if(numero != null)
		{
			String sql = "insert into t_intervention (num,user) values (?,?)";
			Connection c = Transaction.getCon();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, numero);
			ps.setString(2, user);
			ps.executeUpdate();
		}
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
