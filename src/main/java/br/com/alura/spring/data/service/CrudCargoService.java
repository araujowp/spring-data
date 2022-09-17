package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.repository.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository cargoRepository;
	private boolean system = true;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("Qual ação você quer executar ?");
			System.out.println("0 - Sair ?");
			System.out.println("1 - Inserir cargo ?");
			System.out.println("2 - Atualizar cargo ?");
			System.out.println("3 - Exibir cargos ?");
			System.out.println("4 - Deletar cargo ?");

			int option = scanner.nextInt();

			switch (option) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				Atualizar(scanner);
				break;
			case 3:
				exibir();
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
		Cargo cargo = new Cargo();
		System.out.println("Id");
		int id = scanner.nextInt();
		cargo.setId(id);
		cargoRepository.delete(cargo);
	}

	private void Atualizar(Scanner scanner) {
		System.out.println("Informe o id para Atualizar ?");
		Cargo cargo = new Cargo();
		System.out.println("Id");
		int id = scanner.nextInt();
		cargo.setId(id);
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo atualizado");
	}

	private void salvar(Scanner scanner) {
		System.out.println("Informe o novo cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo salvo");
	}

	private void exibir() {
		System.out.println("Exibindo lista de cargos ");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo) );
	}
}
