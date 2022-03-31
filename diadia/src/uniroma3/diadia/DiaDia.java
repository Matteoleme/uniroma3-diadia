package uniroma3.diadia;

import java.util.Scanner;

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

	public DiaDia() {
		this.partita = new Partita("Universita'");
	}

	public void gioca() {
		String istruzione;
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);

		// EDIT 31/03 aggiunta di un metodo per creare una nuova borsa
		scannerDiLinee = new Scanner(System.in);
		System.out.println("Crea una borsa prima! Quanti kg puoi portare?");
		int peso = scannerDiLinee.nextInt();
		this.creaBorsa(peso);
		System.out.println("Ti trovi in:" + partita.getStanzaCorrente().getDescrizione());
		do
			istruzione = scannerDiLinee.nextLine();
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
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
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
				System.out.println(nomeAttrezzo + " preso");
				return true;
			} else
				System.out.println("Borsa piena non e' possibile mettere altri attrezzi");
		} else
			System.out.println(nomeAttrezzo + " non presente");
		return false;
	}

	private boolean posa(String nomeAttrezzo) {
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			if (this.partita.getStanzaCorrente().addAttrezzo(borsa.getAttrezzo(nomeAttrezzo))) {
				borsa.removeAttrezzo(nomeAttrezzo);
				System.out.println(nomeAttrezzo + " posato");
				return true;
			} else
				System.out.println("Stanza piena non e' possibile mettere altri attrezzi");
		} else
			System.out.println(nomeAttrezzo + " non presente");
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
			System.out.println("Il giocatore ha gia' una borsa di " + peso + "kg");
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++) {
			System.out.print(elencoComandi[i] + " ");
			if (i == 0)
				System.out.print("+ direzione "); // EDIT 16/03 aggiunta di un aiuto per capire il comando
													// vai
		}
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}