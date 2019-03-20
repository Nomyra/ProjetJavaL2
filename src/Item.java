import java.util.ArrayList;

public class Item {

	public String nom;
	public String categorie;
	public String codeForme;
	public ArrayList<String> objets;
	public boolean fabricable;
	
	public Item(String[][] pl, String n, String categ) {
		this.nom = n;
		this.fabricable = true;
		this.categorie = categ;
		this.codeForme = "";
		boolean premier = true;
		int[] init = {0,0};
		for (int i=0; i<pl.length; i++) {
			for (int j=0; j<pl.length; j++) {
				if (pl[i][j] != "0") {
					if (premier) {
						this.codeForme += "2";
						init[0] = i;
						init[1] = j;
						premier = false;
					} else {
						this.codeForme += ""+ (10*(i-init[0]) +2+ j-init[1]) ;
					}
					this.objets.add(pl[i][j]);
				}
			}
		}
	}
	
	public Item(String n, String categ) {
		this.nom = n;
		this.fabricable = false;
		this.categorie = categ;
		this.codeForme = "";
		this.objets = new ArrayList<String>();
	}
	
	public int hashCode() {
		int code = this.codeForme.hashCode()*this.objets.hashCode();
		return code;
	}
}
