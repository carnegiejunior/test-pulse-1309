package br.com.carnegieworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carnegieworks.domain.model.entities.Filial;

public interface FilialRepository extends JpaRepository<Filial, Long>{

}
