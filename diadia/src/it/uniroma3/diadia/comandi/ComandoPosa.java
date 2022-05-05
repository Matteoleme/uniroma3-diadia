package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.giocatore.Borsa;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoPosa implements Comando {

	static final private String NOME = "Posa";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		IO io = partita.getIO();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			partita.getStanzaCorrente().addAttrezzo(borsa.getAttrezzo(nomeAttrezzo));
			borsa.removeAttrezzo(nomeAttrezzo);
			io.mostraMessaggioACapo(nomeAttrezzo + " posato");
			return;

		} else
			io.mostraMessaggioACapo(nomeAttrezzo + " non presente");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
