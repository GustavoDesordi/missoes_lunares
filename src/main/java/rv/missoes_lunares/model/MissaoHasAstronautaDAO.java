package rv.missoes_lunares.model;

import java.io.Serializable;
import java.util.UUID;

import rv.missoes_lunares.repository.*;

public class MissaoHasAstronautaDAO extends Querys implements Serializable {

	private String missao;
	private String astronauta;
	
	public MissaoHasAstronautaDAO() {
		this.collection = "MissaoHasAstronauta";
	}
	
	public boolean createVinculo(Object missao) {
		
	    if (!super.create(missao)) {
	        System.out.println(this.ErroAoSalvarDados);
	        return false;
	    }

	    return true;
	}

	public String getMissao() {
		return missao;
	}

	public void setMissao(String missao) {
		this.missao = missao;
	}

	public String getAstronauta() {
		return astronauta;
	}

	public void setAstronauta(String astronauta) {
		this.astronauta = astronauta;
	}

}
