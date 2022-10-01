package br.com.alura.spring.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeService;
import br.com.alura.spring.data.service.RelatorioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private boolean system = true;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeService unidadeService;
	private final RelatorioService relatorioService;

	public SpringDataApplication(CrudCargoService cargoService, //
			CrudFuncionarioService funcionarioService,
			CrudUnidadeService unidadeService, //
			RelatorioService relatorioService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeService = unidadeService;
		this.relatorioService = relatorioService;
	};

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while (system) {

			System.out.println("Qual ação você quer executar ?");
			System.out.println("0 - Sair ?");
			System.out.println("1 - Cargo ?");
			System.out.println("2 - Funcionario ?");
			System.out.println("3 - Unidade ?");
			System.out.println("4 - Relatorios ?");

			int option = scanner.nextInt();
			switch (option) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
				
			default:
				system = false;
				break;
			}
		}
	}

}
