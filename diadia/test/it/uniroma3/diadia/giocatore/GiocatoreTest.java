package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	
	private Giocatore senzaBorsa;
	private Giocatore conBorsa;
	private Borsa busta;
	@Before
	public void setUp() throws Exception {
		senzaBorsa = new Giocatore("Giovanni");
		conBorsa = new Giocatore("Mario");
		busta = new Borsa(1);
		new Borsa(10);
		conBorsa.setBorsa(busta);
	}

	@Test
	public void testGetSenzaBorsa() {
		assertNull(senzaBorsa.getBorsa());
	}
	
	@Test
	public void testGetConBorsa() {
		assertEquals(busta, conBorsa.getBorsa());
	}

}
