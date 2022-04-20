package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * La «stanza bloccata»: una delle direzioni della stanza non può essere seguita
 * a meno che nella stanza non sia presente un oggetto con un nome particolare
 * 
 * @author biond
 *
 */
public class StanzaBloccata extends Stanza {

	final static private String ATTREZZO = "chiave";
	String direzioneBloccata;
	String sbloccaStanza;

	public StanzaBloccata(String nome, String direzione) {
		this(nome, direzione, ATTREZZO);
	}

	public StanzaBloccata(String nome, String direzione, String attrezzo) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.sbloccaStanza = attrezzo;
	}

	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (dir == direzioneBloccata && !this.hasAttrezzo(sbloccaStanza)) {
			return this;
		}
		return super.getStanzaAdiacente(dir);
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.getNome());
		risultato.append("\nUscite: ");
		for (String direzione : this.getDirezioni())
			if (direzione != null)
				if (direzione == this.direzioneBloccata)
					risultato.append(" " + direzione + " <= (Bloccata!)\n");
				else
					risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		if (this.getNumeroAttrezzi() != 0) {
			Attrezzo[] attrezzi = this.getAttrezzi();
			for (int i = 0; i < this.getNumeroAttrezzi(); i++) {
				if (attrezzi[i].getNome() == this.sbloccaStanza)
					risultato.append(attrezzi[i].toString() +
							" Può essere usato per sbloccare una stanza\n");
				else
					risultato.append(attrezzi[i].toString() + " ");
			}
		} else
			risultato.append("nessuno"); // se non ci sono attrezzi

		return risultato.toString();
	}

}
