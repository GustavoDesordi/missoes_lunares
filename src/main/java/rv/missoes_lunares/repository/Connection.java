package rv.missoes_lunares.repository;

import org.dizitart.no2.Nitrite;
import java.io.File;

public class Connection {
	
	private static Nitrite db;
	
	private Connection() {}
	
	public static Nitrite openDatabase() {
		
		File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        } 
           
		if (db == null || db.isClosed()) {
	
			db = Nitrite.builder().compressed().filePath("data/meu_malvado_favorito.db").openOrCreate("gru", "minions_vamos_dominar_a_lua");
	
		}
		
		return db;
	}
	
	public static void closeDatabase() {
		if (db != null && !db.isClosed()) {
			
			db.close();
			
		}
	}
}
