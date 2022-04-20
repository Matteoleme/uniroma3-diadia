package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza fatta solo per esercitarci con i modificatori
 * di visibiltà protected
 * 
 */

public class StanzaProtected {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	protected Attrezzo[] attrezzi;
	protected int numeroAttrezzi;
	private StanzaProtected[] stanzeAdiacenti;
	private int numeroStanzeAdiacenti;
	private String[] direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new StanzaProtected[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */

	// prova a risolvere il fatto che quando metto una stanza in una direzione
	// l'adiacente deve essere nella posizione opposta

	public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
		boolean aggiornato = false;
		// EDIT 25/03 risolto bug che permetteva di impostare una stanza adiacente a se
		// stessa
		if (this.nome != stanza.nome) {
			// EDIT 25/03 risolto bug che impostava stessa stanza in due direzioni diverse
			for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
				if (this.stanzeAdiacenti[i].equals(stanza)) // controllo se gia' presente
					aggiornato = true;
			// ciclo che scorre le direzioni della stanza corrente
			// e controlla se la direzione presa in input era gia' stata messa
			if (!aggiornato)
				for (int i = 0; i < this.direzioni.length; i++)
					if (direzione.equals(this.direzioni[i])) {
						this.stanzeAdiacenti[i] = stanza;
						aggiornato = true; // se gia' presente sovrascrivo
					}
			if (!aggiornato)
				if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
					this.direzioni[numeroStanzeAdiacenti] = direzione;
					this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
					this.numeroStanzeAdiacenti++;
				}
		} else
			return; // caso in cui c'e' una stanza adiacente a se stessa
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public StanzaProtected getStanzaAdiacente(String direzione) {
		StanzaProtected stanza = null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		/*
		 * EDIT 16/03 Cambio for per evitare di andare in NULL In questa soluzione mi
		 * fermo a numeroAttrezzi
		 */
		if (numeroAttrezzi != 0) // Aggiunto if per stampare che non ci sono attrezzi in una stanza senza
									// attrezzi
			for (int i = 0; i < numeroAttrezzi; i++)
				risultato.append(attrezzi[i].toString() + " ");
		else
			risultato.append("nessuno"); // se non ci sono attrezzi

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		// EDIT 26/03 Aggiungo controllo preventivo: se la stanza non ha nemmeno un
		// attrezzo
		// e' inutile che cerco
		if (this.numeroAttrezzi != 0) {
			// EDIT 30/03 cambio il for per un errore che mi da nei test
			// di removeAttrezzo
			for (int i = 0; i < numeroAttrezzi; i++) {
				if (attrezzi[i].getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */

	// EDIT 31/03 Cambio ciclo for perche da un errore
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzi[i];
		}
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */

	// EDIT 30/03 aggiunta metodo removeAttrezzo
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if (!hasAttrezzo(attrezzo.getNome()) || this.numeroAttrezzi == 0)
			return false;
		// se mi trovo qui vuol dire che l'attrezzo c'e'
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (attrezzo.equals(this.attrezzi[i])) {
				// ho trovato quello da rimuovere
				this.numeroAttrezzi--;
				if (numeroAttrezzi != 0 || numeroAttrezzi == NUMERO_MASSIMO_ATTREZZI)
					this.attrezzi[i] = this.attrezzi[numeroAttrezzi];
				return true;
			}
		}
		return false;
	}

	/**
	 * Controlla quante direzioni ha una stanza.
	 * 
	 * @return un array di Stringhe delle direzioni della stanza
	 */

	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}

}