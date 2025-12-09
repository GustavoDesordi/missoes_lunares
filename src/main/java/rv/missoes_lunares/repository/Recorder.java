	package rv.missoes_lunares.repository;
	
	import java.io.*;
	
	public abstract class Recorder extends Reader {
		
		public boolean saveObject(Object obj, String id, String local) {
			try {
				
				// Valida se o path solicitado existe
				boolean control = false;
				String path[] = {"Astronauta", "MissaoHasAstronauta", "Missao", "Nave"};
				for (int i = 0; i < path.length; i++) {
					if (local.equals(path[i])) {
						control = true;
						break;
					}
				}
				
				if (control == false) { 
					return false;
				}
				
				// Garante que o diretório existe
		        File dir = new File("data/" + local + "Repository");
		        if (!dir.exists()) {
		            dir.mkdirs(); // cria todas as pastas, se necessário
		        }
	
		        // Monta o caminho completo
		        File arquivo = new File(dir, id);
	
		        // Salva o objeto
		        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
		            out.writeObject(obj);
		        }
	
		        return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean deleteObject(String id, String local) {
		    try {

		        // Valida se o path solicitado existe
		        boolean control = false;
		        String[] path = {"Astronauta", "MissaoHasAstronauta", "Missao", "Nave"};
		        for (int i = 0; i < path.length; i++) {
		            if (local.equals(path[i])) {
		                control = true;
		                break;
		            }
		        }

		        if (!control) {
		            return false;
		        }

		        // Garante que o diretório existe
		        File dir = new File("data/" + local + "Repository");
		        if (!dir.exists()) {
		            return false; // não há nada pra deletar
		        }

		        // Caminho do arquivo
		        File arquivo = new File(dir, id);

		        // Se não existir, não tem o que deletar
		        if (!arquivo.exists()) {
		            System.out.println("Arquivo binário não existe: " + arquivo.getAbsolutePath());
		            return false;
		        }

		        // Deleta o arquivo
		        boolean deleted = arquivo.delete();

		        if (!deleted) {
		            System.out.println("Falha ao deletar arquivo: " + arquivo.getAbsolutePath());
		        }

		        return deleted;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	}
