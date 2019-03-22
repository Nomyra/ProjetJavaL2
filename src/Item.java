public class Item {

	public String nom;
	public String categorie;
	public boolean fabricable;
	public Plan plan;
	
	public Item(Plan pl, String n, String categ) {
		this.nom = n;
		this.fabricable = true;
		this.categorie = categ;
		this.plan = pl;
	}
	
	public Item(String n, String categ) {
		this.nom = n;
		this.fabricable = false;
		this.categorie = categ;
	}
	
	public int hashCode() {
		return this.plan.hashCode();
	}
	
	public String toString() {
		return this.nom;
	}
}