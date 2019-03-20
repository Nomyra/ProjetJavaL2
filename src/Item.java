
public class Item {

	public String nom;
	public String categorie;
	public String codeForme;
	public String[][] plan;
	public boolean fabricable;
	public int nbObjet;
	
	public Item(String[][] pl, String n, String categ) {
		this.nom = n;
		this.plan = pl;
		this.fabricable = true;
		this.categorie = categ;
		this.codeForme = "";
		boolean premier = true;
		int[] init = {0,0};
		for (int i=0; i<this.plan.length; i++) {
			for (int j=0; j<this.plan.length; j++) {
				if (this.plan[i][j] != "0") {
					if (premier) {
						this.codeForme += "2";
						init[0] = i;
						init[1] = j;
						premier = false;
					} else {
						this.codeForme += ""+ (10*(i-init[0]) +2+ j-init[1]) ;
					}
					this.nbObjet += 1;
				}
			}
		}
	}
	
	public Item(String n, String categ) {
		this.nom = n;
		this.fabricable = false;
		this.categorie = categ;
		this.codeForme = null;
		this.plan = null;
	}
}
