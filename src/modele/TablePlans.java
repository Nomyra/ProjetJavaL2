package modele;

import java.io.Serializable;
import java.util.Hashtable;

public class TablePlans extends Hashtable<Integer, Item> implements Serializable {

	public TablePlans() {
		super();
	}
	
	public void ajouter(Item plan) {
		if (plan.fabricable) {
			this.put(plan.hashCode(), plan);
		}
	}

	public Item chercher(Plan plan){
		try {
			System.out.println(plan.toString() + "->" + plan.hashCode());
			return this.get(plan.hashCode());
		} catch(NullPointerException e) {
			return null;
		}
	}
}

