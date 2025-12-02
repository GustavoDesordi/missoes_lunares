package rv.missoes_lunares.service;

import java.io.Serializable;
import java.util.List;

import org.dizitart.no2.Document;

import rv.missoes_lunares.model.AstronautaDAO;
import rv.missoes_lunares.model.NaveDAO;

public class Astronauta implements Serializable {
	
	protected String nome;
	protected int idade;
	protected String especialidade;
	protected int horas_de_voo;
	
	public Astronauta() {}

	public Astronauta(String nome, int idade, String especialidade, int horas_de_voo) {
		this.nome = nome;
		this.idade = idade;
		this.especialidade = especialidade;
		this.horas_de_voo = horas_de_voo;
	}
	
	public boolean Criar() {
		
		AstronautaDAO astronautaDAO = new AstronautaDAO();
		
		if (!astronautaDAO.create(Astronauta.this)) {
			System.out.println("Não foi possível salvar, voltando para o menu.");
			return false;
		}
		
		System.out.println("Astronauta criada com sucesso!");
		return true;
	}
	
	public List<Document> Listar() {
		
		AstronautaDAO astronautaDAO = new AstronautaDAO();
		return astronautaDAO.returnAll();
		
	}
}
