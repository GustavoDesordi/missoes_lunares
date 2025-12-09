package rv.missoes_lunares.repository;

import java.io.*;

public abstract class Reader {

	public Object readObject(String id, String local) {
		try {
			// Valida se o path solicitado existe
			boolean control = false;
			String path[] = {"Atronauta", "MissaoHasAstronauta", "MissaoHasNave", "Missao", "Nave"};
			for (int i = 0; i < path.length; i++) {
				if (local == path[i]) {
					control = true;
					break;
				}
			}
			
			if (control == false) { 
				return null;
			}
			
			// Garante que o diretório existe
	        File dir = new File("data/" + local + "Repository");
	        if (!dir.exists()) {
	            dir.mkdirs(); // cria todas as pastas, se necessário
	        }
	        
	        // monta o path do arquivo
            File file = new File(dir, id + ".bin");
            if (!file.exists()) {
                return null;
            }
	        
            // lê o objeto
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return ois.readObject();
            }
	        
			
		} catch (Exception e) {
			return null;
		}
	}
}


