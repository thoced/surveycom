package gui;

import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListModel;

import sql.Transaction;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class DialogVisuIntervention extends JDialog implements WindowListener
{
	private JList list;
	public DialogVisuIntervention(Frame f,String titre,boolean modal)
	{
		super(f,titre,modal);
		
		this.setSize(640, 480);
		
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		list = new JList();
		list.setBounds(70, 64, 208, 69);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 50, 614, 343);
		getContentPane().add(scrollPane);
			
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
		// la fenetre s'ouvre on rafraichit la liste
		try 
		{
			String listInter = Transaction.getListIntervention();
			String[] inter = listInter.split("\r");
			// on crée le 
			DefaultListModel mod = new DefaultListModel();
			for(String s : inter)
			{
				mod.addElement(s);
			}
			
			this.list.setModel(mod);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
