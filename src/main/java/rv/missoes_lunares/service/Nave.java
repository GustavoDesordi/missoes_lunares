package rv.missoes_lunares.service;

import java.io.Serializable;
import java.util.List;

import org.dizitart.no2.Document;

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
}
