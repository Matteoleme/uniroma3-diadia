package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparaAttrezziPeso implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int result = a1.getPeso()-a2.getPeso();
		if(result == 0) 
			result = a1.getNome().compareTo(a2.getNome());
		return result;
	}
	
}
