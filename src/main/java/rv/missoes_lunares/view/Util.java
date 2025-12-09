package rv.missoes_lunares.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Map;
import java.util.Scanner;

public abstract class Util {
	// Variaveis dos campos
	static Map<String, String> fields_missao = Map.of(
			"uuid", "String",
			"codigo", "String",
			"nome", "String",
			"data de lancamento", "String",
			"destino", "String",
			"objetivo", "String",
			"resultado", "String",
			"nave", "int",
			"astronauta", "int",
			"data de retorno", "String"
	);
	
	static Map<String, String> fields_nave = Map.of(
			"uuid", "String",
			"tipo", "String",
			"numero de tripulantes", "int"
	);
	
	static Map<String, String> fields_astronauta = Map.of(
			"uuid", "String",
			"nome", "String",
			"idade", "int",
			"especialidade", "String",
			"horas de voo", "int"
	);
	
	static String ErroCampoNaoEncontradoNaClasse = "ERRO! Campo não encontrado na classe.";
	static String ErroAoAcessarOCampo = "ERRO! Ao acessar o campo";
	
	// Função para escolhas fora de escopo
	protected static void Invalido() {
		System.out.println("========================================================");
		System.out.println("Escolha invalida, tente novamente!!!");
		System.out.println("========================================================");
	}
	
	// Função para validar a string de data
	protected static String ForceData(String campo, Scanner scanner) {
		String data;
		
		while (true) {
			System.out.print("Informe a " + campo + ": ");
			data = scanner.nextLine();
			
			DateTimeFormatter formatter = DateTimeFormatter
	                .ofPattern("dd/MM/uuuu")
	                .withResolverStyle(ResolverStyle.STRICT);

	        try {
	            LocalDate.parse(data, formatter);
	            return data; 
	        } catch (DateTimeParseException e) {
	        	System.out.println("!!! Entrada inválida. Digite uma data no formato dd/mm/aaaa !!!");
	        }
	    }
    }
	
	// Função para forçar um número
	protected static int ForceInt(String campo, Scanner scanner) {
	    int numero;

	    while (true) {
	        System.out.print("Informe o " + campo + ": ");

	        String input = scanner.nextLine();

	        try {
	            numero = Integer.parseInt(input);
	            return numero;
	        } catch (NumberFormatException e) {
	            System.out.println("!!! Entrada inválida. Digite um número inteiro !!!");
	        }
	    }
	}
	
	// Confirmação da ação
	protected static boolean Confirm(Scanner scanner) {
		System.out.println("\n========================================================");
	    System.out.print("Deseja prosseguir com a criação (S/N): ");
	    String input = scanner.nextLine();
	    
	    input = input.toUpperCase();
	    
	    if (input.equals("S")) {
	    	return true;
	    } else if (input.equals("N")) {
	    	return false;
	    } else {
	    	System.out.print("\033[H\033[2J");
			System.out.println("========================================================");
	    	Confirm(scanner);
	    }
	    
	    return false;
	}
	
	// Função de espera
	protected static void Wait(int time) {
		try {Thread.sleep(time);} catch (InterruptedException e) {Thread.currentThread().interrupt();};
	}
}
