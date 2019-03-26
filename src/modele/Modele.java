package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import classesGenerales.*;

public class Modele {

	public TablePlans plans; //réserve de plans disponibles
	public Plan planEnCours; //plan en cours sur la table de craft
	public Inventaire inventaire; //inventaire des objets détenus par l'utilisateur (par nom et quantité)
	public TableItems reserve; //liste de tous les items disponibles (fabricables et non fabricables) rangés par nom
	public Item resultatCraft; //Item résultant du plan actuellement sur la table (null si le plan ne correspond à rien)

	public Modele() {
		try {
			this.chargerItem();
		} catch (IOException e) {
			System.out.println("erreur");
		}
		this.inventaire = new Inventaire(); //doit remplir plans et reserve
		String[][] planVide = {{"","",""},{"","",""},{"","",""}};
		this.planEnCours = new Plan(planVide);
		this.resultatCraft = null;
	}
	
	
	//pour charger tous les items au lancement de l'application
	//nécessite de savoir où/comment sont stockées/codées les infos
	public void chargerItem() throws IOException {
		this.plans = new TablePlans();
		this.reserve = new TableItems();
		String m;
		String[] tab;
		FileReader f = new FileReader("/home/ndupasqu/Workspace/ProjetJavaL2/donnees/donnees.txt");
		BufferedReader br = new BufferedReader(f);
		while ((m = br.readLine()) != null){
			tab = m.split(";");
			if (tab.length == 1) {
				this.plans.ajouter(new Item(tab[0], ""));
			} else {
				String[] sousTab = tab[1].split("/");
				String[][] pl = {sousTab[0].split(","),sousTab[1].split(","),sousTab[2].split(",")};
				Plan p1 = new Plan(pl);
				this.plans.ajouter(new Item(p1, tab[0], "t"));
			}
		}

		f.close();
		br.close();
		
		/*
		FileReader f2 = new FileReader("/home/ndupasqu/Téléchargements/choix.txt");
		BufferedReader br2 = new BufferedReader(f2);
		
		while ((m = br2.readLine()) != null){
			tab = m.split(";");
			this.situations.get(Integer.parseInt(tab[0])).ajouterChoix(new Choix(tab[2],tab[3],this.situations.get(Integer.parseInt(tab[1]))));
		}
		
		f2.close();
		br2.close();
		
		this.situationInitiale = this.situations.get(0);
		
		
		*/
	}
	
	// pour mettre planEnCours à jour quand il change
	public void changerPlanEnCours() {
		
	}
}
