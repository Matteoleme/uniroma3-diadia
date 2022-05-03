package uniroma3.diadia;

public class Fixture {
	
	public static IOSimulator creaSimulazionePartitaEGioca(String... righeDaLeggere) {
		IOSimulator io = new IOSimulator(righeDaLeggere);
		new DiaDia(io).gioca();
		return io;
	}
}
