package rv.missoes_lunares.service;

import java.io.Serializable;
import java.util.List;

import org.dizitart.no2.Document;

import rv.missoes_lunares.model.AstronautaDAO;
import rv.missoes_lunares.model.MissaoHasAstronautaDAO;
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
	
	public static boolean Deletar(String uuid) {
		if (uuid.equals(null) || uuid.isBlank() ||uuid.isEmpty()) {
			System.out.print("\033[H\033[2J");
			System.out.println("========================================================");
			System.out.println("UUID invalido retornando ao menu.");
			System.out.println("========================================================");
			return false;
		}
		
		AstronautaDAO astronautaDAO = new AstronautaDAO();
	    Document astronauta = astronautaDAO.findByField("uuid", uuid);

	    if (astronauta == null || astronauta.isEmpty()) {
	        System.out.println("========================================================");
	        System.out.println("Astronauta não encontrada.");
	        System.out.println("========================================================");
	        return false;
	    }
		
		MissaoHasAstronautaDAO missaoHasAstronauta = new MissaoHasAstronautaDAO();
		Document missaoAstronauta = missaoHasAstronauta.findByField("astronauta", uuid);
		
		// Se encontrou um vinculo
	    if (missaoAstronauta != null && !missaoAstronauta.isEmpty()) {
	        System.out.println("========================================================");
	        System.out.println("Astronauta já vinculada a uma missão!");
	        System.out.println("========================================================");
	        return false;
	    }
		
	    // Deleta a nave
	    if (!astronautaDAO.Deletar(uuid)) {
	        System.out.println("========================================================");
	        System.out.println("Erro ao deletar astronauta!");
	        System.out.println("Retornando ao menu.");
	        System.out.println("========================================================");
	        return false;
	    }

	    System.out.println("Sucesso ao deletar astronauta!");
		return true;
	}
}
