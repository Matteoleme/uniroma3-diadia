package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	
	
	private Labirinto vuoto;
	private Labirinto labirinto1;
	private Labirinto labirinto2;
	
	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza stanza3;
	
	@Before
	public void setUp(){
		vuoto = new Labirinto("Bunker");
		labirinto1 = new Labirinto("Labirinto1");
		labirinto2 = new Labirinto("Labirinto2");
		
		stanza1 = new Stanza("Stanza1");
		stanza2 = new Stanza("Stanza2"); 
		stanza3 = new Stanza("Stanza3"); 
		
		labirinto1.setStanzaIniziale(stanza1);
		labirinto1.setStanzaVincente(stanza2);
		
		labirinto2.setStanzaIniziale(stanza3);
		labirinto2.setStanzaVincente(stanza3);
	}

	@Test
	public void testSetStanzaVincenteVuota() {
		assertNull(vuoto.getStanzaVincente());
	}
	
	@Test
	public void testSetStanzaVincenteDiversa() {
		assertEquals(stanza2, labirinto1.getStanzaVincente());
	}
	
	@Test
	public void testSetStanzaVincenteUguale() {
		assertEquals(stanza3, labirinto2.getStanzaVincente());
	}

	@Test
	public void testSetStanzaInizialeVuota() {
		assertNull(vuoto.getStanzaIniziale());
	}
	
	@Test
	public void testSetStanzaInizialeDiversa() {
		assertEquals(stanza1, labirinto1.getStanzaIniziale());
	}
	
	@Test
	public void testSetStanzaInizialeUguale() {
		assertEquals(stanza3, labirinto2.getStanzaIniziale());
	}
	
	/*@Before
	public void setUpEffettivo(){
		mappa = new Labirinto("Mappa");
		mappaFix = mappa.getLabirintoFixture();
		mappaFix.creaStanzeUni();
	}
	
	@Test
	public void testGetStanzaVincenteEff() {
		assertNotNull(mappa.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaInizialeEff() {
		assertNotNull(mappa.getStanzaIniziale());
	}*/

}
