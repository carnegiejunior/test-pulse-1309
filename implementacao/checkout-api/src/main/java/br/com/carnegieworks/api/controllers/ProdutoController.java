package br.com.carnegieworks.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carnegieworks.domain.dto.ProdutoDTO;
import br.com.carnegieworks.domain.model.Produto;
import br.com.carnegieworks.domain.services.ProdutoService;

@RestController
@RequestMapping(path = "produtos",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {
	
	@Autowired private ProdutoService produtoService;
	
	@GetMapping
	public List<Produto> listar() {
		return this.produtoService.buscarTodos();
		
	}

	@PostMapping
	public Produto adicionar(@RequestBody @Valid ProdutoDTO produto) {
		return this.produtoService.salvar(produto.transformToProduto());
		
	}
}
