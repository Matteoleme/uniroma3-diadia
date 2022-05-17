package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.attrezzi = new HashMap<>();
		this.pesoMax = pesoMax;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false; // non posso aggiungere altri attrezzi pesano troppo
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo value : this.attrezzi.values())
			peso += value.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append(this.attrezzi.toString());
		} else
			s.append("Borsa vuota");
		return s.toString();
	}

	/**
	 * @return lista degli attrezzi nella borsa ordinati per peso e, a parità di
	 *         peso, per nome
	 */

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> elenco = new LinkedList<Attrezzo>(this.attrezzi.values());
		Collections.sort(elenco, new ComparaAttrezziPeso());
		return elenco;
	}

	/**
	 * @return l'insieme degli attrezzi nella borsa ordinati per nome
	 */

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> elenco = new TreeSet<Attrezzo>(new ComparaAttrezziNome());
		elenco.addAll(this.attrezzi.values());
		return elenco;
	}

	/**
	 * @return mappa che raggruppa per pesi
	 */

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {

		Map<Integer, Set<Attrezzo>> result = new HashMap<>();

		for (Attrezzo attrezzo : this.attrezzi.values()) {
			Set<Attrezzo> insieme = new HashSet<>();
			int peso = attrezzo.getPeso();
			if (!result.containsKey(Integer.valueOf(peso))) {
				insieme.add(attrezzo);
				result.put(Integer.valueOf(peso), insieme);
			} else {
				insieme = result.get(peso);
				insieme.add(attrezzo);
			}
		}
		return result;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> elenco = new TreeSet<Attrezzo>(new ComparaAttrezziPeso());
		elenco.addAll(this.attrezzi.values());
		return elenco;
	}
}
