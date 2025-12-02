package rv.missoes_lunares.service;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.dizitart.no2.Document;

import rv.missoes_lunares.model.*;

public class Missao implements Serializable{
    protected String codigo;
	protected String nome;
	protected String data_de_lancamento;
	protected String data_de_retorno;
	protected String destino;
	protected String objetivo;
	protected String resultado;
	
	public Missao() {}
	
	public Missao(String codigo, String nome, String data_de_lancamento, String destino, String objetivo, String resultado, String data_de_retorno) {
		this.codigo = codigo;
		this.nome = nome;
		this.data_de_lancamento = data_de_lancamento;
		this.destino = destino;
		this.objetivo = objetivo;
		this.resultado = resultado;
		this.data_de_retorno = data_de_retorno;
	}



	public boolean Criar() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    LocalDate lancamento = LocalDate.parse(this.data_de_lancamento, formatter);
	    LocalDate retorno = LocalDate.parse(this.data_de_retorno, formatter);

	    if (lancamento.isAfter(retorno)) {
	    	System.out.println("Data de lançamento maior que a data de retorno, por favor refaça o cadastro.");
	    	System.out.println("Voltando para o sub menu...");
	        return false;
	    }
	    
	    MissaoDAO missaoDAO = new MissaoDAO();
		
		Document data = missaoDAO.findByField("codigo", this.codigo);
		
		if (data != null && !data.isEmpty()) {
			System.out.println("Código está em uso numa missão, por favor reinicie o cadastro!");
			System.out.println("Voltando para o sub menu...");
			return false;
		}
		
		if (!missaoDAO.create(Missao.this)) {
			System.out.println("Não foi possível salvar, voltando para o sub menu.");
			return false;
		}
		
		System.out.println("Missão criada com sucesso!");
		return true;
	};
	
	public List<Document> Listar() {
		
		MissaoDAO missaoDAO = new MissaoDAO();
		return missaoDAO.returnAll();
		
	}
	
	public static boolean Vincular(String uuid_missao, String uuid_vinculo, String obj) {
		
		MissaoDAO missaoDAO = new MissaoDAO();
		
		if (uuid_missao == null || uuid_vinculo == null) {
			System.out.println("UUID não foi localizado.");
			System.out.println("Voltando para o sub menu...");
			return false;
		}
		
		
		Document missao = missaoDAO.findByField("uuid", uuid_missao);
		
		if (missao == null || missao.isEmpty()) {
			System.out.println("UUID da missão não foi localizado.");
			System.out.println("Voltando para o sub menu...");
			return false;
		}
		
		if (obj.equals("Nave")) {
			
			if (missao.get("nave") != null) {
				System.out.println("Missão com nave já vinculada!");
				System.out.println("Voltando para o sub menu...");
				return false;
			}
			
			NaveDAO naveDAO = new NaveDAO();
			
			Document nave = naveDAO.findByField("uuid", uuid_vinculo);
			
			if (nave == null || nave.isEmpty()) {
				System.out.println("UUID da nave não foi localizado.");
				System.out.println("Voltando para o sub menu...");
				return false;
			}
			
			// Atualizando a informação no banco de dados de missão
			if (!missaoDAO.update(uuid_missao, obj.toLowerCase(), uuid_vinculo)) {
				System.out.println("Não foi possivel atualizar os dados.");
				System.out.println("Voltando para o sub menu...");
				return false;
			}
			
		} else {
			
			if (missao.get("nave") == null) {
				System.out.println("Deve ser vinculado a nave antes de vincular o astronauta!");
				System.out.println("Voltando para o sub menu...");
				return false;
			}
			
			AstronautaDAO astronautaDAO = new AstronautaDAO();
			
			Document astronauta = astronautaDAO.findByField("uuid", uuid_vinculo);
			
			if (astronauta == null && astronauta.isEmpty()) {
				System.out.println("UUID do astronauta não foi localizado.");
				System.out.println("Voltando para o sub menu...");
				return false;
			}
			
			if ((int) astronauta.get("idade") < 21) {
				System.out.println("Astronauta não atende a idade minima de 21 anos.");
				System.out.println("Voltando para o sub menu...");
				return false;
			}
			
			if (missao.get("astronauta") == null) {
				
				NaveDAO naveDAO = new NaveDAO();
				
				Document nave = naveDAO.findByField("uuid", (String) missao.get("nave"));
				
				if ((int) nave.get("numero_de_tripulantes") <= 0) {
					System.out.println("A nave não consegue comportar mais tripulantes");
					System.out.println("Voltando para o sub menu...");
					return false;
				}
				
				MissaoHasAstronautaDAO missaoHasAstronauta = new MissaoHasAstronautaDAO();
				missaoHasAstronauta.setAstronauta(uuid_vinculo);
				missaoHasAstronauta.setMissao(uuid_missao);
			
				// Atualizando a informação no banco de dados de missão
				if (!missaoDAO.update(uuid_missao, obj.toLowerCase(), missaoHasAstronauta.createVinculo(missaoHasAstronauta))) {
					System.out.println("Não foi possivel atualizar os dados.");
					System.out.println("Voltando para o sub menu...");
					return false;
				}
				
			} else {
				
				NaveDAO naveDAO = new NaveDAO();
				
				Document nave = naveDAO.findByField("uuid", (String) missao.get("nave"));
				
				MissaoHasAstronautaDAO missaoHasAstronauta = new MissaoHasAstronautaDAO();
				
				List<Document> achou_astronauta = missaoHasAstronauta.findManyByField("astronauta", uuid_vinculo);
				
				if (achou_astronauta.size() > 0) {
					System.out.println("Tripulante já vinculado a missão!");
					System.out.println("Voltando para o sub menu...");
					return false;
				}
				
				List<Document> astronautas_missao =  missaoHasAstronauta.findManyByField("vinculo_uuid", (String) missao.get("astronauta"));
				
				if ((int) nave.get("numero_de_tripulantes") >= astronautas_missao.size()) {
					System.out.println("Número de tripulantes já atingido na nave.");
					System.out.println("Voltando para o sub menu...");
					return false;
				}
				
				missaoHasAstronauta.setAstronauta(uuid_vinculo);
				missaoHasAstronauta.setMissao(uuid_missao);
				
				// Atualizando a informação no banco de dados de missão
				if (!missaoDAO.update(uuid_missao, obj.toLowerCase(), missaoHasAstronauta.createVinculo2((String) missao.get("astronauta") ,missaoHasAstronauta))) {
					System.out.println("Não foi possivel atualizar os dados.");
					System.out.println("Voltando para o sub menu...");
					return false;
				}
				
			}
		}
		
		System.out.println("Vinculo criado com sucesso!");
		return true;
	}
}
