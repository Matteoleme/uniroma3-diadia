package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import uniroma3.diadia.IOConsole;
import uniroma3.diadia.Partita;

public class ComandoPrendiPosaTest {

	private Partita partita;
	private Giocatore player;
	private Borsa borsa;
	private ComandoPrendi prendi;
	private ComandoPosa posa;
	private Stanza piena;
	private Stanza vuota;
	private IOConsole io;

	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	private Attrezzo attrezzo5;

	@Before
	public void setUp() throws Exception {
		io = new IOConsole();
		partita = new Partita("Test", io);
		player = partita.getGiocatore();
		borsa = new Borsa();
		player.setBorsa(borsa);

		prendi = new ComandoPrendi();
		posa = new ComandoPosa();

		piena = new Stanza("piena");
		vuota = new Stanza("vuota");

		attrezzo1 = new Attrezzo("attrezzo1", 1);
		attrezzo2 = new Attrezzo("attrezzo2", 2);
		attrezzo3 = new Attrezzo("attrezzo3", 3);
		attrezzo4 = new Attrezzo("attrezzo4", 4);
		attrezzo5 = new Attrezzo("attrezzo5", 5);

		piena.addAttrezzo(attrezzo1);
		piena.addAttrezzo(attrezzo2);
		piena.addAttrezzo(attrezzo3);
		piena.addAttrezzo(attrezzo4);

	}

	@Test
	public void testPrendiNormale() {
		partita.setStanzaCorrente(piena);
		prendi.setParametro("attrezzo1");
		prendi.esegui(partita);
		assertNull(piena.getAttrezzo("attrezzo1"));
		assertEquals(attrezzo1, borsa.getAttrezzo("attrezzo1"));
	}

	@Test
	public void testPrendiAttrezzoNonPresenteDaStanzaPiena() {
		partita.setStanzaCorrente(piena);
		prendi.setParametro("attrezzo6");
		prendi.esegui(partita);
		assertNull(borsa.getAttrezzo("attrezzo6"));
	}

	@Test
	public void testPrendiAttrezzoNonPresenteDaStanzaVuota() {
		partita.setStanzaCorrente(vuota);
		prendi.setParametro("attrezzo1");
		prendi.esegui(partita);
		assertNull(borsa.getAttrezzo("attrezzo1"));
	}

	@Test
	public void testPosaNormale() {
		partita.setStanzaCorrente(piena);
		borsa.addAttrezzo(attrezzo5);
		posa.setParametro("attrezzo5");
		posa.esegui(partita);
		assertEquals(attrezzo5, piena.getAttrezzo("attrezzo5"));
		assertFalse(borsa.hasAttrezzo("attrezzo5"));
	}
	
	@Test
	public void testPosaAttrezzoNonPresenteDaBorsaPiena() {
		partita.setStanzaCorrente(piena);
		borsa.addAttrezzo(attrezzo5);
		posa.setParametro("attrezzo6");
		posa.esegui(partita);
		assertNull(piena.getAttrezzo("attrezzo6"));
	}
	
	@Test
	public void testPosaAttrezzoNonPresenteDaBorsaVuota() {
		partita.setStanzaCorrente(piena);
		posa.setParametro("attrezzo6");
		posa.esegui(partita);
		assertNull(piena.getAttrezzo("attrezzo6"));
	}
	
	@Test
	public void testPrendiPosa() {
		partita.setStanzaCorrente(piena);
		
		prendi.setParametro("attrezzo1");
		prendi.esegui(partita);
		assertNull(piena.getAttrezzo("attrezzo1"));
		posa.setParametro("attrezzo1");
		posa.esegui(partita);
		assertEquals(attrezzo1, piena.getAttrezzo("attrezzo1"));
	}

	@Test
	public void testPosaPrendi() {
		partita.setStanzaCorrente(piena);
		borsa.addAttrezzo(attrezzo1);
		
		posa.setParametro("attrezzo1");
		posa.esegui(partita);
		assertFalse(borsa.hasAttrezzo("attrezzo1"));
		prendi.setParametro("attrezzo1");
		prendi.esegui(partita);
		assertEquals(attrezzo1, borsa.getAttrezzo("attrezzo1"));
	}
	
}
