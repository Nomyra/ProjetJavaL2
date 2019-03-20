import java.util.Hashtable;

public class TablePlans extends Hashtable<Integer,Item> {

	public TablePlans() {
		super();
	}
	
	public void ajouter(Item plan) {
		if (plan.fabricable) {
			this.put(plan.hashCode(), plan);
		}
	}

	public Item chercher(String forme, String[] materiaux) {
		return this.get(forme.hashCode()*materiaux.hashCode());
	}
}
