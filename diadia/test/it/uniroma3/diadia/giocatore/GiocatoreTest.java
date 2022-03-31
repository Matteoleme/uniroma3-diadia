package uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	
	private Giocatore SenzaBorsa;
	private Giocatore ConBorsa;
	private Borsa Busta;
	private Borsa Zaino;
	
	
	
	@Before
	public void setUp() throws Exception {
		SenzaBorsa = new Giocatore("Giovanni");
		ConBorsa = new Giocatore("Mario");
		Busta = new Borsa(1);
		Zaino = new Borsa(10);
		ConBorsa.setBorsa(Busta);
	}

	@Test
	public void testGetSenzaBorsa() {
		assertNull(SenzaBorsa.getBorsa());
	}
	
	@Test
	public void testGetConBorsa() {
		assertEquals(Busta, ConBorsa.getBorsa());
	}

}
