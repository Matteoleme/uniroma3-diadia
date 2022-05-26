package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Map<String, Stanza> stanzeLabirinto;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanzeLabirinto = labirinto.getStanze();
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza s = this.stanzeLabirinto.get(stanzaIniziale);
		if (s != null)
			this.labirinto.setStanzaIniziale(s);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza s = this.stanzeLabirinto.get(stanzaVincente);
		if (s != null)
			this.labirinto.setStanzaVincente(s);
		return this;
	}

	public LabirintoBuilder addStanza(String nome) {
		Stanza questa = new Stanza(nome);
		this.stanzeLabirinto.put(nome, questa);
		return this;
	}

	public LabirintoBuilder impostaAdiacente(String prima, String seconda, String direzione) {
		Stanza s1 = this.stanzeLabirinto.get(prima);
		Stanza s2 = this.stanzeLabirinto.get(seconda);
		if (s1 != null && s2 != null)
			s1.impostaStanzaAdiacente(direzione, s2);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, String nomeStanza, int peso) {
		Attrezzo attrezzoDaAggiungere = new Attrezzo(nomeAttrezzo, peso);
		Stanza s = this.stanzeLabirinto.get(nomeStanza);
		if (s != null)
			s.addAttrezzo(attrezzoDaAggiungere);
		return this;
	}
	
	public LabirintoBuilder removeAttrezzo(String nomeAttrezzo, String nomeStanza) {
		Stanza s = this.stanzeLabirinto.get(nomeStanza);
		if (s != null) 
			if(s.hasAttrezzo(nomeAttrezzo))
					s.removeAttrezzo(s.getAttrezzo(nomeAttrezzo));
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaMagica(String stanza) {
		StanzaMagica nuova = new StanzaMagica(stanza);
		this.stanzeLabirinto.put(stanza, nuova);
		return this;
	}

	// overload
	public LabirintoBuilder addStanzaMagica(String stanza, int sogliaMagica) {
		Stanza nuova = new StanzaMagica(stanza, sogliaMagica);
		this.stanzeLabirinto.put(stanza, nuova);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String stanza, String nomeAttrezzoLucente) {
		Stanza nuova = new StanzaBuia(stanza, nomeAttrezzoLucente);
		this.stanzeLabirinto.put(stanza, nuova);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String stanza, String direzioneBloccata, String sbloccaStanza) {
		Stanza nuova = new StanzaBloccata(stanza, direzioneBloccata, sbloccaStanza);
		this.stanzeLabirinto.put(stanza, nuova);
		return this;
	}

	public Stanza getStanza(String nomeStanza) {
		return this.stanzeLabirinto.get(nomeStanza);
	}
	
	public Map<String, Stanza> getStanze() {
		return this.stanzeLabirinto;
	}
	
	/**
	 * crea le stanze per la mappa iniziale DiaDia
	 */
	public Labirinto creaStanze() {
		
		/* crea stanze e le aggiungo nel labirinto */		
		this.addStanza("Aula N10");
		this.addStanza("Aula N11");
		this.addStanza("Laboratorio");
		this.addStanza("Atrio");
		this.addStanza("Biblioteca");
		this.addStanzaIniziale("Atrio");
		this.addStanzaVincente("Biblioteca");
		
		/* imposto adiacenze */
		this.impostaAdiacente("Atrio", "Biblioteca", "nord");
		this.impostaAdiacente("Atrio", "Aula N11", "est");
		this.impostaAdiacente("Atrio", "Aula N10", "sud");
		this.impostaAdiacente("Atrio", "Laboratorio", "ovest");
		
		this.impostaAdiacente("Aula N11", "Laboratorio", "est");
		this.impostaAdiacente("Aula N11", "Atrio", "ovest");
		
		this.impostaAdiacente("Aula N10", "Atrio", "nord");
		this.impostaAdiacente("Aula N10", "Aula N11", "est");
		this.impostaAdiacente("Aula N10", "Laboratorio", "ovest");
		
		this.impostaAdiacente("Laboratorio", "Atrio", "est");
		this.impostaAdiacente("Laboratorio", "Aula N11", "ovest");
		
		this.impostaAdiacente("Biblioteca", "Atrio", "sud");
		
		/* aggiungo attrezzi nelle stanze */
		this.addAttrezzo("lanterna", "Aula N10", 3);
		this.addAttrezzo("osso", "Atrio", 3);
		
		this.labirinto.setNome("Università");
		
		return this.labirinto;
	}

}