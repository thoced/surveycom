package gui;

import java.awt.Frame;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;

import code.RetroAnalyseCode;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class DialogRetroAnalyse extends JDialog implements WindowListener
{
	private JFormattedTextField tDateStart;
	private JFormattedTextField tHeureStart;
	private JFormattedTextField tDateEnd;
	private JFormattedTextField tHeureEnd;
	private JComboBox cListNumero;
	private JPanel panelDate;
	private JPanel panelNumero;

	public DialogRetroAnalyse(Frame arg0, String arg1, boolean arg2) throws ParseException 
	{
		super(arg0, arg1, arg2);
		
		// taille
		this.setSize(800, 600);
		// centre
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		// date de debut de recherche
		tDateStart = new JFormattedTextField();
		tDateStart.setFont(new Font("Dialog", Font.PLAIN, 18));
		tDateStart.setColumns(10);
		MaskFormatter formatter = new MaskFormatter("##/##/####");
		formatter.install(tDateStart);
		tDateStart.setBounds(35, 31, 88, 19);
		getContentPane().add(tDateStart);
		
		// heure de début de recherche
		tHeureStart = new JFormattedTextField();
		tHeureStart.setFont(new Font("Dialog", Font.PLAIN, 18));
		tHeureStart.setBounds(35, 68, 53, 19);
		tHeureStart.setColumns(5);
		MaskFormatter formH = new MaskFormatter("##:##");
		formH.install(tHeureStart);
		
		getContentPane().add(tHeureStart);
		
		// Date de fin
		tDateEnd = new JFormattedTextField();
		tDateEnd.setFont(new Font("Dialog", Font.PLAIN, 18));
		tDateEnd.setBounds(202, 33, 88, 19);
		formatter.install(tDateEnd);
		getContentPane().add(tDateEnd);
		
		// Heure de fin
		tHeureEnd = new JFormattedTextField();
		tHeureEnd.setFont(new Font("Dialog", Font.PLAIN, 18));
		tHeureEnd.setBounds(202, 70, 53, 19);
		formH.install(tHeureEnd);
		getContentPane().add(tHeureEnd);
		
		cListNumero = new JComboBox();
		cListNumero.setBounds(35, 167, 200, 24);
		cListNumero.setActionCommand("LISTNUMERO");
		getContentPane().add(cListNumero);
		
		panelDate = new JPanel();
		panelDate.setBorder(new TitledBorder(null, "Date et Heure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDate.setBounds(12, 12, 475, 126);
		getContentPane().add(panelDate);
		
		panelNumero = new JPanel();
		panelNumero.setBorder(new TitledBorder(null, "Analyser \u00E0 partir de", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNumero.setBounds(12, 144, 475, 103);
		getContentPane().add(panelNumero);
		
		this.addWindowListener(this);
	}

	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
			// reception de la liste des numéros
			try 
			{
				List<String> buff = RetroAnalyseCode.getAllNums();
				
				for(String s : buff)
				{
					cListNumero.addItem(s);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
}
