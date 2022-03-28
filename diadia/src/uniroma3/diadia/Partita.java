package uniroma3.diadia;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;
	
	private Labirinto mappa;
	private Stanza stanzaCorrente;
	private boolean finita;
	private int cfu;
	
	public Partita(String nome){
		mappa = new Labirinto(nome);
		this.finita = false;
		this.cfu = CFU_INIZIALI;
		this.stanzaCorrente = mappa.getStanzaIniziale();
	}
	
	// Aggiunto per i test
	public Partita(String nome, String test){
		mappa = new Labirinto(nome, test);
		this.finita = false;
		this.cfu = CFU_INIZIALI;
	}


	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
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
		return finita || vinta() || (cfu == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
}
