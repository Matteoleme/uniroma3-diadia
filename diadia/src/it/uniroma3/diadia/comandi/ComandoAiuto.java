package it.uniroma3.diadia.comandi;

import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	static final private String[] elencoComandi = { "vai", "aiuto", "fine",
			"prendi", "posa", "saluta", "interagisci"};
	static final public String NOME = "aiuto";
	
	public ComandoAiuto() {
		super(NOME, null);
	}
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		for (int i = 0; i < elencoComandi.length; i++) {
			io.mostraMessaggio(elencoComandi[i] + " ");
			if (i == 0)
				io.mostraMessaggio("+ direzione "); // EDIT 16/03 aggiunta di un aiuto per capire il comando
													// vai
		}
		io.mostraMessaggioACapo("");
	}

}
