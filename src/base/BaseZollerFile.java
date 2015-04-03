package base;

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

public class BaseZollerFile 
{
	
	private final int indexRowStart = 12;
	private int indexRowEnd;
	
	private final int indexRowStartPylone = 1;
	private int indexRowEndPylone;
	
	public BaseZollerFile(File file) throws IOException, ClassNotFoundException, SQLException
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
		
		
		// fermeture du fichier
		stream.close();
	}
	
	public void extractPylone(HSSFWorkbook workBook)
	{
		// on récupère le sheet des pylones base
			HSSFSheet sheet = workBook.getSheetAt(3);
				//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int indRow = 0;
				
				// calcul de l'index final
			this.indexRowEndPylone = sheet.getLastRowNum();
				// calcul du nombre de ligne
			int nbRow = this.indexRowEndPylone - this.indexRowStartPylone;
				
			for(int ind = this.indexRowStartPylone;ind < this.indexRowEndPylone;ind ++)
			{
					// réception d'une ligne
				Row r = sheet.getRow(ind);
				
				
				
			}
				
	}
}
