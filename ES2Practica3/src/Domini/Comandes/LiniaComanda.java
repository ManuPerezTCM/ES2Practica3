package Domini.Comandes;

public class LiniaComanda {
	private int idLiniaComanda;
	private int idArticle;
	private float preuUnitariArticle;
	private int quantitat;
	private int idComanda;
	
	public LiniaComanda(int idLiniaComanda, int idComanda,int article, int quantitat, float preuUnitat){
		this.idLiniaComanda=idLiniaComanda;
		this.idComanda=idComanda;
		this.idArticle=article;
		this.quantitat=quantitat;
		this.preuUnitariArticle=preuUnitat;
	}
	public LiniaComanda(int idComanda,int article, int quantitat, float preuUnitat){
		this.idComanda=idComanda;
		this.idArticle=article;
		this.quantitat=quantitat;
		this.preuUnitariArticle=preuUnitat;
	}
	
	public void setIdComanda(int idComanda){
		this.idComanda=idComanda;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public float getPreuUnitariArticle() {
		return preuUnitariArticle;
	}
	public void setPreuUnitariArticle(float preuUnitariArticle) {
		this.preuUnitariArticle = preuUnitariArticle;
	}
	public int getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}
	public int getIdComanda() {
		return idComanda;
	}
	public void setIdLiniaComanda(int idLiniaComanda){
		this.idLiniaComanda=idLiniaComanda;
	}		
}
