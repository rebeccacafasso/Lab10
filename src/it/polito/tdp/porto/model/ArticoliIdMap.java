package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

public class ArticoliIdMap {

	
	private Map <Integer, Paper> map;
	
	public ArticoliIdMap(){
		map = new HashMap<>();
	}
	
	public Paper get (Integer cod){
		return map.get(cod);
	}
	
	public Paper put (Paper a){
		Paper old = map.get(a.getEprintid());
		if (old==null){
			map.put(a.getEprintid(), a);
			return a;
		}else
			return old;
	
	}
}
