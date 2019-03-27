package modele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModeleTest {

	Modele modele;
	
	@Before
	public void setUp() throws Exception {
		this.modele = new Modele();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(this.modele.plans.chercher(this.modele.reserve.get("Canne_a_peche").plan).toString(), "Canne_a_peche");
		assertEquals(this.modele.plans.chercher(this.modele.reserve.get("Canne_a_peche").plan), this.modele.reserve.get("Canne_a_peche"));
	}

	@Test
	public void testInventaire() {
		this.modele.inventaire.ajouter("Canne_a_peche", 3);
		this.modele.inventaire.retirer("Canne_a_peche");
		assertEquals((int) this.modele.inventaire.get("Canne_a_peche"), 2);
	}
	
	@Test (expected = NullPointerException.class)
	public void testErreurInventaire() {
		this.modele.inventaire.retirer("Canne_a_peche");
	}
}
