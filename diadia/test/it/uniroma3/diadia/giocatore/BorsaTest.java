package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private Borsa pochiAttrezzi;
	private Borsa rotta;
	private Borsa strana;
	private Attrezzo martello;
	private Attrezzo spada;
	private Attrezzo peso5;
	private Attrezzo chiave_Inglese;
	private Attrezzo scalpello;
	private Attrezzo corda;
	private Attrezzo piccone;
	private Attrezzo ascia;
	private Attrezzo peso1;
	private Attrezzo peso3;

	private Attrezzo pala;

	@Before
	public void setUp() throws Exception {

		tuttiAttrezzi = new Borsa(PESOGRANDE); // sforo per numero di attrezzi
		tantiAttrezzi = new Borsa(PESOMEDIO); // sforo per peso attrezzi
		nienteAttrezzi = new Borsa(PESOPICCOLO);
		rotta = new Borsa(0);
		strana = new Borsa();

		// setUp attrezzi
		peso1 = new Attrezzo("Sasso", 1);
		peso3 = new Attrezzo("Cacciavite", 3);
		peso5 = new Attrezzo("Pistola", 5);
		martello = new Attrezzo("Martello", 10);
		spada = new Attrezzo("Spada", 7);
		chiave_Inglese = new Attrezzo("Chiave_Inglese", 5);
		scalpello = new Attrezzo("Scalpello", 2);
		corda = new Attrezzo("Corda", 2);
		piccone = new Attrezzo("Piccone", 7);
		ascia = new Attrezzo("Ascia", 6);
		pala = new Attrezzo("Pala", 3);
		// 51 peso di tutti gli attrezzi

		this.tuttiAttrezzi.addAttrezzo(martello);
		this.tuttiAttrezzi.addAttrezzo(spada);
		this.tuttiAttrezzi.addAttrezzo(peso5);
		this.tuttiAttrezzi.addAttrezzo(chiave_Inglese);
		this.tuttiAttrezzi.addAttrezzo(scalpello);
		this.tuttiAttrezzi.addAttrezzo(corda);
		this.tuttiAttrezzi.addAttrezzo(piccone);
		this.tuttiAttrezzi.addAttrezzo(ascia);
		this.tuttiAttrezzi.addAttrezzo(peso1);
		this.tuttiAttrezzi.addAttrezzo(peso3);

		this.tantiAttrezzi.addAttrezzo(martello);
		this.tantiAttrezzi.addAttrezzo(spada);
		this.tantiAttrezzi.addAttrezzo(peso5);
		this.tantiAttrezzi.addAttrezzo(chiave_Inglese);
		this.tantiAttrezzi.addAttrezzo(scalpello);
		this.tantiAttrezzi.addAttrezzo(corda);
		this.tantiAttrezzi.addAttrezzo(piccone);
		this.tantiAttrezzi.addAttrezzo(ascia);
		this.tantiAttrezzi.addAttrezzo(peso3);
		this.tantiAttrezzi.addAttrezzo(pala);

		pochiAttrezzi = new Borsa(PESOMEDIO);
		pochiAttrezzi.addAttrezzo(peso1); // peso 1
		pochiAttrezzi.addAttrezzo(peso5); // peso 5
		pochiAttrezzi.addAttrezzo(peso3); // peso 3

	}

	@Test
	public void testAddAttrezzoBorsaPesante() {
		assertFalse(tantiAttrezzi.addAttrezzo(peso1));
	}

	@Test
	public void testAddAttrezzoBorsaRotta() {
		assertFalse(rotta.addAttrezzo(peso1));
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

	@Test
	public void testListOrdinataPerPesoVuota() {
		assertEquals(Collections.emptyList(), nienteAttrezzi.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testListOrdinataPerPesoTreAttrezziPesiUguali() {
		Borsa test = new Borsa(PESOMEDIO);
		test.addAttrezzo(scalpello);
		test.addAttrezzo(corda);
		Attrezzo peso2 = new Attrezzo("Peso2", 2); // ricorda la maiuscola!
		test.addAttrezzo(peso2);
		assertEquals(Arrays.asList(corda, peso2, scalpello), test.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testListOrdinataPerPesoTreAttrezziPesiDiversi() {
		List<Attrezzo> elenco = new LinkedList<Attrezzo>();
		elenco.add(peso1);
		elenco.add(peso3);
		elenco.add(peso5);
		assertEquals(elenco, pochiAttrezzi.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testListOrdinataPerNomeVuota() {
		assertEquals(Collections.emptySet(), nienteAttrezzi.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testListOrdinataPerNomeTreElementi() {
		// peso 1 cacciavite, peso 5 pistola, peso 3 cacciavite
		Attrezzo[] expected = { peso3, peso5, peso1 };
		assertArrayEquals(expected, pochiAttrezzi.getContenutoOrdinatoPerNome().toArray());
	}

	@Test
	public void testRaggruppaPerPesoVuota() {
		assertEquals(Collections.emptyMap(), nienteAttrezzi.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testRaggruppaPerPesoTreAttrezziPesoUguale() {
		Borsa actual = new Borsa(PESOMEDIO);
		Attrezzo peso2 = new Attrezzo("Peso2", 2);
		actual.addAttrezzo(scalpello);
		actual.addAttrezzo(corda);
		actual.addAttrezzo(peso2);
		Map<Integer, Set<Attrezzo>> expected = new HashMap<>();
		expected.put(2, new HashSet<Attrezzo>(Arrays.asList(corda, peso2, scalpello)));
		assertEquals(expected, actual.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testRaggruppaPerPesoSeiAttrezzi() {
		pochiAttrezzi.addAttrezzo(scalpello);
		pochiAttrezzi.addAttrezzo(corda);
		pochiAttrezzi.addAttrezzo(chiave_Inglese);
		Map<Integer, Set<Attrezzo>> expeceted = new HashMap<>();
		expeceted.put(1, new HashSet<Attrezzo>(Arrays.asList(peso1)));
		expeceted.put(2, new HashSet<Attrezzo>(Arrays.asList(corda, scalpello)));
		expeceted.put(3, new HashSet<Attrezzo>(Arrays.asList(peso3)));
		expeceted.put(5, new HashSet<Attrezzo>(Arrays.asList(chiave_Inglese, peso5)));
		assertEquals(expeceted, pochiAttrezzi.getContenutoRaggruppatoPerPeso());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPesoVuoto() {
		assertEquals(Collections.emptySet(), nienteAttrezzi.getSortedSetOrdinatoPerPeso());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPesoGenerico() {
		Attrezzo[] expected  = {peso1, peso3, peso5};
		assertArrayEquals(expected, pochiAttrezzi.getSortedSetOrdinatoPerPeso().toArray());
	}
	
	@Test
	public void getSortedSetOrdinatoPerPesoGenericoStessoPesoNomeDiverso() {
		Borsa actual = new Borsa(PESOMEDIO);
		Attrezzo peso2 = new Attrezzo("Peso2", 2);
		actual.addAttrezzo(scalpello);
		actual.addAttrezzo(corda);
		actual.addAttrezzo(peso2);
		Attrezzo[] expected  = {corda, peso2, scalpello};
		assertArrayEquals(expected, actual.getSortedSetOrdinatoPerPeso().toArray());
	}

}
