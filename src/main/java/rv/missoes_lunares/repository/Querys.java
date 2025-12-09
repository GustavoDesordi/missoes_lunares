package rv.missoes_lunares.repository;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.Document;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.filters.Filters;
import static org.dizitart.no2.filters.Filters.eq;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

public abstract class Querys extends Recorder {
	
	protected String collection;
	protected String uuid;
	protected String ErroAoSalvarDados = "Erro ao salvar dados no banco ou no arquivo bin";
	
	protected boolean create(Object obj) {
		try {
			// Abre o banco de dados e seleciona a collection
			Nitrite db = Connection.openDatabase();
			NitriteCollection collection_function = db.getCollection(this.collection);
			
			// Cria o UUID
			String id = UUID.randomUUID().toString();
			
			// Salva objeto serializado
	        String className = this.collection;  // Nave, Missao, Astronauta
	        this.saveObject(obj, id, className);
	        
	        // Prepara documento
	        Document doc = Document.createDocument("_tmp", null);
	        doc.remove("_tmp");

	        // Insere o _id
	        doc.put("uuid", id);
	        
	        Field[] fields = obj.getClass().getDeclaredFields();
	        for (Field f : fields) {
	            f.setAccessible(true);

	            String nomeCampo = f.getName();
	            Object valor = f.get(obj);

	            doc.put(nomeCampo, valor);
	        }

	        // Salva no banco
	        collection_function.insert(doc);

			
	        // Fecha o banco de dados
			Connection.closeDatabase();
			
			this.uuid = id;
			return true;
		} catch (Exception e){
			// Fecha o banco de dados
			Connection.closeDatabase();
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(String uuid, String campo, Object valor) {
	    try {
	        // Abre o banco de dados e seleciona a collection
	        Nitrite db = Connection.openDatabase();
	        NitriteCollection col = db.getCollection(this.collection);

	        // Busca pelo registro
	        Document doc = col.find(eq("uuid", uuid)).firstOrDefault();

	        if (doc == null || doc.isEmpty()) {
	            System.out.println("Documento com UUID " + uuid + " não encontrado.");
	            Connection.closeDatabase();
	            return false;
	        }

	        // Atualiza o campo desejado
	        doc.put(campo, valor);

	        // Salva no banco
	        col.update(eq("uuid", uuid), doc);
	        
	        //Atualiza o arquivo bin
	        deleteObject(uuid, this.collection);
	        Map<String, Object> map = new HashMap<>();
	        for (String key : doc.keySet()) {
	            map.put(key, doc.get(key));
	        }
	        saveObject(map, uuid, this.collection);
	        

	        // Fecha o banco de dados
	        Connection.closeDatabase();

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        Connection.closeDatabase();
	        return false;
	    }
	}

	public Document findByField(String campo, String pesquisa) {
	    try {
	    	// Abre o banco de dados e seleciona a collection
	        Nitrite db = Connection.openDatabase();
	        NitriteCollection col = db.getCollection(this.collection);

	        // Busca pelo registro
	        Document doc = col.find(eq(campo, pesquisa)).firstOrDefault();
	        
	        // Fecha o banco de dados
	        Connection.closeDatabase();

	        return doc;

	    } catch (Exception e) {
	    	// Fecha o banco de dados
	        Connection.closeDatabase();
	        return null;
	    }
	}
	
	public List<Document> returnAll() {
	    try {
	    	// Abre o banco de dados e seleciona a collection
	        Nitrite db = Connection.openDatabase();
	        NitriteCollection col = db.getCollection(this.collection);

	        List<Document> lista = new ArrayList<>();
	        for (Document d : col.find()) {
	            lista.add(d);
	        }
	        
	        // Fecha o banco de dados
	        Connection.closeDatabase();

	        return lista;

	    } catch (Exception e) {
	    	// Fecha o banco de dados
	        Connection.closeDatabase();
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}
	
	public List<Document> findManyByField(String campo, String pesquisa) {
	    List<Document> resultados = new ArrayList<>();

	    try {
	        Nitrite db = Connection.openDatabase();
	        NitriteCollection col = db.getCollection(this.collection);

	        for (Document d : col.find(eq(campo, pesquisa))) {
	            resultados.add(d);
	        }

	        Connection.closeDatabase();
	        return resultados;

	    } catch (Exception e) {
	        Connection.closeDatabase();
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}

	public boolean Deletar(String uuid) {
	    try {
	        Nitrite db = Connection.openDatabase();
	        NitriteCollection col = db.getCollection(this.collection);

	        Document doc = col.find(Filters.eq("uuid", uuid)).firstOrDefault();

	        if (doc == null) {
	            System.out.println("Documento com UUID " + uuid + " não encontrado.");
	            Connection.closeDatabase();
	            return false;
	        }

	        WriteResult result = col.remove(Filters.eq("uuid", uuid));

	        // Remove também o arquivo bin associado
	        deleteObject(uuid, this.collection);

	        Connection.closeDatabase();
	        return result.getAffectedCount() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        Connection.closeDatabase();
	        return false;
	    }
	}
}
