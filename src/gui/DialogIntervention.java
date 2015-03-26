package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogIntervention extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tNumero;
	private String numero;
	private String user;
	private JTextField tUser;

	
	
	public String getNumero()
	{
		return this.numero;
	}
	
	public String getUser()
	{
		return this.user;
	}


	public DialogIntervention(Frame f,String titre,boolean modal) 
	{
		
		super(f,titre,modal);
		
		this.setSize(640, 480);
		
		this.setLocationRelativeTo(null);
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("center:636px"),},
			new RowSpec[] {
				RowSpec.decode("272px"),
				RowSpec.decode("top:198px"),}));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "1, 1, fill, fill");
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(167dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
			
			JLabel lblNewLabel = new JLabel("Numéro:");
			contentPanel.add(lblNewLabel, "4, 10");
		
		
		
			tNumero = new JTextField();
			contentPanel.add(tNumero, "8, 10, fill, default");
			tNumero.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Utilisateur:");
			contentPanel.add(lblNewLabel_1, "4, 14");
			
			tUser = new JTextField();
			contentPanel.add(tUser, "8, 14, fill, top");
			tUser.setColumns(10);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, "1, 2, default, bottom");
			
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		case "OK" : 	this.numero = this.tNumero.getText();
						this.user = this.tUser.getText();
						break;
	
		}
		
		this.setVisible(false);
		
	}
}
