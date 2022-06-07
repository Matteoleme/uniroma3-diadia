package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.IO;
import uniroma3.diadia.IOConsole;
import uniroma3.diadia.Partita;

public class MagoTest {
	
	private Partita partita;
	private Stanza stanza;
	
	private Attrezzo attrezzo;
	private Mago mago;

	@Before
	public void setUp() throws Exception {
		partita = new Partita("testMago", new IOConsole());
		stanza = new Stanza("mago");
		attrezzo = new Attrezzo("bacchetta", 3);
		mago = new Mago("merlino", attrezzo);
		
		partita.setStanzaCorrente(stanza);
		stanza.setPersonaggio(mago);
	}

	@Test
	public void testSaluta() {
		assertEquals("Ciao, io sono merlino. " + Mago.PRESENTAZIONE_DEFAULT, mago.saluta());
		assertEquals("Ciao, io sono merlino. Ci siamo gia' presentati!", mago.saluta());
	}
	
	@Test
	public void testAgisci() {
		assertFalse(stanza.hasAttrezzo(attrezzo.getNome()));
		assertEquals(Mago.MESSAGGIO_DONO, mago.agisci(partita));
		assertTrue(stanza.hasAttrezzo(attrezzo.getNome()));
		assertEquals(Mago.MESSAGGIO_SCUSE, mago.agisci(partita));
	}
	
	@Test
	public void testRiceviRegalo() {
		Attrezzo regalo = new Attrezzo("regalo", 4);
		assertEquals(Mago.MESSAGGIO_REGALO, mago.riceviRegalo(regalo, partita));
		assertEquals(2, regalo.getPeso());
		assertEquals(Mago.MESSAGGIO_REGALO, mago.riceviRegalo(regalo, partita));
		assertEquals(1, regalo.getPeso());
		assertEquals(Mago.MESSAGGIO_REGALO_PESO1, mago.riceviRegalo(regalo, partita));
		assertEquals(1, regalo.getPeso());
	}
	
	

}
