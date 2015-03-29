package gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class DialogResultat extends JDialog implements ActionListener 
{
	private JEditorPane editorResult;

	public DialogResultat(Frame arg0, String arg1, boolean arg2) 
	{
		super(arg0, arg1, arg2);
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 51, 710, 488);
		getContentPane().add(scrollPane);
		
		editorResult = new JEditorPane();
		scrollPane.setViewportView(editorResult);
	}
	
	public void setText(String text)
	{
		editorResult.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
}
