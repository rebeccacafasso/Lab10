package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	List <Author>autori;
	AutoreIdMap a;
	UndirectedGraph <Author, DefaultEdge> grafo;
	
	
	public Model (){
		
		a = new AutoreIdMap();
		
	}
	
	
	private PortoDAO dao = new PortoDAO();
	
	
	public Author getAutore(int id){
		return dao.getAutore(id);
	}
	
	public Paper getArticolo(int codice){
		return dao.getArticolo(codice);
	}
	
	public List<Author> getAutori(){
		if (autori==null)
			autori= dao.getAutori(a);
		return autori;
	}
	
	public List <Author> getCoautori ( Author autore){
		List <Author> ris = dao.getCoautori(a,autore);
		ris.remove(a);
		return ris;
	}
	
	
	
	public void creaGrafo(){
		grafo = new SimpleGraph <Author, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(grafo, this.getAutori());
		
		for (Author a : this.getAutori()){
			for (Author b: this.getCoautori(a)){
				if (!a.equals(b))
					grafo.addEdge(a, b);
			}
		}
		
		
		
		
		
	}
	
	public UndirectedGraph<Author, DefaultEdge> getGrafo(){
		if (grafo==null)
			this.creaGrafo();
		return grafo;
	}

	public List<Author> getNonCoautori(Author autore) {
		List <Author> coautori = dao.getCoautori(a,autore);
		List <Author> autori = this.getAutori();
		
		for (Author a : coautori){
			autori.remove(a);
		}
		return autori;
	}
	
	
	
	public List <Paper> calcolaCamminoMinimo(Author partenza, Author arrivo){
		
		DijkstraShortestPath <Author, DefaultEdge> dsp = new DijkstraShortestPath <Author, DefaultEdge>(grafo, partenza, arrivo);
		List <DefaultEdge>archi = dsp.findPathBetween(grafo,partenza, arrivo);
		
		List<Paper>articoli = new LinkedList<Paper>();
		
		for (DefaultEdge arco : archi){
			Author a1 = grafo.getEdgeSource(arco);
			Author a2 = grafo.getEdgeTarget(arco);
			articoli.addAll(dao.getPapersComuni(a1, a2));
		}
		return articoli;
		
		
	}
	
	
	
	
	
	
	
	
	
}
