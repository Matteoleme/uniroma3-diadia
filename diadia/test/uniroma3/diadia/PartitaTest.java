package uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {
	
	private Labirinto mappaProva;
	private Partita partita;
	private Partita partita2;
	private Giocatore giocatore;
	private Stanza StanzaVincente;
	private Stanza StanzaCorrente;
	private Stanza StanzaTest;
	
	@Before
	public void setUpStanze() {
		mappaProva = new Labirinto("Test", "Test");
		giocatore = new Giocatore("Manichino");
		partita = new Partita("Test", "Test");
		partita2 = new Partita("Test", "Test");		//Partita senza stanza corrente o vincente
		StanzaVincente = new Stanza("StanzaVincente");
		StanzaCorrente = new Stanza("StanzaCorrente");
		StanzaTest = new Stanza("StanzaTest");
		
		mappaProva.setStanzaIniziale(StanzaCorrente);
		partita.setStanzaCorrente(StanzaCorrente);
	}

	@Test
	public void testGetStanzaVincenteSenza() {
		assertNull(mappaProva.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaVincente() {
		mappaProva.setStanzaVincente(StanzaVincente);
		assertEquals(StanzaVincente, mappaProva.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaCorrenteAggiornato() {
		partita.setStanzaCorrente(StanzaTest);
		assertEquals(StanzaTest, partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaVincenteAggiornato() {
		mappaProva.setStanzaVincente(StanzaTest);
		assertEquals(StanzaTest, mappaProva.getStanzaVincente());
	}
	
	@Test
	public void testVintaSenzaStanzaVincente() {
		partita2.setStanzaCorrente(StanzaCorrente);
		assertEquals(false, partita.vinta());
	}
	
	@Test
	public void testVintaSenzaStanzaCorrente() {
		mappaProva.setStanzaVincente(StanzaVincente);
		assertEquals(false, partita2.vinta());
	}
	
	@Test
	public void testVintaSenzaNiente() {
		assertEquals(false, partita2.vinta());
	}

}
