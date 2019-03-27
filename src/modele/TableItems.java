package modele;

import java.util.Hashtable;

public class TableItems extends Hashtable<String,Item>{

	public TableItems() {
		super();
	}
	
	public void ajouter(Item i) {
		this.put(i.nom, i);
	}
}
