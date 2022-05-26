package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale; // Stanza dove spawno
	private String nome; // Nome mappa
	private Map<String, Stanza> stanzeLabirinto;

	public Labirinto(String nome) {
		this.nome = nome;
		this.stanzeLabirinto = new HashMap<>();
	}

	public Labirinto() {
		this.stanzeLabirinto = new HashMap<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Map<String, Stanza> getStanze() {
		return this.stanzeLabirinto;
	}

//	public Stanza piuAttrezzi() {
//		Set<Stanza> stanze = new HashSet<>(this.stanzeLabirinto.values());
//		Stanza result = null;
//		for (Stanza questa : stanze) {
//			if (result == null)
//				result = questa;
//			else if (result.getAttrezzi().size() < questa.getAttrezzi().size())
//				result = questa;
//		}
//		return result;
//	}
//	
//	public Stanza menoAttrezzi() {
//		Set<Stanza> stanze = new HashSet<>(this.stanzeLabirinto.values());
//		Stanza result = null;
//		for (Stanza questa : stanze) {
//			if (result == null)
//				result = questa;
//			else if (result.getAttrezzi().size() > questa.getAttrezzi().size())
//				result = questa;
//		}
//		return result;
//	}

}