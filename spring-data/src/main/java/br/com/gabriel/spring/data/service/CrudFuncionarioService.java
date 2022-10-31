package br.com.gabriel.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.gabriel.spring.data.orm.Cargo;
import br.com.gabriel.spring.data.orm.Funcionario;
import br.com.gabriel.spring.data.orm.Unidade;
import br.com.gabriel.spring.data.repository.CargoRepository;
import br.com.gabriel.spring.data.repository.FuncionarioRepository;
import br.com.gabriel.spring.data.repository.UnidadeRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final UnidadeRepository unidadeRepository;
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	
	
	public CrudFuncionarioService(UnidadeRepository unidadeRepository,CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository) {
		this.unidadeRepository = unidadeRepository;
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
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
	
	private List<Unidade> unidade(Scanner scanner){
		Boolean isTrue = true;
		List<Unidade> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite o UnidadeId (Para sair digite 0");
			Integer unidadeId = scanner.nextInt();
			
			if(unidadeId != 0) {
				Optional<Unidade> unidade = unidadeRepository.findById(unidadeId);
				unidades.add(unidade.get());
			}else {
				isTrue = false;
			}
			
		}
		return unidades;
	}
	
	
	private void salvar(Scanner scanner) {
		System.out.println("Digite o Nome");
		String nome = scanner.next();
		System.out.println("Digite o Cpf");
		String cpf = scanner.next();
		System.out.println("Digite o Salario");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a Data de Contratacao");
		String dataContratacao = scanner.next();
		System.out.println("Digite o CargoId");
		Integer cargoId = scanner.nextInt();
		
		List<Unidade> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidades(unidades);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		System.out.println("Digite o novo Nome");
		String novoNome = scanner.next();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(novoNome);
		
		
		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
