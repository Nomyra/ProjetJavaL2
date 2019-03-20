import java.util.ArrayList;
import java.util.Hashtable;

public class TableauForme extends Hashtable<String,ArrayList<Item>>{
	
	public TableauForme() {
		super();
	}
	
	public void ajouter(Item plan) {
		this.get(plan.codeForme).add(plan);
	}

}
