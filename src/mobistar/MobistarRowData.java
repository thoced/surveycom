package mobistar;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import sql.Transaction;

public class MobistarRowData
{
	private Cell[] rowData = new Cell[32]; 
	
	private Date start_session; 	// 0
	private Date end_session;  		// 1
	private String A_gsm; 			// 2
	private String A_imsi;			// 3
	private String A_imei;			// 4
	private String A_operator;		// 5
	private String A_name;			// 6
	private String A_firstname;		// 7
	private String A_street;		// 8
	private String A_street_num;	// 9
	private String A_zipcode;		// 10
	private String A_city;			// 11
	private String A_btsadress;		// 12
	private String A_btsci;			// 13
	private String A_bsccode;		// 14
	private String A_msccode;		// 15
	
	private String B_gsm;			// 16
	private String B_imsi;			// 17
	private String B_imei;			// 18
	private String B_operator;		// 19
	private String B_name;			// 20
	private String B_firstname;		// 21
	private String B_street;		// 22
	private String B_street_num;	// 23
	private String B_zipcode;		// 24
	private String B_city;			// 25
	
	private String duration;		// 26
	private String volume;			// 27
	private String ip;				// 28
	private String calltype;		// 29
	private String forward;			// 30
	private String roaming;			// 31
		
	
	public MobistarRowData(Row r)
	{
		// on extrait les cellules  (ind 3 à 35)
		
				
		for(int ind = 3,i =0 ;ind < 35 ;ind ++,i++)
		{
			if(r.getCell(ind) != null)
			{
				
				this.rowData[i] = r.getCell(ind,Row.RETURN_BLANK_AS_NULL);
				//this.rowData[i].setCellType(Cell.CELL_TYPE_STRING);
				
				
			}
		}
		
		this.rowData[31].setCellType(Cell.CELL_TYPE_STRING);
			
	}
	
	
	
	public String extract(Cell c)
	{
		String value = "";
		
		if(c == null) // on retourne une valeur vide si la cellule est null
			return value;
		
		switch(c.getCellType())
		{
			case Cell.CELL_TYPE_BLANK : value = "";break;
			
			case Cell.CELL_TYPE_NUMERIC : value = String.valueOf(c.getNumericCellValue());break;
			
			case Cell.CELL_TYPE_STRING : value = c.getStringCellValue();break;
		}
		
		// on nettoie la value en enlevement le signe '
		value = value.replace("'", "");
		
		
		
		return value;
	}
	
	public void insertInCommunication()
	{
		// ajout d'une communication
		
		// on regarde le sens de la communication
		if(this.extract(this.rowData[29]).equals("O"))
		{
			// si le sens de la communication est vers le B
			
			String sql = "insert into t_communication (num_caller,num_receiver,start_time,duration,imsi_caller,imsi_receiver,imei_caller,imei_receiver,"
					+ "operator_caller,operator_receiver,bts_adress_caller,bts_adress_receiver,forward,roaming) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try
			{
				
				
				
				PreparedStatement ps = Transaction.getCon().prepareStatement(sql);
				
				ps.setString(1, this.extract(this.rowData[2])); // num_caller
				ps.setString(2, this.extract(this.rowData[16])); // num_receiver
				ps.setDate(3,new java.sql.Date(this.rowData[0].getDateCellValue().getTime()));  // start_time
				ps.setString(4, this.extract(this.rowData[26])); // duration
				ps.setString(5, this.extract(this.rowData[3])); // imsi caller
				ps.setString(6, this.extract(this.rowData[17]));// imsi_receiver
				ps.setString(7, this.extract(this.rowData[4])); // imei_caller
				ps.setString(8, this.extract(this.rowData[18]));// imei_receiver
				ps.setString(9, this.extract(this.rowData[5]));// operator_caller
				ps.setString(10, this.extract(this.rowData[19]));// operator_receiver
				ps.setString(11, this.extract(this.rowData[12]));// bts_adress_caller
				//ps.setString(12, this.extract(this.rowData[12]));// bts_adress_receiver il n'y en pas pour mobistar
				ps.setString(13, this.extract(this.rowData[30]));// forward
				ps.setString(14, this.extract(this.rowData[31]));// roaming
				
				ps.executeUpdate();
				
				
				
			}
			catch(SQLException | ClassNotFoundException e){}
		}
		else
		{
			// si le sens de la communication est vers le A
			
			String sql = "insert into t_communication (num_caller,num_receiver,start_time,duration,imsi_caller,imsi_receiver,imei_caller,imei_receiver,"
					+ "operator_caller,operator_receiver,bts_adress_caller,bts_adress_receiver,forward,roaming) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				try
				{
					PreparedStatement ps = Transaction.getCon().prepareStatement(sql);
							
					ps.setString(1, this.extract(this.rowData[16])); // num_caller
					ps.setString(2, this.extract(this.rowData[2])); // num_receiver
					ps.setDate(3,new java.sql.Date(this.rowData[0].getDateCellValue().getTime()));  // start_time
					ps.setString(4, this.extract(this.rowData[26])); // duration
					ps.setString(5, this.extract(this.rowData[17])); // imsi caller
					ps.setString(6, this.extract(this.rowData[3]));// imsi_receiver
					ps.setString(7, this.extract(this.rowData[18])); // imei_caller
					ps.setString(8, this.extract(this.rowData[4]));// imei_receiver
					ps.setString(9, this.extract(this.rowData[19]));// operator_caller
					ps.setString(10, this.extract(this.rowData[5]));// operator_receiver
					ps.setString(12, this.extract(this.rowData[12]));// bts_adress_receiver
					//ps.setString(11, this.extract(this.rowData[12]));// bts_adress_caller il n'y en pas pour mobistar
					ps.setString(13, this.extract(this.rowData[30]));// forward
					ps.setString(14, this.extract(this.rowData[31]));// roaming
							
					ps.executeUpdate();
							
				}
				catch(SQLException | ClassNotFoundException e){}
		}
	}
	
	public void insertInIdentification() throws ClassNotFoundException
	{
		// on ajoute pas si il n'y a pas de numéro de gsm
		
		// création de la requete d'insertion dans la table d'identification
		String sql_insert = "insert into t_identification (gsm,name,firstname,adress,n0,zipcode,city) values "
				+ "(?,?,?,?,?,?,?)";
		
	
		if(!this.extract(this.rowData[2]).equals(""))
		{
			// insert dans la base 
			try
			{
				PreparedStatement ps = Transaction.getCon().prepareStatement(sql_insert);
				// on ajoute la partie A
				ps.setString(1, this.extract(this.rowData[2]));
				ps.setString(2, this.extract(this.rowData[6]));
				ps.setString(3, this.extract(this.rowData[7]));
				ps.setString(4, this.extract(this.rowData[8]));
				ps.setString(5, this.extract(this.rowData[9]));
				ps.setString(6, this.extract(this.rowData[10]));
				ps.setString(7, this.extract(this.rowData[11]));
		
				ps.executeUpdate();
			}
			catch(SQLException e ){}
			
		}
		
		if(!this.extract(this.rowData[16]).equals(""))
		{
			try
			{
				PreparedStatement ps = Transaction.getCon().prepareStatement(sql_insert);
				// on ajoute la partie B
				ps.setString(1, this.extract(this.rowData[16]));
				ps.setString(2, this.extract(this.rowData[20]));
				ps.setString(3, this.extract(this.rowData[21]));
				ps.setString(4, this.extract(this.rowData[22]));
				ps.setString(5, this.extract(this.rowData[23]));
				ps.setString(6, this.extract(this.rowData[24]));
				ps.setString(7, this.extract(this.rowData[25]));
		
				// insert dans la base
				
				ps.executeUpdate();
			}
			catch(SQLException e ){}
		}
				
		
		
		
	}

	
	
	
}
