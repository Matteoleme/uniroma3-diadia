package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	private static final String MESSAGGIO = "Qui c'è buio pesto\n";
	private StanzaBuia buia;
	private Attrezzo lanterna;
	
	@Before
	public void setUp() throws Exception {
		buia = new StanzaBuia("buia");
		lanterna = new Attrezzo("lanterna", 5);
	}

	@Test
	public void testGetDescrizioneStanzaBuiaSenzaLuce() {
		assertEquals(MESSAGGIO, buia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneStanzaBuiaConAttrezzoACaso() {
		Attrezzo generico = new Attrezzo("generico", 5);
		buia.addAttrezzo(generico);
		assertEquals(MESSAGGIO, buia.getDescrizione());
	}

	@Test
	public void testGetDescrizioneStanzaBuiaConLuce() {
		buia.addAttrezzo(lanterna);
		assertNotEquals(MESSAGGIO, buia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneStanzaBuiaConLuceMaNonLaterna() {
		buia = new StanzaBuia("buia", "torcia");
		Attrezzo torcia = new Attrezzo("torcia", 3);
		buia.addAttrezzo(torcia);
		assertNotEquals(MESSAGGIO, buia.getDescrizione());
	}
	
	@Test
	public void testGetStanzaAdicenteBuiaSenzaLuce() {
		Stanza generica = new Stanza("Generica");
		buia.impostaStanzaAdiacente("nord", generica);
		assertEquals(MESSAGGIO, buia.getDescrizione());
		assertEquals(generica, buia.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testStampa() {
		System.out.print(buia.getDescrizione());
		buia.addAttrezzo(lanterna);
		System.out.print(buia.getDescrizione());
	}
}
