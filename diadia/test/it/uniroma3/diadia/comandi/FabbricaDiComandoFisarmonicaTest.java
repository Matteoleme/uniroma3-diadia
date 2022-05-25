package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandoFisarmonicaTest {
	
	private String tuttoIlComando;
	private FabbricaDiComandi fab;
	
	@Before
	public void setUp() {
		fab = new FabbricaDiComandoFisarmonica();
	}
	
	@Test
	public void testComandoGenericoSenzaParametro() {
		tuttoIlComando = "vai";
		AbstractComando nuovo = fab.costruisciComando(tuttoIlComando);
		assertEquals("Vai", nuovo.getNome());
	}
	
	@Test
	public void testComandoGenericoConParametro() {
		tuttoIlComando = "vai nord";
		assertEquals("Vai", fab.costruisciComando(tuttoIlComando).getNome());
	}

	@Test
	public void testParametroDiComando() {
		tuttoIlComando = "vai nord";
		assertEquals("nord", fab.costruisciComando(tuttoIlComando).getParametro());
	}
	
	@Test
	public void testComandoNonValido() {
		tuttoIlComando = "voi";
		assertEquals("NonValido", fab.costruisciComando(tuttoIlComando).getNome());
	}
	
	@Test
	public void testComandoCheNonHaParametroConParametro() {
		tuttoIlComando = "guarda qui";
		assertEquals("guarda", fab.costruisciComando(tuttoIlComando).getNome());
	}
	
	@Test
	public void testComandoCheHaParametroSenzaParametro() {
		tuttoIlComando = "vai";
		assertNull(fab.costruisciComando(tuttoIlComando).getParametro());
	}
	
	
	
}
