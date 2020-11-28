package br.com.carnegieworks.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carnegieworks.domain.dto.ProdutoDTO;
import br.com.carnegieworks.domain.dto.ProdutoUpdateQuantidadeDTO;
import br.com.carnegieworks.domain.model.PageModel;
import br.com.carnegieworks.domain.model.PageRequestModel;
import br.com.carnegieworks.domain.model.Produto;
import br.com.carnegieworks.domain.services.ProdutoService;

@RestController
@RequestMapping(path = "produtos",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {
	
	@Autowired private ProdutoService produtoService;
	
	
	@GetMapping
	public ResponseEntity<PageModel<Produto>> listAll(
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "size", defaultValue = "5") int size) {
		
		Optional<PageRequestModel> optionalPageRequestModel = null;
		Optional<PageModel<Produto>> optionalUserPageModel = null;
		
		optionalPageRequestModel = Optional.ofNullable(new PageRequestModel(page, size));
		
		if (optionalPageRequestModel.isPresent()) {

			optionalUserPageModel = this.produtoService.listAllOnLazyMode(optionalPageRequestModel);
			if (optionalUserPageModel.isPresent()) {
				return ResponseEntity.ok(optionalUserPageModel.get());
			}
		
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(value = "/{id}")
	public Produto update(@RequestBody @Valid ProdutoDTO produtoDTO, @PathVariable(name = "id") Long id) {
		return produtoService.update(produtoDTO,id);
	}
	
	@PostMapping
	public Produto add(@RequestBody @Valid ProdutoDTO produto) {
		return this.produtoService.save(produto.transformToProduto());
		
	}
	
	@GetMapping(path = "/{id}")
	public Produto findProdutoById(@PathVariable(name = "id") Long id ) {
		return produtoService.findById(id);
	}
	

	
	// Gerar teste unitário
	@PatchMapping(path = "/{id}")
	public int updateQuantidade(@PathVariable(name = "id") Long id, @RequestBody @Valid ProdutoUpdateQuantidadeDTO produtoUpdateQuantidadeDTO) {
		ProdutoUpdateQuantidadeDTO produto = new ProdutoUpdateQuantidadeDTO();
		produto.setQuantidade(produtoUpdateQuantidadeDTO.getQuantidade());
		return produtoService.updateQuantidade(id,produto);
	}
	
}
