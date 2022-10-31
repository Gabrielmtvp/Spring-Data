package br.com.gabriel.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gabriel.spring.data.orm.Cargo;
import br.com.gabriel.spring.data.orm.Unidade;
import br.com.gabriel.spring.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {
	
	private Boolean system = true;
	
	private final UnidadeRepository unidadeRepository;
	
	public CrudUnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual acao de unidade deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	
	private void salvar(Scanner scanner) {
		System.out.println("Descricao da Unidade");
		String descricao = scanner.next();
		System.out.println("Endereco da Unidade");
		String endereco = scanner.next();
		
		Unidade unidade = new Unidade();
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		
		unidadeRepository.save(unidade);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		System.out.println("Descricao da Unidade");
		String descricao = scanner.next();
		System.out.println("Endereco da Unidade");
		String endereco = scanner.next();
		
		Unidade unidade = new Unidade();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		
		unidadeRepository.save(unidade);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<Unidade> unidades = unidadeRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		unidadeRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
