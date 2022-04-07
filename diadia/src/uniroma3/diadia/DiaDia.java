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

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita("Universita'");
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

		if (comandoDaEseguire.getNome() == null) {
			this.aiuto();
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			io.mostraMessaggioACapo("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggioACapo("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	private boolean prendi(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggioACapo(nomeAttrezzo + " preso");
				return true;
			} else
				io.mostraMessaggioACapo("Borsa piena non e' possibile mettere altri attrezzi");
		} else
			io.mostraMessaggioACapo(nomeAttrezzo + " non presente");
		return false;
	}

	private boolean posa(String nomeAttrezzo) {
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			if (this.partita.getStanzaCorrente().addAttrezzo(borsa.getAttrezzo(nomeAttrezzo))) {
				borsa.removeAttrezzo(nomeAttrezzo);
				io.mostraMessaggioACapo(nomeAttrezzo + " posato");
				return true;
			} else
				io.mostraMessaggioACapo("Stanza piena non e' possibile mettere altri attrezzi");
		} else
			io.mostraMessaggioACapo(nomeAttrezzo + " non presente");
		return false;
	}

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

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++) {
			io.mostraMessaggio(elencoComandi[i] + " ");
			if (i == 0)
				io.mostraMessaggio("+ direzione "); // EDIT 16/03 aggiunta di un aiuto per capire il comando
													// vai
		}
		io.mostraMessaggioACapo("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			io.mostraMessaggioACapo("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggioACapo("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		io.mostraMessaggioACapo(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggioACapo("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}
}