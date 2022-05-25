package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import javax.swing.AbstractAction;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	public AbstractComando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		String nomeComando = null; // es. 'vai'
		String parametro = null; // es. 'sud'
		AbstractComando comando = null;

		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();// seconda parola: eventuale parametro
		try {
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
			nomeClasse.append(nomeComando.substring(1));
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
			comando = (AbstractComando) Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
		}
		catch (Exception e) {
			comando = new ComandoNonValido();
		}
			
		return comando;
	}
}
