package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

public class AutoreIdMap {
	
	
	private Map <Integer, Author> map;
	
	public AutoreIdMap(){
		map = new HashMap<>();
	}
	
	public Author get (Integer cod){
		return map.get(cod);
	}
	
	public Author put (Author a){
		Author old = map.get(a.getId());
		if (old==null){
			map.put(a.getId(), a);
			return a;
		}else
			return old;
	
	}
	

}
