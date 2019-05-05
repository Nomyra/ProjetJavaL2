package modele;

import java.io.Serializable;

public class Item implements Serializable{

	public String nom;
	public String categorie;
	public String nomRecherche;
	public boolean fabricable;
	public Plan plan;
	public int nbFabrique;
	
	public Item(Plan pl, String n, String categ, int nombre, String nomR) {
		this.nom = n;
		this.fabricable = true;
		this.categorie = categ;
		this.plan = pl;
		this.nbFabrique = nombre;
		this.nomRecherche = nomR;
	}
	
	public Item(String n, String categ, String nomR) {
		this.nom = n;
		this.fabricable = false;
		this.categorie = categ;
		this.nbFabrique = 0;
		this.plan = null;
		this.nomRecherche = nomR;
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
}

