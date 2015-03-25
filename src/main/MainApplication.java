package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import mobistar.MobistarZollerFile;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sql.Transaction;

public class MainApplication implements ActionListener
{

	private JFrame frame;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem menuNewDossier;
	private JMenuItem mNewDossier;
	private JMenuItem mOpenDossier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mFichier = new JMenu("Fichier");
		menuBar.add(mFichier);
		
		mNewDossier = new JMenuItem("Créer un nouveau dossier");
		mFichier.add(mNewDossier);
		mNewDossier.setActionCommand("NEWDOSSIER");
		mNewDossier.addActionListener(this);
		
		mOpenDossier = new JMenuItem("Ouvrir un dossier");
		mFichier.add(mOpenDossier);
		mOpenDossier.setActionCommand("OPENDOSSIER");
		mOpenDossier.addActionListener(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		
		switch(arg0.getActionCommand())
		{
		case "NEWDOSSIER" : 
		{ 
							JFileChooser jfc = new JFileChooser();
							int result = jfc.showSaveDialog(null);
							if(result == JFileChooser.APPROVE_OPTION)
							{
								// on réceptionne l'url de la base
								File url = jfc.getSelectedFile();
								Transaction.setDb_name(url.getAbsolutePath());
								// on connect pour créer la base
								try 
								{
									// connection et création de la db
									Transaction.connect();
									// création des tables
									Transaction.createTables();
									
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
			break;
		}
			
		case "OPENDOSSIER":
		{					// ouverture d'une base
							JFileChooser jfc = new JFileChooser();
							int result = jfc.showOpenDialog(null);
							if(result == JFileChooser.APPROVE_OPTION)
							{
								File url = jfc.getSelectedFile();
								Transaction.setDb_name(url.getAbsolutePath());
								try 
								{
									Transaction.connect();
									
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e.getMessage());
								}
							}
							break;
		}
		}
	}

}
