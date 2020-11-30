package br.com.carnegieworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carnegieworks.domain.model.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
