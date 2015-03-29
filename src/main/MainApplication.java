package main;

import gui.DialogIntervention;
import gui.DialogRetroAdd;
import gui.DialogRetroAnalyse;
import gui.DialogVisuIntervention;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import mobistar.MobistarZollerFile;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sql.Transaction;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import documents.DocRetroZoller;

public class MainApplication implements ActionListener
{

	private JFrame frmPhoneSurveyV;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem menuNewDossier;
	private JMenuItem mNewDossier;
	private JMenuItem mOpenDossier;
	private JMenu menuImporter;
	private JMenuItem mRetroZoller;
	private JMenu mIntervention;
	private JMenuItem mGererInter;
	private JMenuItem mNewInter;
	private JMenu mAnalyser;
	private JMenuItem mRetroAnalyse;
	private JLabel lblNewLabel;

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
					window.frmPhoneSurveyV.setVisible(true);
					
					
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
		frmPhoneSurveyV = new JFrame();
		frmPhoneSurveyV.setTitle("Phone Survey V 0.1 Alpha");
		frmPhoneSurveyV.setSize(1024, 768);
		frmPhoneSurveyV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPhoneSurveyV.setJMenuBar(menuBar);
		
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
		
		mIntervention = new JMenu("Interventions");
		menuBar.add(mIntervention);
		
		mGererInter = new JMenuItem("Gérer les interventions");
		mIntervention.add(mGererInter);
		mGererInter.setActionCommand("GERERINTERVENTION");
		mGererInter.addActionListener(this);
		
		mNewInter = new JMenuItem("Créer une intervention");
		mIntervention.add(mNewInter);
		mNewInter.setActionCommand("NEWINTERVENTION");
		mNewInter.addActionListener(this);
		
		menuImporter = new JMenu("Importer");
		menuBar.add(menuImporter);
		
		mRetroZoller = new JMenuItem("Retro Zoller");
		menuImporter.add(mRetroZoller);
		mRetroZoller.setActionCommand("RETROADD");
		mRetroZoller.addActionListener(this);
		
		mAnalyser = new JMenu("Analyse");
		menuBar.add(mAnalyser);
		
		// Analyse rétro
		mRetroAnalyse = new JMenuItem("Retro");
		mRetroAnalyse.setActionCommand("RETROANALYSE");
		mRetroAnalyse.addActionListener(this);
		mAnalyser.add(mRetroAnalyse);
		frmPhoneSurveyV.getContentPane().setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("/home/thonon/workspaceFX/AnalyserPhoobs/images/logo.png"));
		frmPhoneSurveyV.getContentPane().add(lblNewLabel);
	
		
	
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
		
		case "NEWINTERVENTION":  // ouverture de la boite dialogue de création d'une intervention
								DialogIntervention di = new DialogIntervention(null,"Intervention",true);
								di.setVisible(true);
								if(di.getNumero() != null)
								{
									try 
									{
										Transaction.insertNewIntervention(di.getNumero(),di.getUser());
										
									} catch (ClassNotFoundException
											| SQLException e) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e.getMessage());
									}
								}
								break;
								
		case "GERERINTERVENTION":  // visualisation des interventions
									DialogVisuIntervention dvi = new DialogVisuIntervention(null,"Visualisation des interventions",true);
									dvi.setVisible(true);
									break;
									
		case "RETROADD": // ajout d'un retro zoller
									DialogRetroAdd dra = new DialogRetroAdd(null,"Ajout d'un retro-zoller",true);
									dra.setVisible(true);
									break;
									
		case "RETROANALYSE": // dialog de l'analyse retro
									DialogRetroAnalyse dr = null;
									try 
									{
										dr = new DialogRetroAnalyse(null,"Analyse retro-zoller",true);
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e.getMessage());
									}
									dr.setVisible(true);
									break;
		}
	}

}
