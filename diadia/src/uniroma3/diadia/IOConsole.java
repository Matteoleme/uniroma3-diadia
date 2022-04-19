package uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO{

	public void mostraMessaggioACapo(String msg) {
		System.out.println(msg);
	}

	public void mostraMessaggio(String msg) {
		System.out.print(msg);
	}

	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		// scannerDiLinee.close();
		return riga;
	}
	
	public int leggiInt() {
		Scanner scannerDiLinee = new Scanner(System.in);
		int intero = scannerDiLinee.nextInt();
		// scannerDiLinee.close();
		return intero;
	}
}
