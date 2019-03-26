package classesGenerales;
import java.util.ArrayList;

public class Plan{
	
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
				if (this.plan[i][j] != "") {
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
}