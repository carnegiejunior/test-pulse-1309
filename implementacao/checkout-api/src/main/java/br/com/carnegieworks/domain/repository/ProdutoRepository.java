package br.com.carnegieworks.domain.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.carnegieworks.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
//	@Query("SELECT DISTINCT u FROM users u WHERE u.email = ?1 AND u.password = ?2")
//	public Optional<User> login(String email, String password);
//
//	@Query("SELECT DISTINCT u FROM users u WHERE u.email = ?1")
//	public Optional<User> findUserByEmail(String email);
//	
//	//public Optional<User> findByEmail(String email);
//		
//	@Transactional(readOnly = false)
//	@Modifying
//	@Query("UPDATE users SET role = ?2 WHERE id = ?1")
//	public int updateRole(Long id, Role role);
	
	
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE produtos SET quantidade = ?2 WHERE id = ?1")
	public int updateQuantidade(Long id, BigDecimal quantidade);
	
	
}
