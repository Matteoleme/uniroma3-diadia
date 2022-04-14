package uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita("Universita'", io);
		this.io = io;
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggioACapo(MESSAGGIO_BENVENUTO);
		// EDIT 31/03 aggiunta di un metodo per creare una nuova borsa
		io.mostraMessaggioACapo("Crea una borsa prima! Quanti kg puoi portare?");
		int peso = this.io.leggiInt();
		this.creaBorsa(peso);
		io.mostraMessaggioACapo("Ti trovi in:" + partita.getStanzaCorrente().getDescrizione());
		do
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		/*
		 * EDIT 17/03 Risolvo il caso in cui l'utente non scriva nulla e digiti
		 * solamente invio
		 */

		/*
		 * if (comandoDaEseguire.getNome() == null) { this.aiuto(); return false; } if
		 * (comandoDaEseguire.getNome().equals("fine")) { this.fine(); return true; }
		 * else if (comandoDaEseguire.getNome().equals("vai"))
		 * this.vai(comandoDaEseguire.getParametro()); else if
		 * (comandoDaEseguire.getNome().equals("aiuto")) this.aiuto(); else if
		 * (comandoDaEseguire.getNome().equals("prendi"))
		 * this.prendi(comandoDaEseguire.getParametro()); else if
		 * (comandoDaEseguire.getNome().equals("posa"))
		 * this.posa(comandoDaEseguire.getParametro()); else
		 * io.mostraMessaggioACapo("Comando sconosciuto"); if (this.partita.vinta()) {
		 * io.mostraMessaggioACapo("Hai vinto!"); return true; } else return false;
		 */
		return false;			// da eliminare
	}

	// implementazioni dei comandi dell'utente:

	// questo metodo diventa necessario per come ho strutturato il codice
	// e consente inoltre di far impostare all'utente di scegliere il peso della
	// borsa

	private void creaBorsa(int peso) {
		Giocatore player = this.partita.getGiocatore();
		if (player.getBorsa() == null) {
			Borsa borsa = new Borsa(peso);
			player.setBorsa(borsa);
		} else
			io.mostraMessaggioACapo("Il giocatore ha gia' una borsa di " + peso + "kg");
	}

	public static void main(String[] argc) {
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}
}