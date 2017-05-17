package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.ArticoliIdMap;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AutoreIdMap;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {
	
	

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				rs.close();
				conn.close();
				return autore;
			}
			rs.close();
			conn.close();
		
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				rs.close();
				conn.close();
				return paper;
			}
			conn.close();
			rs.close();
			
			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getAutori(AutoreIdMap a) {
		
		final String sql = "SELECT * FROM author ORDER BY lastname ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			List <Author>autori = new ArrayList <Author>();

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autore =  a.put(autore);
				autori.add(autore);
				
			}
			rs.close();
			conn.close();

			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
		
		
		
		public List<Author> getCoautori(AutoreIdMap a, Author a1) {
			
			final String sql = "SELECT DISTINCT authorid  "+
								"FROM creator "+
								"WHERE eprintid IN "+
								"(SELECT eprintid FROM creator WHERE authorid =  ?  ) ";

			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				List <Author>autori = new ArrayList <Author>();
				st.setInt(1, a1.getId());

				ResultSet rs = st.executeQuery();

				while (rs.next()) {

					//Author autore = new Author(rs.getInt("authorid"),null, null);
					Author autore = this.getAutore(rs.getInt("authorid"));
					autore =  a.put(autore);
					autori.add(autore);
					
				}
				rs.close();
				conn.close();

				return autori;

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		
	}
		
		
	public List<Paper> getPapersComuni(Author autore1, Author autore2){
			
			final String sql ="SELECT eprintid "+
					"FROM creator "+
					"WHERE authorid = ? && eprintid IN (SELECT eprintid FROM creator WHERE authorid = ?) ";

				try {
					Connection conn = DBConnect.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);
					List <Author>autori = new ArrayList <Author>();
					st.setInt(1, autore1.getId());
					st.setInt(2, autore2.getId());
					List<Paper>articoli_comuni= new ArrayList<Paper>();

					ResultSet rs = st.executeQuery();

					while (rs.next()) {
						
						Paper p = new Paper (rs.getInt("eprintid"),null,null,null,null,null);
						articoli_comuni.add(p);

					
		
		
					}
					rs.close();
					conn.close();

					return articoli_comuni;

				} catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException("Errore Db");
				}

			
			
			
			
			
		}
	
	
	
	

	

	
	
	
	
	
	
	
	
	
}