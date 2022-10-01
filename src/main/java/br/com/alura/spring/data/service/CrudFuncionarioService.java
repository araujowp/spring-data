package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.Unidade;
import br.com.alura.spring.data.orm.repository.CargoRepository;
import br.com.alura.spring.data.orm.repository.FuncionarioRepository;
import br.com.alura.spring.data.orm.repository.UnidadeRepository;

@Service
public class CrudFuncionarioService {

	private final FuncionarioRepository funcionarioRepository;
	private boolean system = true;
	private final CargoRepository cargoRepository;
	private final UnidadeRepository unidadeRepository;
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")  ;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, //
			CargoRepository cargoRepository,//
			UnidadeRepository unidadeRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("======== Funcionario =========");
			System.out.println("Qual ação você quer executar ?");
			System.out.println("0 - Sair ?");
			System.out.println("1 - Inserir funcionario ?");
			System.out.println("2 - Atualizar funcionario ?");
			System.out.println("3 - Exibir funcionarios ?");
			System.out.println("4 - Deletar funcionario ?");

			int option = scanner.nextInt();

			switch (option) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				Atualizar(scanner);
				break;
			case 3:
				exibir(scanner);
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
			}
		}

	}

	private void deletar(Scanner scanner) {
		System.out.println("Informe o id para deletar ?");
		Funcionario funcionario = new Funcionario();
		System.out.println("Id");
		int id = scanner.nextInt();
		funcionario.setId(id);
		funcionarioRepository.delete(funcionario);
	}

	private void Atualizar(Scanner scanner) {
		System.out.println("Informe o id para Atualizar ?");
		System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, dateTimeFormatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");		System.out.println("funcionario atualizado");
	}

	private void salvar(Scanner scanner) {
		System.out.println("Informe o novo funcionario");
		System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contração");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<Unidade> unidades = (List<Unidade>) unidadeRepository.findAll();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, dateTimeFormatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidades(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
	}

	private void exibir(Scanner scanner) {
		System.out.println("Qual pagina voce deseja visualizar ");
		Integer numeroPagina  = scanner.nextInt();
		
		Pageable pageAble  = PageRequest.of(numeroPagina, 5, Sort.by(Sort.Direction.DESC, "nome")  );
		Page<Funcionario> funcionarioPages = funcionarioRepository.findAll(pageAble);
		
		System.out.println(funcionarioPages);
		System.out.println("Pagina atual " + funcionarioPages.getNumber());
		System.out.println("Total elementos " + funcionarioPages.getTotalElements());
		
//		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarioPages.forEach(funcionario -> System.out.println(funcionario));
		
	}
	
}
