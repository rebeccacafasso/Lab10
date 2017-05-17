package it.polito.tdp.porto.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getAutori());
		//System.out.println("GRAFO");
		//System.out.println(model.getGrafo());
		model.creaGrafo();
		Author a = model.getAutore(3019);
		Author b = model.getAutore(1891);
		System.out.println(model.calcolaCamminoMinimo(a, b));
	}

}
