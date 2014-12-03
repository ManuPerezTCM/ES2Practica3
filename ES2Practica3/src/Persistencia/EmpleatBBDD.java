package Persistencia;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import Domini.Empleat;
import Domini.Zona;
import Domini.FactoriaEmpleats.FactoriaAbstracteEmpleats;

public class EmpleatBBDD {

	private Connexio connexio;
	
	public EmpleatBBDD() throws Exception{
		this.connexio = this.connexio.getConnexioBBDD();
	}
	
	public String recuperarPassword(String DNI)throws Exception{
		try {
			PreparedStatement pst = connexio.prepareStatement("SELECT password FROM Empleat WHERE DNI = ?");
			pst.clearParameters();
			pst.setString(1, DNI);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return rs.getString("password");
			}
			throw new Exception("No hi ha cap empleat amb DNI: "+DNI);
		} catch (Exception e) {
			throw new Exception("Error recuperarPasword - "+e.getMessage());
		}
	}
	
	public Empleat recuperarEmpleat(String DNI)throws Exception{
		try {
			PreparedStatement pst = connexio.prepareStatement("SELECT nom, cognom, email, password, tipus FROM Empleat WHERE DNI = ?");
			pst.clearParameters();
			pst.setString(1, DNI);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				Empleat e = FactoriaAbstracteEmpleats.crearEmpleat(DNI, rs.getString("nom"), rs.getString("cognom"), rs.getString("email"), rs.getString("tipus"), rs.getString("password"));
				return e;
			}
			throw new Exception("No sha trobat cap empleat amb id: "+DNI);
		} catch (Exception e) {
			throw new Exception ("Error recuperarEmpleat - "+e.getMessage());
		}
	}
	
	public LinkedList<String> recuperarEmpleats() throws Exception{
		LinkedList<String> retorn = new LinkedList<String>();
		try{
			PreparedStatement pst = connexio.prepareStatement("SELECT DNI FROM Empleat");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				retorn.add(rs.getString("DNI"));
			}
			return retorn;
		}catch(Exception e){
			throw new Exception("Error recuperarEmpleats - "+e.getMessage());
		}
	}
	
	public LinkedList<String> recuperarVenedors() throws Exception{
		LinkedList<String> retorn = new LinkedList<String>();
		try{
			PreparedStatement pst = connexio.prepareStatement("SELECT DNI FROM Empleat WHERE tipus = Venedor");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				retorn.add(rs.getString("DNI"));
			}
			return retorn;
		}catch(Exception e){
			throw new Exception("Error recuperarVenedors - "+e.getMessage());
		}
	}
	
	public LinkedList<String> recuperarRepartidors() throws Exception{
		LinkedList<String> retorn = new LinkedList<String>();
		try{
			PreparedStatement pst = connexio.prepareStatement("SELECT DNI FROM Empleat WHERE tipus = Repartidor");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				retorn.add(rs.getString("DNI"));
			}
			return retorn;
		}catch(Exception e){
			throw new Exception("Error recuperarRepartidors - "+e.getMessage());
		}
	}
	
	public LinkedList<String> recuperarMossos()throws Exception{
		LinkedList<String> retorn = new LinkedList<String>();
		try{
			PreparedStatement pst = connexio.prepareStatement("SELECT DNI FROM Empleat WHERE tipus = Mosso");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				retorn.add(rs.getString("DNI"));
			}
			return retorn;
		}catch(Exception e){
			throw new Exception("Error recuperarMossos - "+e.getMessage());
		}
	}
	
	public LinkedList<String> recuperarAdministratius()throws Exception{
		LinkedList<String> retorn = new LinkedList<String>();
		try{
			PreparedStatement pst = connexio.prepareStatement("SELECT DNI FROM Empleat WHERE tipus = Administratiu");
			pst.clearParameters();
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				retorn.add(rs.getString("DNI"));
			}
			return retorn;
		}catch(Exception e){
			throw new Exception("Error recuperarAdministratius - "+e.getMessage());
		}
	}
	
	public LinkedList<String> recuperarEmpleatsPerTipus(String tipus)throws Exception{
		LinkedList<String> retorn = new LinkedList<String>();
		try{
			PreparedStatement pst = connexio.prepareStatement("SELECT DNI FROM Empleat WHERE tipus = ?");
			pst.clearParameters();
			pst.setString(1, tipus);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				retorn.add(rs.getString("DNI"));
			}
			return retorn;
		}catch(Exception e){
			throw new Exception("Error recuperarEmpleatsPerTipus - "+e.getMessage());
		}
	}
	
	
	public Zona getZona(String DNI)throws Exception{
		try {
			PreparedStatement pst = connexio.prepareStatement("SELECT idZona FROM Empleat WHERE DNI = ?");
			pst.clearParameters();
			pst.setString(1, DNI);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return new Zona(rs.getInt("ID_ZONA"));
			}
			throw new Exception("L'empleat "+DNI+" no té cap Zona assignada.");
		} catch (Exception e) {
			throw new Exception("Error getZona - "+e.getMessage());
		}
	}
}
