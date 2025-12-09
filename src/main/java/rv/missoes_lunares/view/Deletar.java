package rv.missoes_lunares.view;

import java.util.Scanner;

import rv.missoes_lunares.service.*;

public abstract class Deletar extends Util {
	public static void deletarObjeto(String obj) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\033[H\033[2J");
		System.out.print("Digite o uuid: ");
		String uuid = scanner.nextLine();
		
		switch (obj) {
			case "Missão":
				Missao.Deletar(uuid);
				break;
				
			case "Nave":
				Nave.Deletar(uuid);
				break;
				
			case "Astronauta":
				Astronauta.Deletar(uuid);
				break;
		}
		Wait(5000);
	}
}
