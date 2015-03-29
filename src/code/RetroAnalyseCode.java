package code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	
	public static Vector getInOut(String target) throws ClassNotFoundException, SQLException
	{
		String sql = "select num_caller NUM ,sum(nb) TOTAL "
				+ "from (select num_caller, count(num_caller) nb from t_communication where num_receiver = ? group by num_caller union "
				+ "select num_receiver, count(num_receiver) nb from t_communication where num_caller = ? group by  num_receiver)" 
				+ "group by NUM order by TOTAL desc"; 
		
		PreparedStatement ps = Transaction.getCon().prepareStatement(sql);
		ps.setString(1, target);
		ps.setString(2, target);
		// éxécution de la requete
		ResultSet result = ps.executeQuery();
		// instance du data de retour
		Vector v = new Vector();
		
		while(result.next())
		{
			// instance du data
			Data data = new Data();
			// reception des données depuis la requete
			String num = result.getString("NUM");
			int total = result.getInt("TOTAL");
			data.setNum(num);
			data.setTotal(total);
			// on inscrit la valeur dans le vecteur 
			v.add(data);
		}
		// on retourne le resultat
		return v;
			
	}
}
