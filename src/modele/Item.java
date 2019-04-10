package modele;

import java.io.Serializable;

public class Item implements Serializable{

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
		return this.nom +" "+ this.categorie;
	}
	
	public Inventaire besoins() {
		Inventaire besoin = new Inventaire();
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (this.plan.plan[i][j] != "") {
					besoin.ajouter(this.plan.plan[i][j], 1);
				}
			}
		}
		return besoin;
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

