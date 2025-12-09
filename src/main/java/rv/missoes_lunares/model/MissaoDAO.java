package rv.missoes_lunares.model;

import java.io.Serializable;

import rv.missoes_lunares.repository.Querys;

public class MissaoDAO extends Querys implements Serializable {
	
	public MissaoDAO() {
		this.collection = "Missao";
	}
	
	public boolean create(Object missao) {
		
		if (!super.create(missao)) {
			System.out.println(this.ErroAoSalvarDados);
			return false;
		}
		
		return true;
	}
}
