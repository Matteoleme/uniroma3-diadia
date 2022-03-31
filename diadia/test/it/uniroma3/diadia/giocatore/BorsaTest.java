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
	private Borsa Rotta;
	private Borsa Strana;
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
	public void setUp() throws Exception {
		
		tuttiAttrezzi = new Borsa(PESOGRANDE);			//sforo per numero di attrezzi
		tantiAttrezzi = new Borsa(PESOMEDIO);			//sforo per peso attrezzi
		nienteAttrezzi = new Borsa(PESOPICCOLO);		
		Rotta = new Borsa(0);							
		Strana = new Borsa();							
		
		// setUp attrezzi
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
		//51 peso di tutti gli attrezzi
		
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

		this.tantiAttrezzi.addAttrezzo(Martello);
		this.tantiAttrezzi.addAttrezzo(Spada);
		this.tantiAttrezzi.addAttrezzo(Pistola);
		this.tantiAttrezzi.addAttrezzo(Chiave_Inglese);
		this.tantiAttrezzi.addAttrezzo(Scalpello);
		this.tantiAttrezzi.addAttrezzo(Corda);
		this.tantiAttrezzi.addAttrezzo(Piccone);
		this.tantiAttrezzi.addAttrezzo(Ascia);
		this.tantiAttrezzi.addAttrezzo(Cacciavite);
		this.tantiAttrezzi.addAttrezzo(Pala);
		
	}

	@Test
	public void testAddAttrezzoArrayPieno() {
		assertFalse(tuttiAttrezzi.addAttrezzo(Pala));
	}
	
	@Test
	public void testAddAttrezzoBorsaPesante() {
		assertFalse(tantiAttrezzi.addAttrezzo(Sasso));
	}
	
	@Test
	public void testAddAttrezzoBorsaRotta() {
		assertFalse(Rotta.addAttrezzo(Sasso));
	}
	
	@Test
	public void testAddAttrezzoBorsaVuota() {
		assertTrue(nienteAttrezzi.addAttrezzo(Ascia));
	}
	
	@Test
	public void testAddAttrezzoBorsaStrana() {
		assertTrue(Strana.addAttrezzo(Ascia));
	}
	
	@Test
	public void testGetAttrezzoTrue() {
		assertEquals(Ascia, tuttiAttrezzi.getAttrezzo("Ascia"));
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
		assertEquals(0, Rotta.getPeso());
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
		assertFalse(Rotta.hasAttrezzo("Ascia"));
	}
	
	@Test
	public void testGetPesoDopoAggiunta() {
		this.nienteAttrezzi.addAttrezzo(Corda);
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
		assertEquals(Martello, tuttiAttrezzi.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoMaNonCeNiente() {
		assertNull(Rotta.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoMaNonCeQuello() {
		assertNull(tuttiAttrezzi.removeAttrezzo("Pala"));
	}
	
}
