package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Informe um nome para pesquisa");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}

		System.out.println("Informe o cpf para pesquisa");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Informe o salario para pesquisa");
		Double salario = scanner.nextDouble();
		
		if(salario == 0 ) {
			salario = null;
		}
		
		System.out.println("Informe a data Contratação para pesquisa");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(data, dateTimeFormatter);
		}

		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
				.where(SpecificationFuncionario.nome(nome)//
						.or(Specification.where(SpecificationFuncionario.cpf(cpf)))
						.or(Specification.where(SpecificationFuncionario.salario(salario)))
						.or(Specification.where(SpecificationFuncionario.dataContratacao(dataContratacao)))
						));
		funcionarios.forEach(System.out::println);
	}
}
