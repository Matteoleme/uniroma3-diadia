package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.IOConsole;
import uniroma3.diadia.Partita;

public class CaneTest {

	private Partita partita;
	private Stanza stanza;
	
	private Attrezzo attrezzoRegalo;
	private Cane cane;

	@Before
	public void setUp() throws Exception {
		partita = new Partita("testCane", new IOConsole());
		stanza = new Stanza("cane");
		
		attrezzoRegalo = new Attrezzo("chiave", 2);
		cane = new Cane("fido", attrezzoRegalo);
		
		partita.setStanzaCorrente(stanza);
		stanza.setPersonaggio(cane);
	}

	@Test
	public void testSaluta() {
		assertEquals("Ciao, io sono fido. " + Cane.PRESENTAZIONE_DEFAULT, cane.saluta());
		assertEquals("Ciao, io sono fido. Ci siamo gia' presentati!", cane.saluta());
	}
	
	@Test
	public void testAgisci() {
		int cfu = partita.getGiocatore().getCfu();
		assertEquals("AAARRRRGGG! (-1 cfu)", cane.agisci(partita));
		assertEquals(--cfu, partita.getGiocatore().getCfu());
		cane.agisci(partita);
		assertEquals(--cfu, partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testRiceviRegaloPreferito() {
		Attrezzo ciboPreferito = new Attrezzo(Cane.CIBO_PREFERITO, 2);
		assertFalse(stanza.hasAttrezzo(attrezzoRegalo.getNome()));
		assertEquals(Cane.MESSAGGIO_RINGRAZIAMENTO, cane.riceviRegalo(ciboPreferito, partita));
		assertTrue(stanza.hasAttrezzo(attrezzoRegalo.getNome()));
		stanza.removeAttrezzo(attrezzoRegalo);
		assertEquals(Cane.MESSAGGIO_RINGRAZIAMENTO, cane.riceviRegalo(ciboPreferito, partita));
		assertFalse(stanza.hasAttrezzo(attrezzoRegalo.getNome()));
		
	}
	@Test
	public void testRiceviRegaloGenerico() {
		Attrezzo generico = new Attrezzo("generico", 2);
		int cfu = partita.getGiocatore().getCfu();
		assertFalse(stanza.hasAttrezzo(attrezzoRegalo.getNome()));
		assertEquals(Cane.MESSAGGIO_CATTIVO, cane.riceviRegalo(generico, partita));
		assertEquals(--cfu, partita.getGiocatore().getCfu());
		assertFalse(stanza.hasAttrezzo(attrezzoRegalo.getNome()));
	}
}
