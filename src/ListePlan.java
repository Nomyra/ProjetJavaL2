
import java.util.ArrayList;

public class ListePlan extends ArrayList<TableauPlanTailleFixe>{

	public ListePlan() {
		super();
		for (int i=0; i< 9; i++) {
			this.add(new TableauPlanTailleFixe());
		}
	}
	
	public void ajouterPlan(Item plan) {
		this.get(plan.nbObjet -1).ajouter(plan.codeForme, plan);
	}
}
