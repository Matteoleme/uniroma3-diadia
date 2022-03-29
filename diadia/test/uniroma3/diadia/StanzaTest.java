package uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class StanzaTest {
	
	
	// stanze per il test di metodo ImpostaStanzaAdiacente
	private Stanza noAdiacenti;
	private Stanza tuttiAdiacenti;
	private Stanza Stanza1;
	private Stanza Stanza2;
	private Stanza Stanza3;
	private Stanza Stanza4;
	private Stanza StanzaInPiu;
	
	// stanze per il test di metodo AddAttrezzo
	private Stanza noAttrezzi;		
	private Stanza tuttiAttrezzi;
	private Stanza unAttrezzo;
	
	private Attrezzo Martello;
	private Attrezzo Spada;
	private Attrezzo Pistola;
	private Attrezzo Chiave_Inglese;
	private Attrezzo Scalpello;
	private Attrezzo Corda;
	private Attrezzo Piccone;
	private Attrezzo Ascia;
	private Attrezzo Sasso;
	private Attrezzo Cacciavite;
	private Attrezzo Pala;
	
	
	
	@Before
	public void setUpStanzaAdiacente() {
		this.noAdiacenti = new Stanza("Cella");
		this.tuttiAdiacenti = new Stanza("Corridoio");
		this.Stanza1 = new Stanza("Stanza1");
		this.Stanza2 = new Stanza("Stanza2");
		this.Stanza3 = new Stanza("Stanza3");
		this.Stanza4 = new Stanza("Stanza4");
		this.StanzaInPiu = new Stanza("StanzaInPiu");
		
		// metto la stessa stanza in due direzioni diverse
		this.tuttiAdiacenti.impostaStanzaAdiacente("nord", Stanza1);
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", Stanza1);

		// aggiungo me stesso come stanza adiacente
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", tuttiAdiacenti);
		
		// riempio tutti i campi stanza adiacente
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", Stanza2);
		this.tuttiAdiacenti.impostaStanzaAdiacente("est", Stanza3);
		this.tuttiAdiacenti.impostaStanzaAdiacente("ovest", Stanza4);
		
	}

	// Metto la stessa stanza in due posizioni diverse e deve rimanere il primo dei
	// due che ho messo
	@Test
	public void testImpostaStessaStanzaAdiacenteInPosizioneDiversa() {
		assertEquals(Stanza1, tuttiAdiacenti.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStessaStanzaAdiacente() {
		assertNotSame(tuttiAdiacenti, tuttiAdiacenti.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testImpostaNuovaAdiacente() {
		// aggiungo una stanza in piu' e dovrebbe essere sovrascritta
		this.tuttiAdiacenti.impostaStanzaAdiacente("sud", StanzaInPiu);
		assertEquals(StanzaInPiu, tuttiAdiacenti.getStanzaAdiacente("sud"));
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
		
		Martello = new Attrezzo("Martello", 10);
		Spada = new Attrezzo("Spada", 7);
		Pistola = new Attrezzo("Pistola", 5);
		Chiave_Inglese = new Attrezzo("Chiave_Inglese", 5);
		Scalpello = new Attrezzo("Scalpello", 2);
		Corda = new Attrezzo("Corda", 2);
		Piccone = new Attrezzo("Piccone", 7);
		Ascia = new Attrezzo("Ascia", 6);
		Sasso = new Attrezzo("Sasso", 1);
		Cacciavite = new Attrezzo("Cacciavite", 3);
		Pala = new Attrezzo("Pala", 3);
		
		this.tuttiAttrezzi.addAttrezzo(Martello);
		this.tuttiAttrezzi.addAttrezzo(Spada);
		this.tuttiAttrezzi.addAttrezzo(Pistola);
		this.tuttiAttrezzi.addAttrezzo(Chiave_Inglese);
		this.tuttiAttrezzi.addAttrezzo(Scalpello);
		this.tuttiAttrezzi.addAttrezzo(Corda);
		this.tuttiAttrezzi.addAttrezzo(Piccone);
		this.tuttiAttrezzi.addAttrezzo(Ascia);
		this.tuttiAttrezzi.addAttrezzo(Sasso);
		this.tuttiAttrezzi.addAttrezzo(Cacciavite);
		
		this.unAttrezzo.addAttrezzo(Pala);
		
}
	
	
	@Test
	public void testGetAttrezzo() {
		Attrezzo[] arrayAttrezzi = {Martello, Spada, Pistola, Chiave_Inglese, Scalpello, 
			Corda, Piccone, Ascia, Sasso, Cacciavite};
		assertArrayEquals(arrayAttrezzi, tuttiAttrezzi.getAttrezzi());
	}
	
	@Test
	public void testGetAttrezzoVuota() {
		Attrezzo[] arrayAttrezziVuoto = new Attrezzo[10];
		assertArrayEquals(arrayAttrezziVuoto, noAttrezzi.getAttrezzi());
	}
	
	@Test
	public void testAddAttrezzoStanzaPiena() {
		assertEquals(false, this.tuttiAttrezzi.addAttrezzo(Pala));
	}
	
	@Test
	public void testAddAttrezzoStanzaVuota() {
		assertEquals(true, this.noAttrezzi.addAttrezzo(Pala));
	}
	
	@Test
	public void testAddAttrezzoGiaPresente() {
		this.noAttrezzi.addAttrezzo(Pala);
		assertEquals(true, this.noAttrezzi.addAttrezzo(Pala));
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
		assertTrue(tuttiAttrezzi.removeAttrezzo(Ascia));
	}
	
	@Test
	public void testRemoveAttrezzoTrueRimanentiZero() {
		assertTrue(unAttrezzo.removeAttrezzo(Pala));
	}
	
	@Test
	public void testRemoveAttrezzoMaNonCeNiente() {
		assertFalse(noAttrezzi.removeAttrezzo(Ascia));
	}
	
	@Test
	public void testRemoveAttrezzoMaNonCe() {
		assertFalse(tuttiAttrezzi.removeAttrezzo(Pala));
	}
	
}