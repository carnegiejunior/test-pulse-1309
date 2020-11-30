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

import br.com.carnegieworks.domain.dto.FilialDTO;
import br.com.carnegieworks.domain.exception.EntidadeEmUsoException;
import br.com.carnegieworks.domain.exception.FilialNaoEncontradaException;
import br.com.carnegieworks.domain.model.PageModel;
import br.com.carnegieworks.domain.model.PageRequestModel;
import br.com.carnegieworks.domain.model.entities.Filial;
import br.com.carnegieworks.domain.repository.FilialRepository;

@Service
public class FilialService {

	@Autowired
	private FilialRepository filialRepository;

	private static final String MSG_FILIAL_EM_USO = "FilialDTO de código %d não pode ser removido, pois está em uso";
	private static final String MSG_FILIAL_NAO_ENCONTRADA = "FilialDTO de código %d não pode ser encontrado";
	

	public Filial save(Filial filial) {
		return filialRepository.save(filial);
	}

	public void delete(Long id) {
		try {
			filialRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new FilialNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FILIAL_EM_USO, id));
		}
	}

	public Filial findById(Long id) {
		return filialRepository.findById(id).orElseThrow(() -> new FilialNaoEncontradaException(id));
	}

	public Optional<PageModel<Filial>> listAllOnLazyMode(Optional<PageRequestModel> optionalPageRequestModel) {

		PageModel<Filial> pageModel = null;
		PageRequestModel pageRequestModel = null;
		Pageable objectPageable = null;
		Page<Filial> page = null;
		
		if (optionalPageRequestModel.isPresent()) {
			pageRequestModel = optionalPageRequestModel.get();
			objectPageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
			page = this.filialRepository.findAll(objectPageable);
			pageModel = new PageModel<>(
					(int) page.getTotalElements(),
					page.getSize(),
					page.getTotalPages(),
					page.getContent());
		}
		
		return Optional.ofNullable(pageModel);
	
	}	

	public Filial update(FilialDTO filialDTO, Long id) {
		Filial filialFound = filialRepository.findById(id)
				.orElseThrow(()-> new FilialNaoEncontradaException((String.format(MSG_FILIAL_NAO_ENCONTRADA, id))));
		BeanUtils.copyProperties(filialDTO, filialFound, "id");
		return filialRepository.save(filialFound);
	
	}	
	
	
}
