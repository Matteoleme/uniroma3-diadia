package it.uniroma3.diadia.comandi;

import uniroma3.diadia.IOConsole;
import uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {


	static final private String[] elencoComandi = { "vai", "aiuto", "fine",
			"prendi", "posa" };
	static final private String NOME = "Aiuto";
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		IOConsole io = partita.getIO();
		for (int i = 0; i < elencoComandi.length; i++) {
			io.mostraMessaggio(elencoComandi[i] + " ");
			if (i == 0)
				io.mostraMessaggio("+ direzione "); // EDIT 16/03 aggiunta di un aiuto per capire il comando
													// vai
		}
		io.mostraMessaggioACapo("");
	}

	@Override
	public void setParametro(String parametro) {
		//niente
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
