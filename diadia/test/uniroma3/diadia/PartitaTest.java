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
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	private Stanza stanzaTest;
	private IOConsole io;
	
	@Before
	public void setUpStanze() {
		io = new IOConsole();
		mappaProva = new Labirinto("Test");
		new Giocatore("Manichino");
		partita = new Partita("Test", io);
		partita2 = new Partita("Test", io);		//Partita senza stanza corrente o vincente
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

	/*@Before
	public void setUpStanzeEff() {
		partitaEff = new Partita("Test");
		mappa = partitaEff.getLabirinto();
		mappaFix = mappa.getLabirintoFixture();
		mappaFix.creaStanzeUni();
	}
	
	@Test
	public void testGetStanzaCorrenteEff() {
		assertNotNull(partitaEff.getStanzaCorrente());
	}*/
	
}
