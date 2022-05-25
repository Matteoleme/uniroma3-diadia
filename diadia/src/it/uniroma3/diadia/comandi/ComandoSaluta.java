package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	static final public String NOME = "saluta";
	static final public String PERSONAGGIO_ASSENTE = "In questa stanza non c'è nessuno da salutare…";
	
	public ComandoSaluta() {
		super(NOME, null);
	}

	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			io.mostraMessaggio(personaggio.saluta());
		} else
			io.mostraMessaggioACapo(PERSONAGGIO_ASSENTE);
	}

}
