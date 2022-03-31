package uniroma3.diadia;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Labirinto mappa;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore player;
	
	public Partita(String mappa){
		this.mappa = new Labirinto(mappa);
		this.finita = false;
		this.stanzaCorrente = this.mappa.getStanzaIniziale();
		this.player = new Giocatore("Luca");
	}
	
	// Aggiunto per i test
	public Partita(String mappa, String test){
		this.mappa = new Labirinto(mappa, test);
		this.finita = false;
		this.player = new Giocatore("Luca");
	}


	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public Giocatore getGiocatore() {
		return this.player;
	}
	
	public void setGiocatore(String nomeGiocatore) {
		this.player.setNome(nomeGiocatore);;
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	
	/* EDIT 26/03 Aggiungo un controllo per vedere se e' stata settata 
	 * una stanza vincente, altrimenti non eseguo il controllo
	 */
	public boolean vinta() {
		boolean result = false;
		if(mappa.getStanzaVincente()!=null)
			result = this.getStanzaCorrente()== mappa.getStanzaVincente();
		return result;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (player.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

}
