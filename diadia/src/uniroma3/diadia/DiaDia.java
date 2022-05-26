package uniroma3.diadia;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
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

	static final public String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;

	public DiaDia(IO io) {
		this.partita = new Partita("Università", io);
		this.io = io;
	}

	public void gioca() {
		String istruzione;
		
		LabirintoBuilder mappaDiaDia = new LabirintoBuilder();
		this.partita.setLabirinto(mappaDiaDia.creaStanze());

		io.mostraMessaggioACapo(MESSAGGIO_BENVENUTO);
		// EDIT 31/03 aggiunta di un metodo per creare una nuova borsa
		io.mostraMessaggioACapo("Crea una borsa prima! Quanti kg puoi portare?");
		int peso = this.io.leggiInt();
		this.creaBorsa(peso);
		io.mostraMessaggioACapo("Ti trovi in:" + partita.getStanzaCorrente().getDescrizione());
		do
			istruzione = this.io.leggiRiga();
		while (processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandiRiflessiva istr = new FabbricaDiComandiRiflessiva();

		AbstractComando comando = istr.costruisciComando(istruzione);

		comando.esegui(this.partita);			// questo esegui si comportera' in base al tipo DINAMICO del comando
		if (this.partita.vinta()) {
			io.mostraMessaggioACapo("Hai vinto!");
			return false;
		}
		return true;
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
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}