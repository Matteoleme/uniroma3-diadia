package uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] righeDaLeggere;
	private int indiceRigheDaLeggere;
	private String[] messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;

	public IOSimulator(String... righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheDaLeggere = 0;
		this.messaggiProdotti = new String[100];
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiMostrati = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {			//non a capo
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	@Override
	public void mostraMessaggioACapo(String messaggio) {
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere[this.indiceRigheDaLeggere];
		this.indiceRigheDaLeggere++;
		return rigaLetta;
	}

	@Override
	public int leggiInt() {
		String rigaLetta = this.righeDaLeggere[this.indiceRigheDaLeggere];
		this.indiceRigheDaLeggere++;
		return Integer.parseInt(rigaLetta);
	}
	
	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiMostrati];
		this.indiceMessaggiMostrati++;
		return next;
	}
	
	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}
	
	public String[] getMexProd() {
		return this.messaggiProdotti;
	}
}