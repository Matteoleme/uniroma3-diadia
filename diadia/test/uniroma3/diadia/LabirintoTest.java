package uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	
	
	private Labirinto vuoto;
	private Labirinto Labirinto1;
	private Labirinto Labirinto2;
	
	private Stanza Stanza1;
	private Stanza Stanza2;
	private Stanza Stanza3;
	
	@Before
	public void setUp(){
		vuoto = new Labirinto("Bunker", "Test");
		Labirinto1 = new Labirinto("Labirinto1", "Test");
		Labirinto2 = new Labirinto("Labirinto2", "Test");
		
		Stanza1 = new Stanza("Stanza1");
		Stanza2 = new Stanza("Stanza2"); 
		Stanza3 = new Stanza("Stanza3"); 
		
		Labirinto1.setStanzaIniziale(Stanza1);
		Labirinto1.setStanzaVincente(Stanza2);
		
		Labirinto2.setStanzaIniziale(Stanza3);
		Labirinto2.setStanzaVincente(Stanza3);
	}

	@Test
	public void testSetStanzaVincenteVuota() {
		assertNull(vuoto.getStanzaVincente());
	}
	
	@Test
	public void testSetStanzaVincenteDiversa() {
		assertEquals(Stanza2, Labirinto1.getStanzaVincente());
	}
	
	@Test
	public void testSetStanzaVincenteUguale() {
		assertEquals(Stanza3, Labirinto2.getStanzaVincente());
	}

	@Test
	public void testSetStanzaInizialeVuota() {
		assertNull(vuoto.getStanzaIniziale());
	}
	
	@Test
	public void testSetStanzaInizialeDiversa() {
		assertEquals(Stanza1, Labirinto1.getStanzaIniziale());
	}
	
	@Test
	public void testSetStanzaInizialeUguale() {
		assertEquals(Stanza3, Labirinto2.getStanzaIniziale());
	}
	

}
