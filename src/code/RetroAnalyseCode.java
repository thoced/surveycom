package code;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
	
	public static Vector getInOut(String target,String dateStart,String heureStart,String dateEnd,String heureEnd) throws ClassNotFoundException, SQLException, DateHeureException
	{
		// on vérifie si les dates et heures sont correctes.
		String[] splitDateStart = dateStart.split("/");
		if(splitDateStart == null || splitDateStart.length != 3)
			throw new DateHeureException("La date de debut de recherche n'est pas valable");
		
		
		String[] splitHeureStart = heureStart.split(":");
		if(splitHeureStart == null || splitHeureStart.length != 2)
			throw new DateHeureException("L'heure de debut de recherche n'est pas valable");
		
		
		String[] splitDateEnd = dateEnd.split("/");
		if(splitDateEnd == null || splitDateEnd.length != 3)
			throw new DateHeureException("La date de fin de recherche n'est pas valable");
		
		
		String[] splitHeureEnd = heureEnd.split(":");
		if(splitHeureEnd == null || splitHeureEnd.length != 2)
			throw new DateHeureException("L'heure de fin de recherche n'est pas valable");
		
		
		// création des dates sur base d'un calendar
		Calendar calStart = new GregorianCalendar(Integer.parseInt(splitDateStart[2]), // annee
				Integer.parseInt(splitDateStart[1])-1, // mois
				Integer.parseInt(splitDateStart[0]),// jour
				Integer.parseInt(splitHeureStart[0]),
				Integer.parseInt(splitHeureStart[1])); 
		
		//calStart.clear();
		//calStart.set(Calendar.DAY_OF_MONTH,Integer.parseInt(splitDateStart[0]));
		//calStart.set(Calendar.MONTH,Integer.parseInt(splitDateStart[1])-1
		//calStart.set(Calendar.YEAR,Integer.parseInt(splitDateStart[2]));
	
				
		Calendar calEnd = new GregorianCalendar(Integer.parseInt(splitDateEnd[2]), // annee
				Integer.parseInt(splitDateEnd[1])-1, // mois
				Integer.parseInt(splitDateEnd[0]),// jour
				Integer.parseInt(splitHeureEnd[0]),
				Integer.parseInt(splitHeureEnd[1])); 
				
		
		// transformation des dates en java.sql.date
		java.sql.Date sqldateStart = new java.sql.Date(calStart.getTimeInMillis());
		java.sql.Date sqldateEnd   = new java.sql.Date(calEnd.getTimeInMillis());
	
	
		// requete pour l'affichage de l'ensemble des 
		String sql = "select num_caller NUM ,sum(nbin) TOTAL, nbin "
				+ "from (select num_caller, count(num_caller) nbin from t_communication  where num_receiver = ? AND start_time BETWEEN ? AND ? group by num_caller "
				+ "union "
				+ "select num_receiver, count(num_receiver) nbout from t_communication  where num_caller = ? AND start_time BETWEEN ? AND ? group by num_receiver) "
				+ "group by num_caller order by TOTAL desc";
		
		
		
		// creation du statement
		PreparedStatement ps = Transaction.getCon().prepareStatement(sql);
		ps.setString(1, target);
		ps.setDate(2, sqldateStart); // date start
		ps.setDate(3, sqldateEnd); // date end
		ps.setString(4, target);
		ps.setDate(5, sqldateStart); // date start
		ps.setDate(6, sqldateEnd); // date end
		
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
			int nbin = result.getInt("nbin");
		
			data.setNum(num);
			data.setTotal(total);
			data.setNbin(nbin);
			
			
			// on inscrit la valeur dans le vecteur 
			v.add(data);
		}
		// on retourne le resultat
		return v;
			
	}
}
