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
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	private Stanza stanzaTest;
	
	@Before
	public void setUpStanze() {
		mappaProva = new Labirinto("Test", "Test");
		giocatore = new Giocatore("Manichino");
		partita = new Partita("Test", "Test");
		partita2 = new Partita("Test", "Test");		//Partita senza stanza corrente o vincente
		stanzaVincente = new Stanza("StanzaVincente");
		stanzaCorrente = new Stanza("StanzaCorrente");
		stanzaTest = new Stanza("StanzaTest");
		
		mappaProva.setStanzaIniziale(stanzaCorrente);
		partita.setStanzaCorrente(stanzaCorrente);
	}

	@Test
	public void testGetStanzaVincenteSenza() {
		assertNull(mappaProva.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaVincente() {
		mappaProva.setStanzaVincente(stanzaVincente);
		assertEquals(stanzaVincente, mappaProva.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaCorrenteAggiornato() {
		partita.setStanzaCorrente(stanzaTest);
		assertEquals(stanzaTest, partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaVincenteAggiornato() {
		mappaProva.setStanzaVincente(stanzaTest);
		assertEquals(stanzaTest, mappaProva.getStanzaVincente());
	}
	
	@Test
	public void testVintaSenzaStanzaVincente() {
		partita2.setStanzaCorrente(stanzaCorrente);
		assertEquals(false, partita.vinta());
	}
	
	@Test
	public void testVintaSenzaStanzaCorrente() {
		mappaProva.setStanzaVincente(stanzaVincente);
		assertEquals(false, partita2.vinta());
	}
	
	@Test
	public void testVintaSenzaNiente() {
		assertEquals(false, partita2.vinta());
	}

}
