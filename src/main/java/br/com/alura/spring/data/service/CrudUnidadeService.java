package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Unidade;
import br.com.alura.spring.data.orm.repository.UnidadeRepository;


@Service
public class CrudUnidadeService {

	private final UnidadeRepository unidadeRepository;
	private boolean system = true;

	public CrudUnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("Qual ação você quer executar ?");
			System.out.println("0 - Sair ?");
			System.out.println("1 - Inserir unidade ?");
			System.out.println("2 - Atualizar unidade ?");
			System.out.println("3 - Exibir unidades ?");
			System.out.println("4 - Deletar unidade ?");

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
				break;
			}
		}

	}

	private void deletar(Scanner scanner) {
		System.out.println("Informe o id para deletar ?");
		Unidade unidade = new Unidade();
		System.out.println("Id");
		int id = scanner.nextInt();
		unidade.setId(id);
		unidadeRepository.delete(unidade);
	}

	private void Atualizar(Scanner scanner) {
		System.out.println("Informe o id para Atualizar ?");
		Unidade unidade = new Unidade();
		System.out.println("Id");
		int id = scanner.nextInt();
		unidade.setId(id);
		System.out.println("Descrição do unidade");
		String descricao = scanner.next();
		unidade.setDescricao(descricao);
		unidadeRepository.save(unidade);
		System.out.println("unidade atualizado");
	}

	private void salvar(Scanner scanner) {
		Unidade unidade = new Unidade();
		System.out.println("Informe a decrição da unidade");
		String descricao = scanner.next();
		unidade.setDescricao(descricao);
		System.out.println("Informe o endereço da unidade");
		String endereco = scanner.next();
		unidade.setEndereco(endereco);
		unidadeRepository.save(unidade);
		System.out.println("unidade salvo");
	}

	private void exibir() {
		System.out.println("Exibindo lista de unidades ");
		Iterable<Unidade> unidades = unidadeRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade) );
	}
}
