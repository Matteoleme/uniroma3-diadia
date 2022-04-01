package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private final static int PESOGRANDE = 1000;
	private final static int PESOMEDIO = 50;
	private final static int PESOPICCOLO = 10;
	
	private Borsa tuttiAttrezzi;
	private Borsa tantiAttrezzi;
	private Borsa nienteAttrezzi;
	private Borsa rotta;
	private Borsa strana;
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
	public void setUp() throws Exception {
		
		tuttiAttrezzi = new Borsa(PESOGRANDE);			//sforo per numero di attrezzi
		tantiAttrezzi = new Borsa(PESOMEDIO);			//sforo per peso attrezzi
		nienteAttrezzi = new Borsa(PESOPICCOLO);		
		rotta = new Borsa(0);							
		strana = new Borsa();							
		
		// setUp attrezzi
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
		//51 peso di tutti gli attrezzi
		
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

		this.tantiAttrezzi.addAttrezzo(martello);
		this.tantiAttrezzi.addAttrezzo(spada);
		this.tantiAttrezzi.addAttrezzo(pistola);
		this.tantiAttrezzi.addAttrezzo(chiave_Inglese);
		this.tantiAttrezzi.addAttrezzo(scalpello);
		this.tantiAttrezzi.addAttrezzo(corda);
		this.tantiAttrezzi.addAttrezzo(piccone);
		this.tantiAttrezzi.addAttrezzo(ascia);
		this.tantiAttrezzi.addAttrezzo(cacciavite);
		this.tantiAttrezzi.addAttrezzo(pala);
		
	}

	@Test
	public void testAddAttrezzoArrayPieno() {
		assertFalse(tuttiAttrezzi.addAttrezzo(pala));
	}
	
	@Test
	public void testAddAttrezzoBorsaPesante() {
		assertFalse(tantiAttrezzi.addAttrezzo(sasso));
	}
	
	@Test
	public void testAddAttrezzoBorsaRotta() {
		assertFalse(rotta.addAttrezzo(sasso));
	}
	
	@Test
	public void testAddAttrezzoBorsaVuota() {
		assertTrue(nienteAttrezzi.addAttrezzo(ascia));
	}
	
	@Test
	public void testAddAttrezzoBorsaStrana() {
		assertTrue(strana.addAttrezzo(ascia));
	}
	
	@Test
	public void testGetAttrezzoTrue() {
		assertEquals(ascia, tuttiAttrezzi.getAttrezzo("Ascia"));
	}
	
	@Test
	public void testGetAttrezzoNullVuota() {
		assertNull(nienteAttrezzi.getAttrezzo("Ascia"));
	}
	
	@Test
	public void testGetAttrezzoNullPiena() {
		assertNull(tuttiAttrezzi.getAttrezzo("Pala"));
	}
	
	@Test
	public void testGetPesoPiena() {
		assertEquals(48, tuttiAttrezzi.getPeso());
	}
	
	@Test
	public void testGetPesoVuota() {
		assertEquals(0, rotta.getPeso());
	}
	
	@Test
	public void testHasAttrezzoTrue() {
		assertTrue(tuttiAttrezzi.hasAttrezzo("Ascia"));
	}
	
	@Test
	public void testHasAttrezzoFalse() {
		assertFalse(tuttiAttrezzi.hasAttrezzo("Pala"));
	}
	
	@Test
	public void testHasAttrezzoFalseVuota() {
		assertFalse(rotta.hasAttrezzo("Ascia"));
	}
	
	@Test
	public void testGetPesoDopoAggiunta() {
		this.nienteAttrezzi.addAttrezzo(corda);
		assertEquals(2, nienteAttrezzi.getPeso());
	}
	
	@Test
	public void testIsEmptyTrue() {
		assertTrue(nienteAttrezzi.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		assertFalse(tuttiAttrezzi.isEmpty());
	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertEquals(martello, tuttiAttrezzi.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoMaNonCeNiente() {
		assertNull(rotta.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoMaNonCeQuello() {
		assertNull(tuttiAttrezzi.removeAttrezzo("Pala"));
	}
	
}
