package it.uniroma3.diadia.ambienti;

/**
 * La «stanza buia»: se nella stanza non è presente un attrezzo con un nome
 * particolare il metodo getDescrizione() di una stanza
 * buia ritorna la stringa "qui c'è un buio pesto"
 */

public class StanzaBuia extends Stanza {
	final static private String ATTREZZO = "lanterna";  
	private String nomeAttrezzoLucente;

	public StanzaBuia(String nome) {
		this(nome, ATTREZZO);
	}

	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.nomeAttrezzoLucente = attrezzo;
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(nomeAttrezzoLucente)) {
			return super.getDescrizione();
		}
		return "Qui c'è buio pesto\n";
	}

	public String getNomeAttrezzoLucente() {
		return "Serve una " + nomeAttrezzoLucente;
	}

}
