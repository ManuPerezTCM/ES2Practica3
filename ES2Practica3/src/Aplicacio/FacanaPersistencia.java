package Aplicacio;

public class FacanaPersistencia{
	
	private FacanaPersistencia instancia;
	
	
	private  FacanaPersistencia(){
		
		instancia = new FacanaPersistencia();		
		
	}
	
	public FacanaPersistencia getInstance(){
		
		if (instancia == null){
		 new	FacanaPersistencia();
		}
		return instancia;		
	}
	
	
	
	
	
	
}