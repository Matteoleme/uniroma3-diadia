package it.uniroma3.diadia.ambienti;

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
		if (dir == direzioneBloccata && !super.hasAttrezzo(sbloccaStanza))
			return this;
		return super.getStanzaAdiacente(dir);
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}

	@Override
	public String toString() {
		String stampa = "\nDirezione bloccata: " + this.direzioneBloccata + 
				"\nServirebbe una " + this.sbloccaStanza + "...";
		return super.toString() + stampa;
	}

	// da testare
	public boolean hasSbloccaStanza() {
		return super.getAttrezzi().containsKey(sbloccaStanza);
	}

}
