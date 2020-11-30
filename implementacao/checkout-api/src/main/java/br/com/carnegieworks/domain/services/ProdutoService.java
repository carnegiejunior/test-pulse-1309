package br.com.carnegieworks.domain.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.carnegieworks.domain.dto.ProdutoDTO;
import br.com.carnegieworks.domain.dto.ProdutoUpdateQuantidadeDTO;
import br.com.carnegieworks.domain.exception.EntidadeEmUsoException;
import br.com.carnegieworks.domain.exception.ProdutoNaoEncontradoException;
import br.com.carnegieworks.domain.model.PageModel;
import br.com.carnegieworks.domain.model.PageRequestModel;
import br.com.carnegieworks.domain.model.entities.Produto;
import br.com.carnegieworks.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";
	private static final String MSG_PRODUTO_NAO_ENCONTRATO = "Produto de código %d não pode ser encontrado";
	

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void delete(Long id) {
		try {
			produtoRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, id));
		}
	}

	public Produto findById(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}

	public Optional<PageModel<Produto>> listAllOnLazyMode(Optional<PageRequestModel> optionalPageRequestModel) {

		PageModel<Produto> pageModel = null;
		PageRequestModel pageRequestModel = null;
		Pageable objectPageable = null;
		Page<Produto> page = null;
		
		if (optionalPageRequestModel.isPresent()) {
			pageRequestModel = optionalPageRequestModel.get();
			objectPageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
			page = this.produtoRepository.findAll(objectPageable);
			pageModel = new PageModel<>(
					(int) page.getTotalElements(),
					page.getSize(),
					page.getTotalPages(),
					page.getContent());
		}
		
		return Optional.ofNullable(pageModel);
	
	}	

	public Produto update(ProdutoDTO produtoDTO, Long id) {
		Produto produtoFound = produtoRepository.findById(id)
				.orElseThrow(()-> new ProdutoNaoEncontradoException((String.format(MSG_PRODUTO_NAO_ENCONTRATO, id))));
		BeanUtils.copyProperties(produtoDTO, produtoFound, "id");
		return produtoRepository.save(produtoFound);
	
	}	
	
	public int updateQuantidade(Long id, ProdutoUpdateQuantidadeDTO produtoDTO) {
		return produtoRepository.updateQuantidade(id, produtoDTO.getQuantidade());
	}
	
}
