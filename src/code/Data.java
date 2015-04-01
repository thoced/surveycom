package code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Data 
{
	
	private String num;
	private int total;
	private int nbin;
	private int nbout; // nbout se crée en soustrayant le total par le nbin

	public Data() 
	{
		
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNbin() {
		return nbin;
	}

	public void setNbin(int nbin) {
		this.nbin = nbin;
	}

	public int getNbout()
	{
		// on le créé  à la volé
		return this.total - this.nbin;
		
	}

	
	
	
	
}
