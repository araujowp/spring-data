package br.com.alura.spring.data.util;

import java.io.IOException;
import java.util.Scanner;

public class MyConsole {

	// testando limpeza de console
	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Teste a limpeza de tela");
		String texto = scanner.next();
		System.out.println("voce digitou " + texto);

		// Limpa a tela no windows, no linux e no MacOS
		if (System.getProperty("os.name").contains("Windows"))
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		else
			Runtime.getRuntime().exec("clear");

		System.out.println("outro teste ");
		System.out.print('\u000C');
//		System.out.println("outro teste ");
//		for (int i = 0; i <= 20; i++) {
//			System.out.println();
//		}
        System.out.print("Everything on the console will cleared");
        System.out.print("\033[H\033[2J");
        System.out.flush();
       
        String operatingSystem = System.getProperty("os.name"); //Check the current operating system
                
        if(operatingSystem.contains("Windows")){        
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        } else {
            ProcessBuilder pb = new ProcessBuilder("clear");
            Process startProcess = pb.inheritIO().start();

            startProcess.waitFor();
        } 
        
        
	}
}
