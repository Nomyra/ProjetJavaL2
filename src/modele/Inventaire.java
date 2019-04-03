package modele;

import java.io.Serializable;
import java.util.HashMap;

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
}
