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
		
		while(system ) {
			System.out.println("Qual ação você quer executar ?");
			System.out.println("0 - Sair ?");
			System.out.println("1 - Inserir cargo ?");
			System.out.println("2 - Atualizar cargo ?");
			
			int option = scanner.nextInt();
			
			switch (option) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				Atualizar(scanner);				
			default:
				system = false;
			}
		}
		
	}
	
	private void Atualizar(Scanner scanner) {
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
		System.out.println("Descrição do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo salvo");
	}
}
