package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class InterventionModel extends DefaultTableModel 
{
	private List<Intervention> listIntervention;

	public InterventionModel()
	{
		this.listIntervention = new ArrayList<Intervention>();
	}
	
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	
	public int getRowCount() 
	{
		// TODO Auto-generated method stub
		if(this.listIntervention == null) this.listIntervention = new ArrayList<Intervention>();
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
		switch(column)
		{
		case 0 : return "id";
		
		case 1 : return "num";
		
		case 2 : return "user";
		
		default: return "";
		}
	}
	
	

}
