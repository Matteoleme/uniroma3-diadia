package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	// stanze per il test di metodo ImpostaStanzaAdiacente
	private Stanza noAdiacenti;
	private Stanza tuttiAdiacenti;
	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza stanza3;
	private Stanza stanza4;
	private Stanza stanzaInPiu;

	// stanze per il test di metodo AddAttrezzo
	private Stanza noAttrezzi;
	private Stanza tuttiAttrezzi;
	private Stanza unAttrezzo;

	private Attrezzo martello;
	private Attrezzo spada;
	private Attrezzo pistola;
	private Attrezzo chiave_Inglese;
	private Attrezzo scalpello;
	private Attrezzo corda;
	private Attrezzo piccone;
	private Attrezzo ascia;
	private Attrezzo sasso;
	private Attrezzo cacciavite;
	private Attrezzo pala;

	@Before
	public void setUpStanzaAdiacente() {
		this.noAdiacenti = new Stanza("Cella");
		this.tuttiAdiacenti = new Stanza("Corridoio");
		this.stanza1 = new Stanza("Stanza1");
		this.stanza2 = new Stanza("Stanza2");
		this.stanza3 = new Stanza("Stanza3");
		this.stanza4 = new Stanza("Stanza4");
		this.stanzaInPiu = new Stanza("StanzaInPiu");

		// metto la stessa stanza in due direzioni diverse
		this.tuttiAdiacenti.impostaStanzaAdiacente("nord", stanza1);

		// riempio tutti i campi stanza adiacente
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", stanza2);
		this.tuttiAdiacenti.impostaStanzaAdiacente("est", stanza3);
		this.tuttiAdiacenti.impostaStanzaAdiacente("ovest", stanza4);

	}

	// Metto la stessa stanza in due posizioni diverse e deve rimanere il primo dei
	// due che ho messo
	@Test
	public void testImpostaStessaStanzaAdiacenteInPosizioneDiversa() {
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", stanza1);
		assertEquals(stanza2, tuttiAdiacenti.getStanzaAdiacente("sud"));
	}

	@Test
	public void testImpostaStessaStanzaAdiacente() {
		// aggiungo me stesso come stanza adiacente
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", tuttiAdiacenti);
		assertNotSame(tuttiAdiacenti, tuttiAdiacenti.getStanzaAdiacente("sud"));
	}

	@Test
	public void testImpostaNuovaAdiacente() {
		// aggiungo una stanza in piu' e dovrebbe essere sovrascritta
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", stanzaInPiu);
		assertEquals(stanzaInPiu, tuttiAdiacenti.getStanzaAdiacente("sud"));
	}

	@Test
	public void testImpostaStanzaAdiacenteVuota() {
		assertNull(noAdiacenti.getStanzaAdiacente("sud"));
	}

	@Test
	public void testGetNessunaDirezione() {
		assertEquals(Collections.emptyMap(), noAdiacenti.getDirezioni());
	}

	@Test
	public void testGetDirezioniTotali() {
		Map<String, Stanza> direzioni = new HashMap<>();
		direzioni.put("nord", stanza1);
		direzioni.put("sud", stanza2);
		direzioni.put("est", stanza3);
		direzioni.put("ovest", stanza4);
		assertEquals(direzioni, this.tuttiAdiacenti.getDirezioni());
	}

	@Test
	public void testStampeDirezioni() {
		System.out.println(this.tuttiAdiacenti.getDescrizione());
		System.out.println(this.noAdiacenti.getDescrizione());
	}

	@Before
	public void setUpAttrezzi() {
		this.noAttrezzi = new Stanza("Bagno");
		this.unAttrezzo = new Stanza("Camera da letto");
		this.tuttiAttrezzi = new Stanza("Cucina");

		martello = new Attrezzo("Martello", 10);
		spada = new Attrezzo("Spada", 7);
		pistola = new Attrezzo("Pistola", 5);
		chiave_Inglese = new Attrezzo("Chiave_Inglese", 5);
		scalpello = new Attrezzo("Scalpello", 2);
		corda = new Attrezzo("Corda", 2);
		piccone = new Attrezzo("Piccone", 7);
		ascia = new Attrezzo("Ascia", 6);
		sasso = new Attrezzo("Sasso", 1);
		cacciavite = new Attrezzo("Cacciavite", 3);
		pala = new Attrezzo("Pala", 3);

		this.tuttiAttrezzi.addAttrezzo(martello);
		this.tuttiAttrezzi.addAttrezzo(spada);
		this.tuttiAttrezzi.addAttrezzo(pistola);
		this.tuttiAttrezzi.addAttrezzo(chiave_Inglese);
		this.tuttiAttrezzi.addAttrezzo(scalpello);
		this.tuttiAttrezzi.addAttrezzo(corda);
		this.tuttiAttrezzi.addAttrezzo(piccone);
		this.tuttiAttrezzi.addAttrezzo(ascia);
		this.tuttiAttrezzi.addAttrezzo(sasso);
		this.tuttiAttrezzi.addAttrezzo(cacciavite);

		this.unAttrezzo.addAttrezzo(pala);
	}

	@Test
	public void testGetAttrezzoStanzaConUnAttrezzo() {
		Map<String, Attrezzo> test = new HashMap<>();
		test.put("Pala", pala);
		assertEquals(test, unAttrezzo.getAttrezzi());
	}

	@Test
	public void testGetAttrezzoVuota() {
		assertTrue(noAttrezzi.getAttrezzi().isEmpty());
	}

	@Test
	public void testAddAttrezzoStanzaPiena() {
		this.tuttiAttrezzi.addAttrezzo(pala);
		assertTrue(this.tuttiAttrezzi.getAttrezzi().containsKey("Pala"));
	}

	@Test
	public void testAddAttrezzoStanzaVuota() {
		this.noAttrezzi.addAttrezzo(pala);
		assertTrue(this.noAttrezzi.getAttrezzi().containsKey("Pala"));
	}

	@Test
	public void testAddAttrezzoGiaPresente() {
		this.noAttrezzi.addAttrezzo(pala);
		assertEquals(pala, this.noAttrezzi.addAttrezzo(pala));
	}

	@Test
	public void testHasAttrezzoTrue() {
		assertTrue(tuttiAttrezzi.hasAttrezzo("Martello"));
	}

	@Test
	public void testHasAttrezzoFalse() {
		assertFalse(tuttiAttrezzi.hasAttrezzo("Pala"));
	}

	@Test
	public void testHasAttrezzoStanzaSenzaAttrezzi() {
		assertFalse(noAttrezzi.hasAttrezzo("Pala"));
	}

	@Test
	public void testRemoveAttrezzoTrue() {
		assertTrue(tuttiAttrezzi.removeAttrezzo(ascia));
	}

	@Test
	public void testRemoveAttrezzoTrueRimanentiZero() {
		assertTrue(unAttrezzo.removeAttrezzo(pala));
	}

	@Test
	public void testRemoveAttrezzoMaNonCeNiente() {
		assertFalse(noAttrezzi.removeAttrezzo(ascia));
	}

	@Test
	public void testRemoveAttrezzoMaNonCe() {
		assertFalse(tuttiAttrezzi.removeAttrezzo(pala));
	}

	@Test
	public void testStampeAttrezzi() {
		System.out.println(this.unAttrezzo.getDescrizione());
		System.out.println(this.tuttiAttrezzi.getDescrizione());
	}

}