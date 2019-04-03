package modele;

public class Item {

	public String nom;
	public String categorie; //definir des catégories fixes
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
		return this.nom + this.categorie;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public boolean isFabricable() {
		return fabricable;
	}

	public void setFabricable(boolean fabricable) {
		this.fabricable = fabricable;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public int getNbFabrique() {
		return nbFabrique;
	}

	public void setNbFabrique(int nbFabrique) {
		this.nbFabrique = nbFabrique;
	}
}

