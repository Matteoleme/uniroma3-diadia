package uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

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
	private IO io;
	
	public Partita(String mappa, IO io){
		this.mappa = new Labirinto(mappa);
		this.finita = false;
		this.player = new Giocatore("Luca");
		this.stanzaCorrente = this.mappa.getStanzaIniziale();
		this.io = io;
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
		this.player.setNome(nomeGiocatore);
	}
	
	public Labirinto getLabirinto() {
		return this.mappa;
	}
	
	public void setLabirinto(Labirinto mappa) {
		this.mappa = mappa;
		this.stanzaCorrente = mappa.getStanzaIniziale();
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

	public IO getIO() {
		return io;
	}

}
