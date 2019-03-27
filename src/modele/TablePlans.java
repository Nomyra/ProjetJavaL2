package modele;

import java.util.Hashtable;

public class TablePlans extends Hashtable<Integer, Item> {

	public TablePlans() {
		super();
	}
	
	public void ajouter(Item plan) {
		if (plan.fabricable) {
			this.put(plan.hashCode(), plan);
		}
	}

	public Item chercher(Plan plan){
		return this.get(plan.hashCode());
	}

}

