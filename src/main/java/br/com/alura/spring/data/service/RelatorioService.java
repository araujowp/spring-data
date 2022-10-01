package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.repository.FuncionarioRepository;

@Service
public class RelatorioService {

	private final FuncionarioRepository funcionarioRepository;
//	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY").withLocale(new Locale("pt", "BR"));
//	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private boolean system = true;
	
	public RelatorioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;		
	}
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("selecione sua opção de relatório");
			System.out.println("0 - Sair ");
			System.out.println("1 - Relatorio por nome ");
			System.out.println("2 - Relatorio que contem no nome ");
			System.out.println("3 - Relatorio por nome data contratação e salario ");
			System.out.println("4 - Relatorio a partir de uma data de contratação ");
			int option  = scanner.nextInt();
			switch(option) {
				case 1:
					funcionariosPorNome(scanner);
					break;
				case 2:
					funcionariosQueContemNoNome(scanner);
					break;
				case 3:
					buscaNomeDataSalario(scanner);
					break;
				case 4:
					buscaAPartirDataContratacao(scanner);
					break;
				default:
					system = false;
			}
		}
		
	}

	private void buscaAPartirDataContratacao(Scanner scanner) {
		System.out.println("A partir de qual data deseja pesquisar ?");
		String data = scanner.next();
		
		LocalDate dataContratacao = LocalDate.from(formatter.parse(data));
		List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoPos(dataContratacao);
		funcionarios.forEach(System.out::println);
	}

	private void buscaNomeDataSalario(Scanner scanner) {
		
		System.out.println("Qual nome deseja pesquisar ?");
		String nome = scanner.next();
		
		System.out.println("Qual data de contratacao deseja pesquisar ?");
		String data = scanner.next();
		
		LocalDate dataContratacao = LocalDate.from(formatter.parse(data));
		
		System.out.println("Qual a partir de qual salario deseja pesquisar ?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioDataContratacao(nome, salario, dataContratacao);
		funcionarios.forEach(System.out::println);
		
	}

	private void funcionariosPorNome(Scanner scanner) {
		System.out.println("Informe o nome que deseja pesquisar ");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}

	private void funcionariosQueContemNoNome(Scanner scanner) {
		System.out.println("Informe uma parte do nome que deseja pesquisar ");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNomeLike(nome);
		funcionarios.forEach(System.out::println);
	}
	
}
