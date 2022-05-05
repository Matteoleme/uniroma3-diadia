package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private Stanza normale1;
	private Stanza normale2;
	private StanzaBloccata bloccata;
	private StanzaBloccata pazza;				//stanza bloccata che in realta non ha stanze adicenti in quella posizione
	private Attrezzo chiave;

	@Before
	public void setUp() throws Exception {
		normale1 = new Stanza("normale1");
		normale2 = new Stanza("normale2");
		bloccata = new StanzaBloccata("bloccata", "nord");
		pazza = new StanzaBloccata("stanza pazza", "sud", "spranga");
		chiave = new Attrezzo("chiave", 1);
		
		normale1.impostaStanzaAdiacente("sud", bloccata);
		bloccata.impostaStanzaAdiacente("nord", normale1);
		bloccata.impostaStanzaAdiacente("sud", normale2);
		normale2.impostaStanzaAdiacente("nord", bloccata);

	}
	
	/*
	 * Test Senza attrezzo nelle stanze
	 */
	@Test
	public void testGetStanzaAdiacenteDaNormaleABloccata() {
		assertEquals(bloccata, normale2.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacenteDaBloccataANormaleDirezioneNonBloccata() {
		assertEquals(normale2, bloccata.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteDaBloccataANormaleDirezioneBloccata() {
		assertEquals(bloccata, bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteDaNormaleABloccataMaBloccataInSensoInverso() {
		assertEquals(bloccata, normale1.getStanzaAdiacente("sud"));
	}
	
	/*
	 * Introduzione di attrezzi
	 *  
	 */
	@Test
	public void testGetStanzaAdiacenteDaBloccataANormaleConAttrezzoGenerico() {
		Attrezzo generico = new Attrezzo("generico", 10);
		bloccata.addAttrezzo(generico);
		assertEquals(bloccata, bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteDaBloccataANormaleConAttrezzoSbloccante() {
		bloccata.addAttrezzo(chiave);
		assertEquals(normale1, bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testMiniPartita() {
		bloccata.addAttrezzo(chiave);
		bloccata.addAttrezzo(new Attrezzo("attrezzo1", 5));
		normale1.addAttrezzo(new Attrezzo("attrezzo2", 5));
		assertEquals(normale1, bloccata.getStanzaAdiacente("nord"));
		bloccata.removeAttrezzo(chiave);
		assertEquals(bloccata, bloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteStanzaPazzaSenzaChiave() {
		assertEquals(pazza, pazza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteStanzaPazzaConChiave() {
		Attrezzo spranga = new Attrezzo("spranga", 5);
		pazza.addAttrezzo(spranga);
		assertNull(pazza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testStampa() {
		System.out.println(this.bloccata.getDescrizione());
		bloccata.addAttrezzo(chiave);
		System.out.println(this.bloccata.getDescrizione());
	}
	
	
	
}
