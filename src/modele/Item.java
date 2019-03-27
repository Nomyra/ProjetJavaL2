package modele;

public class Item {

	public String nom;
	public String categorie;
	public boolean fabricable;
	public Plan plan;
	public int nbFabrique;
	
	public Item(Plan pl, String n, String categ, int nombre) {
		this.nom = n;
		this.fabricable = true;
		this.categorie = categ;
		this.plan = pl;
		this.nbFabrique = nombre;
	}
	
	public Item(String n, String categ) {
		this.nom = n;
		this.fabricable = false;
		this.categorie = categ;
		this.nbFabrique = 0;
		this.plan = null;
	}
	
	public int hashCode() {
		return this.plan.hashCode();
	}
	
	public String toString() {
		return this.nom;
	}
}

