package modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Inventaire extends HashMap<String,Integer> implements Serializable{

	public Inventaire() {
		super();
	}
	
	public void ajouter(String s, int quantite) {
		if (this.get(s) == null) {
			this.put(s, quantite);
		} else {
			this.replace(s, this.get(s)+quantite);
		}
	}
	
	public void retirer(String s) {
		if (this.get(s) == 1) {
			this.remove(s);
		} else {
			this.replace(s, this.get(s)-1);
		}
	}
	
	public Inventaire comparer(Inventaire i) {
		String nom;
		Set<String> k = i.keySet();
		Iterator<String> it = k.iterator();
		Inventaire inv = new Inventaire();
		inv.putAll(i);
		while (it.hasNext()) {
			nom = it.next();
			if (this.get(nom) != null && this.get(nom)>inv.get(nom)) {
				inv.retirer(nom);
			}
		}
		return inv;
	}
}
