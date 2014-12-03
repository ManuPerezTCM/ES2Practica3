package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Aplicacio.IComanda;
import Aplicacio.ProxyComanda;
import Domini.Client;
import Domini.Comanda;
import Domini.Empleat;
import Domini.Estat;
import Domini.MossoMagatzem;
import Domini.Repartidor;
import Domini.Comandes.LiniaComanda;

public class ComandaBBDD {

	private Connexio connexio;
	private ClientBBDD oConClient;
	private LiniaComandaBBDD oConLinia;
	// DUBTES: hem de veure com ho fem per a posar els parÃ metres de qui es
	// el venedor i qui es el repartidor i tot, si ho fem al obtenirComanda o
	// on.

	public ComandaBBDD() throws Exception {
		this.connexio = this.connexio.getConnexioBBDD();
		this.oConClient = new ClientBBDD();
		this.oConLinia=new LiniaComandaBBDD();
	}

	public int obtenirIdComandaBBDD() throws Exception {
		try {
			PreparedStatement pst = connexio.prepareStatement("SELECT seq_comanda.NEXTVAL FROM DUAL");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			rs.next();
			int id_comanda = rs.getInt(1);
			return id_comanda;
		} catch (Exception e) {
			throw new Exception("Error obtenirIdComanda - " + e.getMessage());
		}
	}

	public int afegirComandaBBDD(IComanda comanda) throws Exception {
		try {
			int id;
			id = obtenirIdComandaBBDD();
			String sql = "INSERT INTO Comanda(id_comanda,import_total,data_comanda,estat,codi_client) VALUES(?,?,?,?,?)";
			PreparedStatement pst = connexio.prepareStatement(sql);
			pst.clearParameters();
			pst.setInt(1, id);
			pst.setFloat(2, comanda.getImport_total());
			pst.setString(3, comanda.getDataComanda());
			pst.setString(4, comanda.getEstat().getNom());
			pst.setString(5, comanda.getIdClient());
			pst.execute();
			return id;
		} catch (Exception e) {
			throw new Exception("Error afegirComanda - " + e.getMessage());
		}
	}

	
	/// Modificat pel proxy
	public IComanda obtenirComandaBBDD(int id_comanda) throws Exception {
		try {
			PreparedStatement pst = connexio.prepareStatement("SELECT * FROM Comanda WHERE id_comanda = ?");
			pst.clearParameters();
			pst.setInt(1, id_comanda);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				float import_total = rs.getFloat("import_total");
				// ClientBBDD client = new ClientBBDD();
				// Aixo falla per lo dels enums, hem de veure com es fa, sino jo
				// soc partidari d'strings(manu)
				Estat estat = Estat.valueOf(rs.getString("estat").toUpperCase());
				IComanda c = new ProxyComanda(id_comanda, oConClient.obtenirClient(rs.getString("Codi_client")), rs.getString("data_comanda"), estat, import_total, this,oConLinia );
				// Setters d'empleats
				return c;
			} else {
				throw new Exception("No hi han comandes amb id: " + id_comanda);
			}
		} catch (Exception e) {
			throw new Exception("Error obtenirComanda - " + e.getMessage());
		}
	}

	/*
	 * public void modificarComandaBBDD(Comanda comanda) throws Exception{ try{
	 * 
	 * String sql =
	 * "UPDATE Comanda SET (id_comanda=?,import_total=?,data_comanda=?,data_facturacio=?,estat=?,venedor_dni=?,repartidor_dni=?,preparador_dni=?,reposador_dni=?,codi_client=?) WHERE id_comanda=?"
	 * ; PreparedStatement pst = connexio.prepareStatement(sql);
	 * pst.clearParameters(); pst.setInt(1, comanda.getNumComanda());
	 * pst.setFloat(2, comanda.getImport_total()); pst.setString(3,
	 * comanda.getDataComanda()); pst.setString(4,
	 * comanda.getData_facturacio()); pst.setString(4,
	 * comanda.getEstat().getNom()); pst.setString(5,
	 * comanda.getVenedor().getIdEmpleat()); pst.setString(6,
	 * comanda.getRepartidor().getIdEmpleat()); pst.setString(7,
	 * comanda.getMossoMagatzemPreparador().getIdEmpleat()); pst.setString(8,
	 * comanda.getMossoMagatzemReponedor().getIdEmpleat()); pst.setString(9,
	 * comanda.getIdClient()); pst.setInt(10, comanda.getNumComanda());
	 * pst.executeUpdate(); }catch(Exception e){ throw new
	 * Exception("Error modificarComanda - "+e.getMessage()); } }
	 */

	public void modificarComandaBBDD(IComanda comanda) throws Exception {
		try {

			String sql = "UPDATE comanda SET import_total=? WHERE id_comanda=?";
			PreparedStatement pst = connexio.prepareStatement(sql);
			pst.clearParameters();
			pst.setFloat(1, comanda.getImport_total());
			pst.setInt(2, comanda.getNumComanda());
			pst.execute();
		} catch (Exception e) {
			throw new Exception("Error modificar Comanda - " + e.getMessage());
		}
	}

	
	

	///nou Sergi pel proxy
	public void afegirVariesLinies(List<LiniaComanda> linies) throws Exception{
		
		int x = 0;
		for (x = 0 ; x < linies.size(); x++  ){
			
			oConLinia.afegirLiniaComandaBBDD(linies.get(x));
			
		}
		
		
	}

	public ArrayList<String> llistaCodiComandes() throws Exception {

		ArrayList<String> llistaCodis = new ArrayList<String>();

		PreparedStatement pst = connexio.prepareStatement("SELECT * FROM comanda");

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {

			llistaCodis.add(rs.getString("id_comanda"));
		}
		return llistaCodis;

	}

	public ArrayList<IComanda> ObtenirLlistaComandesPendents() throws Exception {
		IComanda comanda;
		ArrayList<IComanda> llistaComandes = new ArrayList<IComanda>();

		PreparedStatement pst = connexio.prepareStatement("SELECT * FROM comanda");

		ResultSet rs = pst.executeQuery();

		while (rs.next()) {

			int id = rs.getInt("id_comanda");
			Client client = oConClient.obtenirClient(rs.getString("codi_client"));
			String data = rs.getString("data_comanda");
			Estat estat = Estat.valueOf(rs.getString("estat"));
			Float import_total = rs.getFloat("import_total");
			comanda = new Comanda(id, client, data, estat, import_total);
			llistaComandes.add(comanda);
		}
		return llistaComandes;

	}

	

	

	///nou Sergi pel proxy
	public Repartidor obtenirRepartidor(int codiComanda) throws SQLException {
		  String sql = "select repartidor_dni from comanda where id_comanda = ?  ";
		  PreparedStatement pst = connexio.prepareStatement(sql);
		  
		  pst.setInt(1, codiComanda);
		  ResultSet rs = pst.executeQuery();
		  rs.next();
		  
		  String dni_repartidor = rs.getString("repartidor_dni");
		  String SqlRepartidor = "select * from empleat where dni = ?  ";
		  PreparedStatement pst1 = connexio.prepareStatement(SqlRepartidor);
		  
		  pst1.setString(1, dni_repartidor);
		  ResultSet rsRepartidor = pst1.executeQuery();
		  rsRepartidor.next();
		  
		  Repartidor repartidor = new Repartidor(rs.getString("dni"), rsRepartidor.getString("nom"), rsRepartidor.getString("cognom"), rsRepartidor.getString("email"), rsRepartidor.getString("password"));
		  return repartidor;

		 }


	 ///nou Sergi pel proxy
	 /// Aquest mètode modifica el repartidor de la comanda. Un cop modificat crida el mètode obtenir repartidor
	 ///  per recuperar el repartidor que acaba d'afegir per modificar la comanda que te el proxy.
	 
	 public void setRepartidor(int codiComanda, Empleat repartidor) throws Exception {
	  String sql = "update comanda set repartidor_dni = ? where id_comanda = ?";
	   PreparedStatement pst = connexio.prepareStatement(sql); 
	   pst.setString(1, repartidor.getIdEmpleat());
	   pst.setInt(2,codiComanda);
	   pst.executeQuery();
	   
	 
	 }


	 ///nou Sergi pel proxy
	 public MossoMagatzem getMossoMagatzem(int codiComanda) throws Exception {
	 throw new  Exception("No implementat");

	 }

	///nou Sergi pel proxy

	 public float setImportTotal(float Import, int codiComanda) throws Exception {

	  String sql = "update comanda set import_total = ? where id_comanda = ?";
	  PreparedStatement pst = connexio.prepareStatement(sql); 
	  pst.setFloat(1, Import);
	  pst.setInt(2, codiComanda);
	  pst.executeUpdate();
	  return Import;
	 }

	 ///nou Sergi pel proxy
	 public void setClient(Client client) throws Exception {
	  
	  String sql = "update comanda set codi_client = ? where id_comanda = ?";
	  PreparedStatement pst = connexio.prepareStatement(sql); 
	  pst.setString(1,  client.getDNI()  );
	  pst.executeUpdate();
	 
	 }


	///nou Sergi pel proxy
	 public int setNumeroComanda(int numComanda) throws Exception{
	 throw new Exception("El número de la comanda no es pot modificar");
	  
	  
	 
	 }
	/// nou Sergi pel proxy
	 public String obtenirDataFacturacio(int numComanda) throws Exception {
	 
	  String sql = "select data_facturacio from comanda";
	  PreparedStatement pst = connexio.prepareStatement(sql); 
	  pst.setInt(1, numComanda); 
	  ResultSet rs = pst.executeQuery();
	  rs.next();
	  
	  return rs.getString("data_facturacio");
	 }
	 
	 /// nou Sergi pel proxy
	 public void setDataComanda(int numComanda) throws SQLException {
	  String sql = "update comanda set data_comanda = ? where numComanda = ?";
	  PreparedStatement pst = connexio.prepareStatement(sql); 
	  pst.setInt(1,  numComanda  );
	  pst.executeUpdate(); 
	 
	 }
	 
	//nou Sergi pel proxy
	 public String getDataComanda(int numComanda) throws SQLException {
	  // TODO Auto-generated method stub
	  String sql = "select data_comanda from comanda where id_comanda = ?";
	  PreparedStatement pst = connexio.prepareStatement(sql); 
	  pst.setInt(1, numComanda);
	   
	  
	  ResultSet rs = pst.executeQuery();
	   return rs.getString("data_comanda"); 
	  
	 }
	
	
	
	
	
	
	
	

}
