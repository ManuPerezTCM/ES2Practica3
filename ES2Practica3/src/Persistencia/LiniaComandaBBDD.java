package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Domini.Comandes.LiniaComanda;

public class LiniaComandaBBDD {
	private Connexio connexio;
	//Andreu
	public LiniaComandaBBDD() throws Exception{
			this.connexio = this.connexio.getConnexioBBDD();
		}
	
	
	//Andreu
	public int obtenirIdLiniaComandaBBDD() throws Exception {
		try {
			PreparedStatement pst = connexio.prepareStatement("SELECT seq_liniaComanda.NEXTVAL FROM DUAL");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			rs.next();
			int id_liniaComanda = rs.getInt(1);
			return id_liniaComanda;
		} catch (Exception e) {
			throw new Exception("Error obtenirIdLiniaComanda - " + e.getMessage());
		}
	}
	//Andreu
	public void afegirLiniaComandaBBDD(LiniaComanda linia) throws Exception {
		try {
			int idLiniaComanda;
			idLiniaComanda = obtenirIdLiniaComandaBBDD();
			String sql = "INSERT INTO linia_comanda(codi_comanda,codi_liniacomanda,codi_article,quantitat,preu_unitat) VALUES(?,?,?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			pst.clearParameters();
			pst.setInt(1, linia.getIdComanda());
			pst.setInt(2, idLiniaComanda);
			pst.setInt(3, linia.getIdArticle());
			pst.setInt(4, linia.getQuantitat());
			pst.setFloat(5, linia.getPreuUnitariArticle());
			pst.execute();
		} catch (Exception e) {
			throw new Exception("Error afegirComanda - " + e.getMessage());
		}

	}
	//Andreu
	public void eliminarLiniesComanda(int codiComanda) throws Exception {

		String sql = "delete from linia_comanda where codi_comanda = ?";
		PreparedStatement pst = connexio.prepareStatement(sql);
		pst.setInt(1, codiComanda);

		pst.execute();

	}
	//Andreu
	public ArrayList<String[]> obtenirLiniesComanda(int idComanda) throws Exception {
		try {
			String sql = "select a.id_article AS CODI, a.nom_article AS NOM, l.quantitat AS QUANTITAT, l.preu_unitat AS PREU from linia_comanda l JOIN article a ON l.codi_article=a.id_article where l.codi_comanda = ?";
			PreparedStatement pst = connexio.prepareStatement(sql);

			pst.setInt(1, idComanda);
			ArrayList<String[]> linies = new ArrayList<String[]>();
			ResultSet rs = pst.executeQuery();
			String[] linia;

			while (rs.next()) {
				linia = new String[4];
				linia[0] = String.valueOf(rs.getInt("CODI"));
				linia[1] = rs.getString("NOM");
				linia[2] = String.valueOf(rs.getInt("QUANTITAT"));
				linia[3] = String.valueOf(rs.getFloat("PREU"));
				linies.add(linia);
			}
			return linies;
		} catch (Exception e) {
			throw new Exception("Error al obtenir les linies - " + e.getMessage());
		}

	}
	
	 public void afegirVariesLinies(List<LiniaComanda> linies) throws Exception{
		  
		  int x = 0;
		  for (x = 0 ; x < linies.size(); x++  ){
		   afegirLiniaComandaBBDD(linies.get(x));   
		  }
	
	 }

}
