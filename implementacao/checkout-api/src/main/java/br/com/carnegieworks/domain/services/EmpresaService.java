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

import br.com.carnegieworks.domain.dto.EmpresaDTO;
import br.com.carnegieworks.domain.exception.EmpresaNaoEncontradaException;
import br.com.carnegieworks.domain.exception.EntidadeEmUsoException;
import br.com.carnegieworks.domain.model.PageModel;
import br.com.carnegieworks.domain.model.PageRequestModel;
import br.com.carnegieworks.domain.model.entities.Empresa;
import br.com.carnegieworks.domain.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String MSG_EMPRESA_EM_USO = "Empresa de código %d não pode ser removido, pois está em uso";
	private static final String MSG_EMPRESA_NAO_ENCONTRADA = "Empresa de código %d não pode ser encontrado";
	

	public Empresa save(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	public void delete(Long id) {
		try {
			empresaRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EmpresaNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_EMPRESA_EM_USO, id));
		}
	}

	public Empresa findById(Long id) {
		return empresaRepository.findById(id).orElseThrow(() -> new EmpresaNaoEncontradaException(id));
	}

	public Optional<PageModel<Empresa>> listAllOnLazyMode(Optional<PageRequestModel> optionalPageRequestModel) {

		PageModel<Empresa> pageModel = null;
		PageRequestModel pageRequestModel = null;
		Pageable objectPageable = null;
		Page<Empresa> page = null;
		
		if (optionalPageRequestModel.isPresent()) {
			pageRequestModel = optionalPageRequestModel.get();
			objectPageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
			page = this.empresaRepository.findAll(objectPageable);
			pageModel = new PageModel<>(
					(int) page.getTotalElements(),
					page.getSize(),
					page.getTotalPages(),
					page.getContent());
		}
		
		return Optional.ofNullable(pageModel);
	
	}	

	public Empresa update(EmpresaDTO empresaDTO, Long id) {
		Empresa empresaFound = empresaRepository.findById(id)
				.orElseThrow(()-> new EmpresaNaoEncontradaException((String.format(MSG_EMPRESA_NAO_ENCONTRADA, id))));
		BeanUtils.copyProperties(empresaDTO, empresaFound, "id");
		return empresaRepository.save(empresaFound);
	
	}	
	
	
}
