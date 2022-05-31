package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	private String nome;
	private AbstractPersonaggio personaggio;


	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
		this.nome = nome;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		// EDIT 25/03 risolto bug che permetteva di impostare una stanza adiacente a se
		// stessa
		if (this.nome != stanza.nome)
			if (!stanzeAdiacenti.containsValue(stanza))	
				// se nella stanze adiacenti non e' gia' contenuta la stanza che sto aggiungendo
				this.getStanzeAdiacenti().put(direzione, stanza);
			else
				return;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.getStanzeAdiacenti().get(direzione);
	}

	public Map<String, Stanza> getStanzeAdiacenti() {
		return stanzeAdiacenti;
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
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public Attrezzo addAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.put(attrezzo.getNome(), attrezzo);
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
		for (String key : this.stanzeAdiacenti.keySet())
			risultato.append(key + " ");
		risultato.append("\nAttrezzi nella stanza: ");
		if (!this.attrezzi.isEmpty()) // Aggiunto if per stampare che non ci sono attrezzi in una stanza senza
										// attrezzi
			risultato.append(attrezzi.toString() + "\n");
		else
			risultato.append("nessun attrezzo presente"); // se non ci sono attrezzi
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */

	// EDIT 30/03 aggiunta metodo removeAttrezzo
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}

	/**
	 * Controlla quante direzioni ha una stanza.
	 * 
	 * @return un array di Stringhe delle direzioni della stanza
	 */

	public Map<String, Stanza> getDirezioni() {
		return this.getStanzeAdiacenti();
		// metodo sospetto che non ho capito perché ho implementato così
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/*implementazione metodi personaggi*/
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public Stanza piuAttrezzi() {
		Set<Stanza> stanze = new HashSet<>(this.getStanzeAdiacenti().values());
		Stanza result = null;
		for (Stanza questa : stanze) {
			if (result == null)
				result = questa;
			else if (result.getAttrezzi().size() < questa.getAttrezzi().size())
				result = questa;
		}
		return result;
	}
	
	public Stanza menoAttrezzi() {
		Set<Stanza> stanze = new HashSet<>(this.getStanzeAdiacenti().values());
		Stanza result = null;
		for (Stanza questa : stanze) {
			if (result == null)
				result = questa;
			else if (result.getAttrezzi().size() > questa.getAttrezzi().size())
				result = questa;
		}
		return result;
	}

}