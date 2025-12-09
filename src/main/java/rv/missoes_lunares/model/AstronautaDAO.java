package rv.missoes_lunares.model;

import rv.missoes_lunares.repository.Querys;

import java.io.Serializable;

public class AstronautaDAO extends Querys implements Serializable {

	private String nome;
	private int idade;
	private String especialidade;
	private int horas_de_voo;
	
	public AstronautaDAO() {
		this.collection = "Astronauta";
	}
	
	public boolean create(Object astronauta) {
				
		if (!super.create(astronauta)) {
			System.out.println(this.ErroAoSalvarDados);
			return false;
		}
		
		return true;
	}
}
