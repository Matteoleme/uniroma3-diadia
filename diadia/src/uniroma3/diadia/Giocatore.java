package uniroma3.diadia;

/**
 * Classe Giocatore - un giocatore e' il peronaggio che girera' nel labirinto
 * e che dovra' vincere la partita
 * Un giocatore ha un nome, una borsa che contiene degli attrezzi e un numero di 
 * cfu che una volta finiti finiranno il gioco
 * 
 * 
 * @author Matteoleme
 * @see Borsa
 * @version base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private String nome;
	private Borsa borsa;
	private int cfu;
	
	public Giocatore(String nome) {
		this.setNome(nome);
		this.borsa = null;
		this.cfu = CFU_INIZIALI;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(int peso) {
		this.borsa = new Borsa(peso);
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}