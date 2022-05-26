package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {

	
	private LabirintoBuilder vuota;
	private LabirintoBuilder singola;
	private LabirintoBuilder tre;
	
	private final static String nord = "nord";
	private final static String sud = "sud";

	private final static String primaStanza = "prima";
	private final static String secondaStanza = "seconda";
	private final static String terzaStanza = "terza";
	
	private final static String primoAttrezzo = "primo";
	
	

	@Before
	public void setUp() {
		this.vuota = new LabirintoBuilder();
		
		this.singola = new LabirintoBuilder();
		singola.addStanza(primaStanza);
		this.tre = new LabirintoBuilder();
		tre.addStanza(primaStanza);
		tre.addStanza(secondaStanza);
		tre.addStanza(terzaStanza);
		tre.impostaAdiacente(primaStanza, secondaStanza, nord);
		tre.impostaAdiacente(secondaStanza, primaStanza, sud);
		
	}
	
	/*
	 * test relativi ad addStanza
	 * */
	
	@Test
	public void testAddStanzaEmpty() {
		assertEquals(Collections.emptyMap(), vuota.getStanze());
	}
	
	@Test
	public void testAddStanzaSingola() {
		assertEquals(primaStanza, singola.getStanza(primaStanza).getNome());
		assertEquals(1, singola.getStanze().size());
	}
	
	@Test
	public void testAddStanzaGiaEsistente() {
		singola.addStanza(primaStanza);
		assertEquals(primaStanza, singola.getStanza(primaStanza).getNome());
		assertEquals(1, singola.getStanze().size());				// non aggiunge niente
	}
	
	@Test
	public void testAddStanzaTreVolte() {
		assertEquals(3, tre.getStanze().size());
		assertTrue(tre.getStanze().containsKey(primaStanza));
	}
	
	/*
	 * test relativi ad impostaAdiacenze
	 * */
	
	@Test
	public void testImpostaAdiacenteVuota() {
		vuota.impostaAdiacente(primaStanza, secondaStanza, nord);
		assertEquals(Collections.emptyMap(), vuota.getStanze());
	}
	
	@Test
	public void testImpostaAdiacenteNormale() {
		assertEquals(secondaStanza, tre.getStanza(primaStanza).getStanzaAdiacente(nord).getNome());
		assertEquals(primaStanza, tre.getStanza(secondaStanza).getStanzaAdiacente(sud).getNome());
	}
	
	@Test
	public void testImpostaAdiacenteStanzaCheNonEsiste() {
		singola.impostaAdiacente(primaStanza, secondaStanza, nord);
		assertNull(singola.getStanza(primaStanza).getStanzaAdiacente(nord));
	}
	
	@Test
	public void testImpostaAdiacenteGiaEsistenteInQuellaDirezione() {
		tre.impostaAdiacente(primaStanza, terzaStanza, nord);
		assertEquals(terzaStanza, tre.getStanza(primaStanza).getStanzaAdiacente(nord).getNome());		//cambia adiacenza
	}
	
	/*
	 * test relativi ad addAttrezzo
	 * */
	
	@Test
	public void testAddAttrezzoVuota() {
		vuota.addAttrezzo(primoAttrezzo, primaStanza, 5);
		assertNull(vuota.getStanza(primaStanza));
	}
	
	@Test
	public void testAddAttrezzoNormale() {
		singola.addAttrezzo(primoAttrezzo, primaStanza, 5);
		assertTrue(singola.getStanza(primaStanza).hasAttrezzo(primoAttrezzo));
	}
	
	@Test
	public void testAddAttrezzo() {
		singola.addAttrezzo(primoAttrezzo, primaStanza, 5);
		assertTrue(singola.getStanza(primaStanza).hasAttrezzo(primoAttrezzo));
	}
	
	/*
	 * test relativi ad addStanzaIniziale
	 * */
	
	@Test
	public void testAddStanzaIniziale() {
		singola.addStanzaIniziale(primaStanza);
		assertEquals(primaStanza, singola.getLabirinto().getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAddStanzaVincente() {
		singola.addStanzaVincente(primaStanza);
		assertEquals(primaStanza, singola.getLabirinto().getStanzaVincente().getNome());
	}

	@Test
	public void testAddStanzaInizialeEVincente() {
		tre.addStanzaIniziale(primaStanza);
		tre.addStanzaVincente(terzaStanza);
		assertEquals(primaStanza, tre.getLabirinto().getStanzaIniziale().getNome());
		assertEquals(terzaStanza, tre.getLabirinto().getStanzaVincente().getNome());
	}
	
	@Test
	public void testAddStanzaInizialeSovrascrivi() {
		tre.addStanzaIniziale(primaStanza);
		tre.addStanzaIniziale(terzaStanza);
		assertEquals(terzaStanza, tre.getLabirinto().getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAddStanzaVincenteSovrascrivi() {
		tre.addStanzaVincente(primaStanza);
		tre.addStanzaVincente(terzaStanza);
		assertEquals(terzaStanza, tre.getLabirinto().getStanzaVincente().getNome());
	}
	
	@Test
	public void testCreaStanzePredefinito() {
		Labirinto test = new LabirintoBuilder().creaStanze();
		assertEquals("Biblioteca", test.getStanzaVincente().getNome());
		assertEquals("Atrio", test.getStanzaIniziale().getNome());
	}
	
	
	
}
