package br.com.gabriel.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gabriel.spring.data.orm.Funcionario;
import br.com.gabriel.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de cargo deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionario Por Nome");
			System.out.println("2 - Busca Funcionario Por Nome Salario Maior e Data Contratacao");
			System.out.println("3 - Busca Funcionario Por Data Contratacao");
			
			int action = scanner.nextInt();
			
			switch (action) {
				case 1:
					buscaFuncionarioNome(scanner);
					break;
				case 2:
					buscaFuncionarioNomeSalarioMaiorData(scanner);
					break;
				case 3:
					buscaFuncionarioDataContratacao(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		
		System.out.println("Qual Data Contratacao deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, format);
		
		System.out.println("Qual Salario deseja pesquisar?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual Data Contratacao deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, format);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}

}
