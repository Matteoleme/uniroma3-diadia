package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {

	protected static final String PRESENTAZIONE_DEFAULT = "Bau Bau!";
	protected static final String MESSAGGIO_CATTIVO = "AAAARRRGGGG BAUU! (-1 CFU)";
	protected static final String MESSAGGIO_RINGRAZIAMENTO = "Bau Bau! <3";

	protected static final String CIBO_PREFERITO = "osso";

	private Attrezzo attrezzoRegalo;

	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzoRegalo = attrezzo;
	}

	// overloading per presentazione default
	public Cane(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE_DEFAULT);
		this.attrezzoRegalo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		this.morde(partita);
		return "AAARRRRGGG! (-1 cfu)";
	}

	/**
	 * un cane riceve un regalo: se questo è il suo cibo preferito lo accetta, e
	 * butta a terra un attrezzo; altrimenti morde e toglie un CFU
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String messaggio = MESSAGGIO_CATTIVO;
		if (attrezzo.getNome().equals(CIBO_PREFERITO)) {
			messaggio = MESSAGGIO_RINGRAZIAMENTO;
			if (this.attrezzoRegalo != null) {
				partita.getStanzaCorrente().addAttrezzo(this.attrezzoRegalo);
				this.attrezzoRegalo = null;
			}
		} else
			this.morde(partita);
		return messaggio;
	}

	public void morde(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

}
