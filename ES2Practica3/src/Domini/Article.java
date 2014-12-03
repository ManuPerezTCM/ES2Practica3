package Domini;

public class Article {

	private int idArticle;
	private String nomArticle;
	private double preuUnitari;
	private int estoc_actual;
	
	
	

	public int getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public double getPreuUnitari() {
		return preuUnitari;
	}


	public void setPreuUnitari(double preuUnitari) {
		this.preuUnitari = preuUnitari;
	}


	public int getEstoc_actual() {
		return estoc_actual;
	}


	public void setEstoc_actual(int estoc_actual) {
		this.estoc_actual = estoc_actual;
	}


	public Article(int idArticle, String nomArticle, double preuUnitari, int estoc_actual) {
		super();
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.preuUnitari = preuUnitari;
		this.estoc_actual = estoc_actual;
	}
	
	
	
	
}
