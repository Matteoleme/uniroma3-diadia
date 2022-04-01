package it.uniroma3.diadia.ambienti;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale; // Stanza dove spawno
	private String nome; // Nome mappa
	private LabirintoFixture labfix;

	public Labirinto(String nome) {
		this.nome = nome;
		this.labfix = new LabirintoFixture(this);
	}

	public String getNome() {
		return nome;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	public LabirintoFixture getLabirintoFixture() {
		return this.labfix;
	}

}
