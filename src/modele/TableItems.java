package modele;

import java.io.Serializable;
import java.util.Hashtable;

public class TableItems extends Hashtable<String,Item> implements Serializable{

	public TableItems() {
		super();
	}
	
	public void ajouter(Item i) {
		this.put(i.nom, i);
	}


}
