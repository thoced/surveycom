package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class InterventionModel extends DefaultTableModel 
{
	private String[] nameColumns = {"id","numéro","utilisateur"};
	
//	private List<Intervention> listIntervention;
	
	private Vector<Intervention> listIntervention;
	
	

	public InterventionModel()
	{
		this.listIntervention = new Vector<Intervention>();
		
	}
	
	
	
	
	@Override
	public Vector getDataVector() {
		// TODO Auto-generated method stub
		return this.listIntervention;
	}




	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	

	public int getRowCount() 
	{
		// TODO Auto-generated method stub
		if(this.listIntervention == null)
			this.listIntervention = new Vector<Intervention>();
		
			return this.listIntervention.size();
	}

	
	public Object getValueAt(int arg0, int arg1) 
	{
		// TODO Auto-generated method stub
		
		Intervention i = listIntervention.get(arg0);
		
		switch(arg1)
		{
		case 0 : return i.getId();
		case 1 : return i.getNum();
		case 2 : return i.getUser();
		default : return null;
		
		}
	}
	
	public void insertIntervention(Intervention i)
	{
		listIntervention.add(i);
	}

	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.nameColumns[column];
	}
	
	

}
