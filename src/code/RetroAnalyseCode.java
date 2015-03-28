package code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sql.Transaction;

public class RetroAnalyseCode 
{
	public static List<String> getAllNums() throws ClassNotFoundException, SQLException
	{
		List<String> buff = new ArrayList<String>();
		
		String sql = "select gsm from t_identification";
		// on crée le statement
		Statement st = Transaction.getCon().createStatement();
		// exécution de la requete
		ResultSet result = st.executeQuery(sql);
		
		while(result.next())
		{
			buff.add(result.getString("gsm"));
		}
		
		return buff;
		
		
	}
}
