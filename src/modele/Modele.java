package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
			System.out.println(e.getMessage());
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
		FileReader f = new FileReader("donnees/donnees.txt");
		BufferedReader br = new BufferedReader(f);
		Item it;
		while ((m = br.readLine()) != null){
			tab = m.split(";");
			if (tab.length != 1) {
				String[] sousTab = tab[1].split("/");
				String[][] pl = {sousTab[0].split(","),sousTab[1].split(","),sousTab[2].split(",")};
				Plan p1 = new Plan(pl);
				it = new Item(p1, tab[0], "", Integer.parseInt(tab[tab.length-1]));
				this.plans.ajouter(it);
			} else {
				it = new Item(tab[0], "");
			}
			this.reserve.ajouter(it);
		}

		f.close();
		br.close();
	}
	
	// pour mettre planEnCours à jour quand il change
	public void changerPlanEnCours() {
		
	}
}
