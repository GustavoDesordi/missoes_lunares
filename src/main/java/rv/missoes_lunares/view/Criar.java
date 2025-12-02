package rv.missoes_lunares.view;

import rv.missoes_lunares.service.*;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;

public abstract class Criar extends Listar {
	protected static void criarObjeto(String obj) {
		System.out.print("\033[H\033[2J");
		Scanner scanner = new Scanner(System.in);
		
		switch (obj) {
			case "Missão":
				
				Missao missao = new Missao();

			    for (Map.Entry<String, String> entry : Util.fields_missao.entrySet()) {

			        String campo = entry.getKey();
			        String tipo = entry.getValue();

			        if (campo.equals("uuid")) {
			            continue;
			        }
			        
			        if (campo.equals("nave") || campo.equals("astronauta")) {
			        	System.out.println("O campo " + campo + " deve ser vinculados posteriomente no Submenu");
			        	continue;
			        }

			        Object valor;

			        if (tipo.equals("int")) {
			            valor = Util.ForceInt(campo, scanner);
			        } else {
			        	if (campo.equals("data de lancamento") || campo.equals("data de retorno")) {
			        		valor = Util.ForceData(campo, scanner);
			        	} else {
			        		System.out.print("Informe o " + campo + ": ");
			        		valor = scanner.nextLine();
			        	}
			        }

			        AuxInsertInputInClass(missao, campo, valor);
			    }
			    
			    if(!Util.Confirm(scanner)) {
			    	return;
			    }

			    missao.Criar();
			    Wait(5000);
				break;
				
			case "Nave":
				
				Nave nave = new Nave();

			    for (Map.Entry<String, String> entry : Util.fields_nave.entrySet()) {

			        String campo = entry.getKey();
			        String tipo = entry.getValue();

			        if (campo.equals("uuid")) {
			            continue;
			        }

			        Object valor;

			        if (tipo.equals("int")) {
			            valor = Util.ForceInt(campo, scanner);
			        } else {
			            System.out.print("Informe o " + campo + ": ");
			            valor = scanner.nextLine();
			        }

			        AuxInsertInputInClass(nave, campo, valor);
			    }
			    
			    if(!Util.Confirm(scanner)) {
			    	return;
			    }

			    nave.Criar();
			    Wait(5000);
				break;
				
			case "Astronauta":
				
				Astronauta astronauta = new Astronauta();

			    for (Map.Entry<String, String> entry : Util.fields_astronauta.entrySet()) {

			        String campo = entry.getKey();
			        String tipo = entry.getValue();

			        if (campo.equals("uuid")) {
			            continue;
			        }

			        Object valor;

			        if (tipo.equals("int")) {
			            valor = Util.ForceInt(campo, scanner);
			        } else {
			            System.out.print("Informe " + campo + ": ");
			            valor = scanner.nextLine();
			        }

			        AuxInsertInputInClass(astronauta, campo, valor);
			    }
			    
			    if(!Util.Confirm(scanner)) {
			    	return;
			    }

			    astronauta.Criar();
			    Wait(5000);
				break;
		}
	}

	protected static void AuxInsertInputInClass(Object obj, String campo, Object valor) {
		try {
            Field field = obj.getClass().getDeclaredField(
                campo.replace(" ", "_")
            );
            field.setAccessible(true);
            field.set(obj, valor);

        } catch (NoSuchFieldException e) {
            System.out.println(Util.ErroCampoNaoEncontradoNaClasse);
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            System.out.println(Util.ErroAoAcessarOCampo);
            e.printStackTrace();
        }
	}
	
	protected static void Vincular(String obj) {
		String uuid_missao;
		String uuid_vinculo;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\033[H\033[2J");
		System.out.print("Digite o uuid da missão: ");
		uuid_missao = scanner.nextLine();
		
		if (obj.equals("Nave")) {
			System.out.print("Digite o uuid da nave: ");
			uuid_vinculo = scanner.nextLine();
		} else {
			System.out.print("Digite o uuid do astronauta: ");
			uuid_vinculo = scanner.nextLine();
		}
		
		Missao missao = new Missao();
		missao.Vincular(uuid_missao, uuid_vinculo, obj);
		Wait(5000);
	}
}
