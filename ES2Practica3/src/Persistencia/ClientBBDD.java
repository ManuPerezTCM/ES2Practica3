package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Domini.Client;



public class ClientBBDD {
private Connexio connexio;
	
	
	public ClientBBDD() throws Exception{
		this.connexio = this.connexio.getConnexioBBDD();
	}
	public Client obtenirClient(String DNI) throws Exception {
		
		PreparedStatement s = connexio.prepareStatement("select * from client where DNI = ?"); 
		s.setString(1,DNI);
		ResultSet rs = s.executeQuery();
		rs.next();
		if (rs.getRow()== 0){
			return null;
		}
		Client c = new Client(rs.getString("DNI"),rs.getString("Nom"),rs.getString("cognom"),rs.getString("direccio"),rs.getString("telefon"),rs.getString("email"),rs.getInt("codi_area"));	
		return c;
	}

	public LinkedList<String> obtenirClientsPerVenedor(String idVenedor)throws Exception{
		try {
			LinkedList<String> retorn = new LinkedList<String>();
			PreparedStatement pst = connexio.prepareStatement("SELECT c.Dni, c.Nom, c.Cognom, c.Telefon "
					+ "FROM Client c JOIN Empleat e ON (e.zona_venda = c.zona) WHERE e.Dni = ?");
			pst.clearParameters();
			pst.setString(1 , idVenedor);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String dni = rs.getString("dni");
				String nom = rs.getString("nom");
				String cognom = rs.getString("cognom");
				String telefon = rs.getString("telefon");
				retorn.add(dni+" "+nom+" "+cognom+" "+telefon);
			}
			return retorn;
		} catch (Exception e) {
			throw new Exception("Error obtenirClientsPerVenedor - "+e.getMessage());
		}
	}
	
	
}
