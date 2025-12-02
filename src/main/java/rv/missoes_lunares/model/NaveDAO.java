package rv.missoes_lunares.model;

import rv.missoes_lunares.repository.Querys;

import java.io.Serializable;

public class NaveDAO extends Querys implements Serializable {
	
	public NaveDAO() {
		this.collection = "Nave";
	}
	
	public boolean create(Object nave) {
		
		if (!super.create(nave)) {
			System.out.println(this.ErroAoSalvarDados);
			return false;
		}
		
		return true;
	}
}
