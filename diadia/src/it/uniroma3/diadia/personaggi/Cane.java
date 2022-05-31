package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio {

	private static final String PRESENTAZIONE_DEFAULT = "Bau Bau!";
	private static final String MESSAGGIO_CATTIVO = "AAAARRRGGGG BAUU! (-1 CFU)";
	private static final String MESSAGGIO_RINGRAZIAMENTO = "Bau Bau! <3";

	private static final String CIBO_PREFERITO = "osso";

	private Attrezzo AttrezzoRegalo = new Attrezzo("chiave", 2);

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	// overloading per presentazione default
	public Cane(String nome) {
		super(nome, PRESENTAZIONE_DEFAULT);
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
			if (this.AttrezzoRegalo != null) {
				partita.getStanzaCorrente().addAttrezzo(this.AttrezzoRegalo);
				this.AttrezzoRegalo = null;
			}
		} else
			this.morde(partita);
		return messaggio;
	}

	public void morde(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

}
