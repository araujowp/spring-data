package br.com.alura.spring.data.service;

import java.util.Scanner;

public interface CrudService {

	void inicial(Scanner scanner);
	void deletar(Scanner scanner);
	void Atualizar(Scanner scanner);
	void salvar(Scanner scanner);
}
