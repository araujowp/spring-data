package br.com.alura.spring.data.orm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends  PagingAndSortingRepository<Funcionario, Integer>{

	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByNomeLike(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome  = :nome "
			+ "AND f.salario >= :salario "
			+ "AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioDataContratacao(String nome, Double salario, LocalDate dataContratacao);

	@Query(value = "select * from funcionarios f where data_contratacao >= :dataContratacao ", nativeQuery = true)
	List<Funcionario> findDataContratacaoPos(LocalDate dataContratacao);
	
	@Query(value ="select f.id, f.nome, f.salario from funcionarios f ", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
