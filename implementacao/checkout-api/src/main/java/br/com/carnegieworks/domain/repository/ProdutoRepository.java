package br.com.carnegieworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carnegieworks.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
