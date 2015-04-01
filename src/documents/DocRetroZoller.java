package documents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import code.Data;

public class DocRetroZoller 
{
	private XWPFDocument documentWord;
	
	public DocRetroZoller(Vector v,String target) throws IOException 
	{
		// création du document word (POI)
		documentWord = new XWPFDocument();
		// créatin du fichier
		FileOutputStream out = new FileOutputStream(new File("createparagraph.docx"));
		 
		// entete du fichier
		 XWPFParagraph paragraph = documentWord.createParagraph();
		//Set bottom border to paragraph
		  paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);     
		//Set left border to paragraph
		  paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES); 
		//Set right border to paragraph
		  paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);     
		//Set top border to paragraph
		  paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
		  paragraph.setAlignment(ParagraphAlignment.CENTER);
		  
		 XWPFRun run = paragraph.createRun();
		 run.setText("IN OUT pour le numéro "+ target + "\r\r" );
		
		
		// on crée un iterator sur le vecteur 
		 Iterator it = v.iterator();
		 while(it.hasNext())
		 {
			 Data data = (Data)it.next();
			// création d'un paragraphe
			 XWPFParagraph paragraph2 = documentWord.createParagraph();
			 paragraph2.setSpacingAfterLines(5);
			 // run
			 XWPFRun run2 = paragraph2.createRun();	 
			 run2.setText(data.getNum() + "					TOTAL : " + data.getTotal() + "  IN : " + data.getNbin() +"  OUT : " + data.getNbout() + "\r");
			 
		 }
		
		
		 
		 // run
		// XWPFRun run = paragraph.createRun();
		 
		// run.setText("Bonjour à tous, ici je teste la création d'un document word avec POI");
		 
		 // ecriture
		 documentWord.write(out);
		 out.close();
	}
	
}
