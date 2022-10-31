package br.com.gabriel.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gabriel.spring.data.orm.Cargo;
import br.com.gabriel.spring.data.repository.CargoRepository;
import br.com.gabriel.spring.data.service.CrudCargoService;
import br.com.gabriel.spring.data.service.CrudFuncionarioService;
import br.com.gabriel.spring.data.service.CrudUnidadeService;
import br.com.gabriel.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService cargoService;
	private final CrudUnidadeService unidadeService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatoriosService relatoriosService;
	
	private Boolean system = true;
	
	public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeService unidadeService,
			CrudFuncionarioService funcionarioService, RelatoriosService relatoriosService) {
		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		
		while(system) {
			System.out.println("Qual acao voce deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionario");
			System.out.println("4 - Relatorios");
			
			int action = scanner.nextInt();
			if(action == 1) {
				cargoService.inicial(scanner);
			}else if (action == 2) {
				unidadeService.inicial(scanner);
			}else if (action == 3) {
				funcionarioService.inicial(scanner);
			}else if (action == 4) {
				relatoriosService.inicial(scanner);
			} else {
				system = false;
			}
		}
	}

}
