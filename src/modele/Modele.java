package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.scene.image.Image;


public class Modele {

	public ArrayList<String> categories; //liste des catégories existantes
	public ArrayList<String> nom; //liste des noms d'item(clés reserve)
	public TablePlans plans; //réserve de plans disponibles
	public Plan planEnCours; //plan en cours sur la table de craft
	public Inventaire inventaire; //inventaire des objets détenus par l'utilisateur (par nom et quantité)
	public TableItems reserve; //liste de tous les items disponibles (fabricables et non fabricables) rangés par nom
	public boolean modeCreatif; //true si le jeu est en mode creatif, false sinon
	public Item resultatCraft; //Item résultant du plan actuellement sur la table (null si le plan ne correspond à rien)

	File fichierInv = new File("inventaire.dat");
	File fichierTab = new File("table.dat");
	File fichierMode = new File("mode.dat");

	public Modele() {
		try {
			this.chargerItem();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			FileInputStream fis = new FileInputStream(this.fichierInv);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.inventaire = (Inventaire)ois.readObject();

			fis = new FileInputStream(this.fichierTab);
			ois = new ObjectInputStream(fis);
			this.planEnCours = (Plan)ois.readObject();

			fis = new FileInputStream(this.fichierMode);
			ois = new ObjectInputStream(fis);
			this.modeCreatif = (boolean)ois.readObject();

			ois.close();
			fis.close();
		} catch(IOException | ClassNotFoundException e) {
			this.inventaire = new Inventaire();
			String[][] planVide = {{" "," "," "},{" "," "," "},{" "," "," "}};
			this.planEnCours = new Plan(planVide);
			this.resultatCraft = null;
		}
		this.resultatCraft = this.plans.chercher(this.planEnCours);
	}


	//pour charger tous les items au lancement de l'application
	public void chargerItem() throws IOException {
		this.categories = new ArrayList<String>();
		this.nom = new ArrayList<String>();
		this.plans = new TablePlans();
		this.reserve = new TableItems();
		String m;
		String[] tab;
		FileReader f = new FileReader("donnees/donnees.txt");
		BufferedReader br = new BufferedReader(f);
		Item it;
		while ((m = br.readLine()) != null){
			tab = m.split(";");
			nom.add(tab[0]);
			if (tab.length == 5) {
				String[] sousTab = tab[1].split("/");
				String[][] pl = {sousTab[0].split(","),sousTab[1].split(","),sousTab[2].split(",")};
				Plan p1 = new Plan(pl);
				it = new Item(p1, tab[0], tab[3], Integer.parseInt(tab[2]), tab[4]);
				this.plans.ajouter(it);
			} else {
				it = new Item(tab[0], tab[1], tab[2]);
			}
			if (!(this.categories.contains(it.categorie))) {
				this.categories.add(it.categorie);
			}
			this.reserve.ajouter(it);
		}

		f.close();
		br.close();
	}

	// pour enregistrer l'état de l'appli (sur demande de l'user)
	public void enregistrerEtat() {
		try {
			FileOutputStream fos = new FileOutputStream(this.fichierInv);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.inventaire);

			fos = new FileOutputStream(this.fichierTab);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.planEnCours);

			fos = new FileOutputStream(this.fichierMode);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.modeCreatif);

			oos.close();
			fos.close();
		} catch(IOException e1) {
			throw new RuntimeException("Impossible d'écrire les données");
		}
	}

	// supprime la sauvegarde existante
	public void resetSauvegarde() {
		this.fichierInv.delete();
		this.fichierTab.delete();
		this.fichierMode.delete();
	}

	public Item getResultatCraft() {
		return resultatCraft;
	}
	public void setResultatCraft(Item resultatCraft) {
		this.resultatCraft = resultatCraft;
	}
}
