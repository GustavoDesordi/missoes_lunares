package rv.missoes_lunares.service;

import java.io.Serializable;
import java.util.List;

import org.dizitart.no2.Document;

import rv.missoes_lunares.model.MissaoDAO;
import rv.missoes_lunares.model.NaveDAO;

public class Nave implements Serializable {
	
	protected String tipo;
	protected int numero_de_tripulantes;
	
	public Nave() {}

	public Nave(String tipo, int numero_de_tripulantes) {
		this.tipo = tipo;
		this.numero_de_tripulantes = numero_de_tripulantes;
	}
	
	public boolean Criar() {
		
		NaveDAO naveDAO = new NaveDAO();
		
		if (!naveDAO.create(Nave.this)) {
			System.out.println("Não foi possível salvar, voltando para o menu.");
			return false;
		}
		
		System.out.println("Nave criada com sucesso!");
		return true;
	}
	
	public List<Document> Listar() {
		
		NaveDAO naveDAO = new NaveDAO();
		return naveDAO.returnAll();
		
	}
	
	public static boolean Deletar(String uuid) {
	    if (uuid == null || uuid.isBlank()) {
	        System.out.println("========================================================");
	        System.out.println("UUID invalido retornando ao menu.");
	        System.out.println("========================================================");
	        return false;
	    }

	    NaveDAO naveDAO = new NaveDAO();
	    Document nave = naveDAO.findByField("uuid", uuid);

	    if (nave == null || nave.isEmpty()) {
	        System.out.println("========================================================");
	        System.out.println("Nave não encontrada.");
	        System.out.println("========================================================");
	        return false;
	    }

	    MissaoDAO missaoDAO = new MissaoDAO();
	    Document missao = missaoDAO.findByField("nave", uuid);

	    // Se encontrou uma missão, significa que a nave está vinculada
	    if (missao != null && !missao.isEmpty()) {
	        System.out.println("========================================================");
	        System.out.println("Nave já vinculada a uma missão!");
	        System.out.println("========================================================");
	        return false;
	    }

	    // Deleta a nave
	    if (!naveDAO.Deletar(uuid)) {
	        System.out.println("========================================================");
	        System.out.println("Erro ao deletar nave!");
	        System.out.println("Retornando ao menu.");
	        System.out.println("========================================================");
	        return false;
	    }

	    System.out.println("Sucesso ao deletar nave!");
	    return true;
	}
}
