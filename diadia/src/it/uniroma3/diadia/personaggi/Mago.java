package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.Partita;

public class Mago extends AbstractPersonaggio {

	protected static final String PRESENTAZIONE_DEFAULT = "Attenzione a non fidarti di tutti quanti...";
	protected static final String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	protected static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	protected static final String MESSAGGIO_REGALO = "La tua gentilezza ti ha premiato...";
	protected static final String MESSAGGIO_REGALO_PESO1 = "Gia' troppo leggero!";

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	// Costruttore in overloading per presentazione default
	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE_DEFAULT);
		this.attrezzo = attrezzo;
	}

	// possiede un attrezzo che può donare
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	/**
	 * un mago riceve un regalo, gli dimezza il peso e lo lascia cadere nella stanza
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		int pesoAttrezzo = attrezzo.getPeso();
		String messaggio = MESSAGGIO_REGALO_PESO1;
		if (pesoAttrezzo != 1) { 				// per non avere un attrezzo con peso 0
			attrezzo.setPeso(pesoAttrezzo / 2);
			messaggio = MESSAGGIO_REGALO;
		}
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return messaggio;
	}

}
