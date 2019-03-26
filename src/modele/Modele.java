package modele;

import classesGenerales.*;

public class Modele {

	public TablePlans plans; //réserve de plans disponibles
	public Plan planEnCours; //plan en cours sur la table de craft
	public Inventaire inventaire; //inventaire des objets détenus par l'utilisateur (par nom et quantité)
	public TableItems reserve; //liste de tous les items disponibles (fabricables et non fabricables) rangés par nom

	public Modele() {
		this.chargerItem();
		this.inventaire = new Inventaire(); //doit remplir plans et reserve
		String[][] planVide = {{"","",""},{"","",""},{"","",""}};
		this.planEnCours = new Plan(planVide);
	}
	
	
	//pour charger tous les items au lancement de l'application
	//nécessite de savoir où/comment sont stockées/codées les infos
	public void chargerItem() {
		
	}
	
	// pour mettre planEnCours à jour quand il change
	public void changerPlanEnCours() {
		
	}
}
