package classesGenerales;

import java.util.HashMap;

public class Inventaire extends HashMap<String,Integer>{

	public Inventaire() {
		super();
	}
	
	public void ajouter(String s) {
		if (this.get(s) == null) {
			this.put(s, 1);
		} else {
			this.put(s, this.get(s)+1);
		}
	}
}
