package modele;
import java.io.Serializable;
import java.util.ArrayList;

public class Plan implements Serializable{
	
	String[][] plan;
	
	public Plan(String[][] p) {
		this.plan = p;
	}

	public int hashCode() {
		ArrayList<String> materiaux = new ArrayList<String>();
		String codeForme = "";
		boolean premier = true;
		int[] init = {0,0};
		for (int i=0; i<this.plan.length; i++) {
			for (int j=0; j<this.plan.length; j++) {
				if (!(this.plan[i][j].equals(" "))) {
					if (premier) {
						codeForme += "2";
						init[0] = i;
						init[1] = j;
						premier = false;
					} else {
						codeForme += ""+ (10*(i-init[0]) +2+ j-init[1]) ;
					}
					materiaux.add(this.plan[i][j]);
				}
			}
		}
		return codeForme.hashCode()*materiaux.hashCode();
	}
	
	public String toString() {
		String m = "";
		for (int i=0; i<this.plan.length; i++) {
			for (int j=0; j<this.plan[0].length; j++) {
				m += " " + this.plan[i][j];
			}
		}
		return m;
	}
	
	public void modifierPlan(int i, int j, String objet) {
		this.plan[i][j] = objet;
	}
	
	public void modifierPlan(int i, int j) {
		this.plan[i][j] = " ";
	}
	public String[][] getPlan(){
		return this.plan;
	}
}