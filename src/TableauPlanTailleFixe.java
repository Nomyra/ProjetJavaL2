import java.util.ArrayList;
import java.util.Hashtable;

public class TableauPlanTailleFixe extends Hashtable<String,ArrayList<Item>>{

	public TableauPlanTailleFixe() {
		super();
	}
	
	public void ajouter(String cl�, Item valeur) {
		this.get(cl�).add(valeur);
	}
}
