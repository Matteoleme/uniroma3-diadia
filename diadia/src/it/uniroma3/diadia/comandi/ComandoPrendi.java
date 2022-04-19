package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoPrendi implements Comando {
	static final private String NOME = "Prendi";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggioACapo(nomeAttrezzo + " preso");
				return; 				// preso
			} else
				io.mostraMessaggioACapo("Borsa piena non e' possibile mettere altri attrezzi");
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
