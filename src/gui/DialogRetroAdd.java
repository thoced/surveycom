package gui;

import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListModel;

import sql.Transaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;

import mobistar.MobistarZollerFile;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DialogRetroAdd extends JDialog implements WindowListener,ActionListener
{
	private JButton bMobistar;
	private JButton bProximus;
	private JButton bBase;
	private JTable table;
	private InterventionModel model;
	public DialogRetroAdd(Frame f,String titre,boolean modal)
	{
		super(f,titre,modal);
		
		this.setSize(640, 480);
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 50, 614, 242);
		getContentPane().add(scrollPane);
		
		// création de la table
		table = new JTable();
		model = new InterventionModel();
		table.setModel(model);
		
		// ajout de la table dans le scrollpane
		scrollPane.setViewportView(table);
		
		bMobistar = new JButton("Mobistar");
		bMobistar.setBounds(12, 304, 140, 115);
		bMobistar.setActionCommand("MOBISTAR");
		bMobistar.addActionListener(this);
		getContentPane().add(bMobistar);
		
		bProximus = new JButton("Proximus");
		bProximus.setBounds(183, 304, 140, 115);
		bProximus.setActionCommand("PROXIMUS");
		bProximus.addActionListener(this);
		getContentPane().add(bProximus);
		
		bBase = new JButton("Base");
		bBase.setBounds(359, 304, 140, 115);
		bBase.setActionCommand("BASE");
		bBase.addActionListener(this);
		
		getContentPane().add(bBase);
			
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
	public void windowOpened(WindowEvent arg0)
	{
		// on affiche l'ensemble des intervention
		try 
		{
			// list des interventions
			List<Intervention> list = Transaction.getListIntervention();
			// on ajoute cela dans le model
			for(Intervention i : list)
				model.insertIntervention(i);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
		case "MOBISTAR": JFileChooser jfc = new JFileChooser();
						 jfc.setDialogTitle("Importer un fichier Mobistar");
						 int result = jfc.showOpenDialog(this);
						 if(result == JFileChooser.APPROVE_OPTION)
						 {
							 // on importe le fichier
							 try 
							 {
								 // on récupère l'intervention sélectionné
								int row = this.table.getSelectedRow();
								if(row < 0)
								{
									JOptionPane.showMessageDialog(null, "Veuillez sélectionner une intervention !");
									return;
								}
								// on récupère le numero de communication de l'intervention sélectionné
								String num = (String) this.table.getModel().getValueAt(row, 1);
								 // on ajoute le nom du fichier dans la table
								Transaction.insertFile(jfc.getSelectedFile().getName(), num);
								 // importation du fichier mobistar
								MobistarZollerFile mzf = new MobistarZollerFile(jfc.getSelectedFile());
								// affichage de l'information de fin
								JOptionPane.showMessageDialog(null, "Importation terminée !");
								
							 } 
							 catch (ClassNotFoundException | IOException
									| SQLException e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						 }
		}
		
		
	}
}
