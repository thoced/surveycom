package main;


import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import mobistar.MobistarZollerFile;

public class MonProg {

	public static void main(String[] args) throws IOException
	{
		
	
		
		
		
		JFileChooser jfc = new JFileChooser();
		int result = jfc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			// ouverture du fichier mobistar
			try 
			{
				
				
				MobistarZollerFile mzf = new MobistarZollerFile(jfc.getSelectedFile());
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		

	}

}
