package Aplicacio;

import java.util.LinkedList;

import Domini.Empleat;
import Persistencia.EmpleatBBDD;


public class ControladorEmpleat {
	
	private EmpleatBBDD empleatBBDD;
	
	public ControladorEmpleat () throws Exception{
		empleatBBDD = new EmpleatBBDD();
	}
	
	public String aconseguirPassword(String idEmpleat)throws Exception{
		try {
			return empleatBBDD.recuperarPassword(idEmpleat);
		} catch (Exception e) {
			throw new Exception("Error aconseguirPassword - "+e.getMessage());
		}
	}
	
	public Empleat aconseguirEmpleat(String idEmpleat)throws Exception{
		try {
			return this.empleatBBDD.recuperarEmpleat(idEmpleat);
		} catch (Exception e) {
			throw new Exception("Error aconseguirEmpleat - "+e.getMessage());
		}
	}
	
	public LinkedList<String> aconseguirVenedors() throws Exception{
		try {
			return this.empleatBBDD.recuperarVenedors();
		} catch (Exception e) {
			throw new Exception("Error aconseguirVenedors - "+e.getMessage());
		}
	}
	
	public LinkedList<String> aconseguirAdministratius() throws Exception{
		try {
			return this.empleatBBDD.recuperarAdministratius();
		} catch (Exception e) {
			throw new Exception("Error aconseguirAdministratius - "+e.getMessage());
		}
	}
	
	public LinkedList<String> aconseguirRepartidors() throws Exception{
		try {
			return this.empleatBBDD.recuperarRepartidors();
		} catch (Exception e) {
			throw new Exception("Error aconseguirRepartidors - "+e.getMessage());
		}
	}
	
	public LinkedList<String> aconseguirMossos() throws Exception{
		try {
			return this.empleatBBDD.recuperarMossos();
		} catch (Exception e) {
			throw new Exception("Error aconseguirMossos - "+e.getMessage());
		}
	}
	
	public LinkedList<String> aconseguirEmpleatsPerTipus(String tipus) throws Exception{
		try {
			return this.empleatBBDD.recuperarEmpleatsPerTipus(tipus);
		} catch (Exception e) {
			throw new Exception("Error aconseguirEmpleatsPerTipus - "+e.getMessage());
		}
	}
	
	public LinkedList<String> aconseguirTotsEmpleats() throws Exception{
		try {
			return this.empleatBBDD.recuperarEmpleats();
		} catch (Exception e) {
			throw new Exception("Error aconseguirTotsEmpleats - "+e.getMessage());
		}
	}
}
