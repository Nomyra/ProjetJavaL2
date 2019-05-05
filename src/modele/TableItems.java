package modele;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Hashtable;

public class TableItems extends Hashtable<String,Item> implements Serializable{

	public TableItems() {
		super();
	}

	public void ajouter(Item i) {
		this.put(i.nom, i);
	}

	public ArrayList<Item> rechercher(String nom) {
		ArrayList<Item> liste = new ArrayList<Item>();
		for (String n : this.keySet()) {
			String temp = Normalizer.normalize(nom, Normalizer.Form.NFD);
			nom = temp.replaceAll("[^\\p{ASCII}]", "").toUpperCase();
			if (this.get(n).nomRecherche.toUpperCase().contains(nom)) {
				liste.add(this.get(n));
			}
		}
		return liste;
	}
}
