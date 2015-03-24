package mobistar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import sql.Transaction;

public class MobistarZollerFile 
{
	
	private final int indexRowStart = 12;
	private int indexRowEnd;
	
	public MobistarZollerFile(File file) throws IOException, ClassNotFoundException, SQLException
	{
		// création de la base
				try 
				{
					Transaction.createTables();

				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		// chargement du fichier
		FileInputStream stream = new FileInputStream(file);
		// le workboot
		HSSFWorkbook workBook = new HSSFWorkbook(stream);
		// on récupère le sheet
		HSSFSheet sheet = workBook.getSheetAt(0);
		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();
		int indRow = 0;
		
		// calcul de l'index final
		this.indexRowEnd = sheet.getLastRowNum() - 2;
		// calcul du nombre de ligne
		int nbRow = this.indexRowEnd - this.indexRowStart;
		// chargemt des lignes
		
		// création de la requete
		// insert dans la base sql
		
		
		for(int ind = this.indexRowStart;ind < this.indexRowEnd;ind ++)
		{
			// réception d'une ligne
			Row r = sheet.getRow(ind);
			
			// création de la classe data mobistar
			MobistarRowData mobi = new MobistarRowData(r);
			// insert dans la table identification
			mobi.insertInIdentification();
			// insert une communication
			mobi.insertInCommunication();
			
		}
		
		
		// fermeture du fichier
		stream.close();
	}
}
