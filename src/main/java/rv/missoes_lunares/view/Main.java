package rv.missoes_lunares.view;

import java.util.Scanner;

public class Main extends Criar{
	public static void main(String[] args) {
		System.out.print("\033[H\033[2J");
		System.out.println("========================================================");
		System.out.println("|             Bem vindo ao sistema Lunar               |");
		System.out.println("========================================================");
		Wait(1000);
		Menu();
	}
	
	public static void Menu() {
		System.out.print("\033[H\033[2J");
		System.out.println("========================================================");
		System.out.println("|                         Menu                         |");
		System.out.println("========================================================");
		System.out.println("1 - Missão");
		System.out.println("2 - Nave");
		System.out.println("3 - Astronauta");
		System.out.println("4 - Sair");
		System.out.println("========================================================");
		System.out.print("Digite o número da opção: ");
		
		// Leitor de input
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		
		switch(input) {
			case "1":
				subMenu("Missão");
				break;
				
			case "2":
				subMenu("Nave");
				break;
				
			case "3":
				subMenu("Astronauta");
				break;
				
			case "4":
				Sair();
				break;
				
			default:
				Invalido();
				break;
		}
		
		Menu();
	}
	
	public static void subMenu(String obj) {
		System.out.print("\033[H\033[2J");
		System.out.println("========================================================");
		System.out.println("|                     Sub Menu                         |");
		System.out.println("========================================================");
		System.out.println("0 - Voltar");
		System.out.println("1 - Listar");
		System.out.println("2 - Criar");
		System.out.println("3 - Deletar");
		
		if(obj == "Missão") {
			System.out.println("4 - Vincular Nave");
			System.out.println("5 - Vincular Astronauta");
		}
		
		System.out.println("========================================================");
		System.out.print("Digite o número da opção: ");
		
		// Leitor de input
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		System.out.println("");
		
		switch (input) {
			case "0":
				Menu();
				break;
			
			case "1":
				listarObjetos(obj);
				break;
				
				
			case "2":
				criarObjeto(obj);
				break;
				
			case "3":
				deletarObjeto(obj);
				break;
				
			case "4":
				if(obj == "Missão") {
					Vincular("Nave");
				} else {
					Invalido();
				}
				break;
				
			case "5":
				if(obj == "Missão") {
					Vincular("Astronauta");
				} else {
					Invalido();
				}
				break;
				
			default:
				Invalido();
				break;
		}
		
		subMenu(obj);
	}
	
	// Função que encerra o programa
	protected static void Sair() {
		for (int i = 3; i > -1; i--) {
			System.out.print("\033[H\033[2J");
			System.out.println("========================================================");
			System.out.println("Finalizando o programa em " + i);
			System.out.println("========================================================");
			Wait(1000);
		}
		System.out.print("\033[H\033[2J");
		System.exit(0);
	}
}

