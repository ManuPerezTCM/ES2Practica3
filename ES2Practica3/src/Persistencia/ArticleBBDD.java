package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Domini.Article;

public class ArticleBBDD {
	private Connexio connexio;
	
	
	public ArticleBBDD() throws Exception{
		this.connexio = this.connexio.getConnexioBBDD();
	}
	public Article obtenirArticle(int id_Article) throws Exception {
		
		PreparedStatement s = connexio.prepareStatement("select * from article where id_Article = ?"); 
		s.setInt(1, id_Article);
		ResultSet rs = s.executeQuery();
		rs.next();
		if (rs.getRow()== 0){
			return null;
		}
		Article a = new Article(rs.getInt("id_article"),rs.getString("nom_article"),rs.getDouble("preu_unitari"),rs.getInt("Estoc_actual"));	
		return a;
	}//Se tiene que hacer un trycatch general para que throwee el error con el nombre del metodo.
	
	
	//Andreu
public void actualitzarEstocArticle(int id_Article, int estoc) throws Exception{
	try {

		String sql = "UPDATE article SET estoc_actual=? WHERE id_article=?";
		PreparedStatement pst = connexio.prepareStatement(sql);
		pst.clearParameters();
		pst.setFloat(1, estoc);
		pst.setInt(2, id_Article);
		pst.execute();
	} catch (Exception e) {
		throw new Exception("Error al modificar estoc article - " + e.getMessage());
	}
}


public int obtenirEstocArticle(int idArticle) throws Exception {
	try {
	int estoc;
	PreparedStatement s = connexio.prepareStatement("select estoc_actual from article where id_Article = ?"); 
	s.setInt(1, idArticle);
	ResultSet rs = s.executeQuery();
	rs.next();
	if (rs.getRow()== 0){
		return 0;
	}
	
		estoc = rs.getInt("estoc_actual");
		return estoc;
	} catch (SQLException e) {
		throw new Exception("Error al obtenir estoc de l'article");
	}	
	
}
		
			
		
}
