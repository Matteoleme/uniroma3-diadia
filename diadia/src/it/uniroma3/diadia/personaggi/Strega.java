package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.Partita;

public class Strega extends AbstractPersonaggio {

	private static final String PRESENTAZIONE_DEFAULT = "Ciao, sono la strega Malefica";
	private static final String MESSAGGIO_CATTIVO = "UHAHAHAHA, questo me lo prendo io!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	// overloading per presentazione default
	public Strega(String nome) {
		super(nome, PRESENTAZIONE_DEFAULT);
	}

	/**
	 * se non l’abbiamo ancora salutata, ci «trasferisce» nella stanza adiacente che
	 * contiene meno attrezzi, altrimenti in quella che contiene più attrezzi
	 */
	@Override
	public String agisci(Partita partita) {
		Stanza destinazione;
		String result = null;
		if(this.haSalutato()) {
			// sposto giocatore in stanza con più attrezzi
			destinazione = partita.getStanzaCorrente().piuAttrezzi();
			partita.setStanzaCorrente(destinazione);
			result = "Sei stato cortese con me, ti spedisco in " + destinazione.getNome();
		}
		else {
			destinazione = partita.getStanzaCorrente().menoAttrezzi();
			partita.setStanzaCorrente(destinazione);
			result = "Che maleducato! vattente in " + destinazione.getNome();
		}
		return result;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_CATTIVO;
	}

}
