package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

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
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", stanza1);

		// aggiungo me stesso come stanza adiacente
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", tuttiAdiacenti);
		
		// riempio tutti i campi stanza adiacente
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", stanza2);
		this.tuttiAdiacenti.impostaStanzaAdiacente("est", stanza3);
		this.tuttiAdiacenti.impostaStanzaAdiacente("ovest", stanza4);
		
	}

	// Metto la stessa stanza in due posizioni diverse e deve rimanere il primo dei
	// due che ho messo
	@Test
	public void testImpostaStessaStanzaAdiacenteInPosizioneDiversa() {
		assertEquals(stanza1, tuttiAdiacenti.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStessaStanzaAdiacente() {
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
	public void testGetDirezioniTotali() {
		String[] direzioni = {"nord", "sud", "est", "ovest"};
		assertArrayEquals(direzioni, this.tuttiAdiacenti.getDirezioni());
	}
	
	@Test
	public void testGetNessunaDirezione() {
		String[] direzioniVuoto = new String[0];
		assertArrayEquals(direzioniVuoto, noAdiacenti.getDirezioni());
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
	public void testGetAttrezzo() {
		Attrezzo[] arrayAttrezzi = {martello, spada, pistola, chiave_Inglese, scalpello, 
			corda, piccone, ascia, sasso, cacciavite};
		assertArrayEquals(arrayAttrezzi, tuttiAttrezzi.getAttrezzi());
	}
	
	@Test
	public void testGetAttrezzoVuota() {
		Attrezzo[] arrayAttrezziVuoto = new Attrezzo[10];
		assertArrayEquals(arrayAttrezziVuoto, noAttrezzi.getAttrezzi());
	}
	
	@Test
	public void testAddAttrezzoStanzaPiena() {
		assertEquals(false, this.tuttiAttrezzi.addAttrezzo(pala));
	}
	
	@Test
	public void testAddAttrezzoStanzaVuota() {
		assertEquals(true, this.noAttrezzi.addAttrezzo(pala));
	}
	
	@Test
	public void testAddAttrezzoGiaPresente() {
		this.noAttrezzi.addAttrezzo(pala);
		assertEquals(true, this.noAttrezzi.addAttrezzo(pala));
	}
	
	@Test
	public void testHasAttrezzoTrue() {
		assertEquals(true, tuttiAttrezzi.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testHasAttrezzoFalse() {
		assertEquals(false, tuttiAttrezzi.hasAttrezzo("Pala"));
	}
	
	@Test
	public void testHasAttrezzoStanzaSenzaAttrezzi() {
		assertEquals(false, noAttrezzi.hasAttrezzo("Pala"));
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
	
}