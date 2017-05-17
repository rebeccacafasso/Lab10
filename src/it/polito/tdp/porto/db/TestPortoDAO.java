package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.AutoreIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
		AutoreIdMap a = new AutoreIdMap();
		
		System.out.print(pd.getAutori(a));
		
		System.out.println(pd.getAutore(85));
		System.out.println(pd.getArticolo(2293546));
		System.out.println(pd.getArticolo(1941144));
		

	}

}
