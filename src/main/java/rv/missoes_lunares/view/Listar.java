package rv.missoes_lunares.view;

import java.util.ArrayList;
import java.util.List;

import org.dizitart.no2.Document;

import rv.missoes_lunares.service.*;

public class Listar extends Deletar {
	public static void listarObjetos(String obj) {
		System.out.print("\033[H\033[2J");
		List<Document> lista = new ArrayList<>();
		
		switch (obj) {
			case "Missão":
				
				Missao missao = new Missao();
				lista = missao.Listar();
				
				break;
				
			case "Nave":
				
				Nave nave = new Nave();
				lista = nave.Listar();
				
				break;
				
			case "Astronauta":
				
				Astronauta astronauta = new Astronauta();
				lista = astronauta.Listar();
				
				break;
		}
		
		if (lista.isEmpty()) {
			System.out.println("========================================================");
			System.out.println("|           Não há registros para listar.              |");
			System.out.println("========================================================");
			System.out.println("Retornando para o sub menu...");
			Wait(5000);
			return;
		} else {
			for (Document doc : lista) {
			    System.out.println("----- Registro -----");
			    doc.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
			    System.out.println();
			}
			Wait(5000);
		}
	}
}
