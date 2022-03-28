package uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	
	private Partita partita;
	private Partita partita2;
	private Stanza StanzaVincente;
	private Stanza StanzaCorrente;
	private Stanza StanzaTest;
	
	@Before
	public void setUpStanze() {
		partita = new Partita("Test");
		partita2 = new Partita("Test");		//Partita senza stanza corrente o vincente
		StanzaVincente = new Stanza("StanzaVincente");
		StanzaCorrente = new Stanza("StanzaCorrente");
		StanzaTest = new Stanza("StanzaTest");
		
		partita.setStanzaVincente(StanzaVincente);
		partita.setStanzaCorrente(StanzaCorrente);
	}

	@Test
	public void testGetStanzaVincenteSenza() {
		assertNull(partita2.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals(StanzaVincente, partita.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaCorrenteAggiornato() {
		partita.setStanzaCorrente(StanzaTest);
		assertEquals(StanzaTest, partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaVincenteAggiornato() {
		partita.setStanzaVincente(StanzaTest);
		assertEquals(StanzaTest, partita.getStanzaVincente());
	}
	
	@Test
	public void testVintaSenzaStanzaVincente() {
		partita2.setStanzaCorrente(StanzaCorrente);
		assertEquals(false, partita.vinta());
	}
	
	@Test
	public void testVintaSenzaStanzaCorrente() {
		partita2.setStanzaVincente(StanzaVincente);
		assertEquals(false, partita2.vinta());
	}
	
	@Test
	public void testVintaSenzaNiente() {
		assertEquals(false, partita2.vinta());
	}
	
	

}
