package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoPrendi extends AbstractComando {
	

	static final private String NOME = "Prendi";
	private String nomeAttrezzo;
	
	public ComandoPrendi() {
		super(NOME, null);
	}

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
			if(nomeAttrezzo!=null)
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
