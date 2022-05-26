package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import uniroma3.diadia.IOConsole;
import uniroma3.diadia.Partita;

public class ComandoVaiTest {
	
	private Partita partita;
	private ComandoVai vai;
	private Stanza nord;
	private Stanza sud;
	private Stanza isolata;
	private IOConsole io;

	@Before
	public void setUp() throws Exception {
		io = new IOConsole();
		partita = new Partita("Test", io);
		vai = new ComandoVai();
		isolata = new Stanza("isolata");
		nord = new Stanza("nord");
		sud = new Stanza("sud");
		
		sud.impostaStanzaAdiacente("nord", nord);
		nord.impostaStanzaAdiacente("sud", sud);
	}

	@Test
	public void testSenzaParametro() {
		this.partita.setStanzaCorrente(sud);
		vai.esegui(partita);
		assertEquals(sud, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testStanzaIsolata() {
		this.partita.setStanzaCorrente(isolata);
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(isolata, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiVersoDirezione() {
		this.partita.setStanzaCorrente(sud);
		vai.setParametro("nord");
		vai.esegui(partita);
		assertEquals(nord, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiVersoDirezioneSbagliata() {
		this.partita.setStanzaCorrente(sud);
		vai.setParametro("est");
		vai.esegui(partita);
		assertEquals(sud, this.partita.getStanzaCorrente());
	}
	
	
}
