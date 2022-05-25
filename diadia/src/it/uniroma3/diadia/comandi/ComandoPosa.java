package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.giocatore.Borsa;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando {

	static final private String NOME = "Posa";
	private String nomeAttrezzo;
	
	public ComandoPosa() {
		super(NOME, null);
	}
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		IO io = partita.getIO();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			partita.getStanzaCorrente().addAttrezzo(borsa.getAttrezzo(nomeAttrezzo));
			borsa.removeAttrezzo(nomeAttrezzo);
			io.mostraMessaggioACapo(nomeAttrezzo + " posato");
			return;

		} else if (nomeAttrezzo != null)
			io.mostraMessaggioACapo(nomeAttrezzo + " non presente");
		else
			io.mostraMessaggioACapo("Specificare attrezzo");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
