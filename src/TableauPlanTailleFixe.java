import java.util.ArrayList;
import java.util.Hashtable;

public class TableauPlanTailleFixe extends Hashtable<String,ArrayList<Item>>{

	public TableauPlanTailleFixe() {
		super();
	}
	
	public void ajouter(String clé, Item valeur) {
		this.get(clé).add(valeur);
	}
}
