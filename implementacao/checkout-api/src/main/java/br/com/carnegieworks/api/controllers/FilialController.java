package br.com.carnegieworks.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.carnegieworks.domain.dto.FilialDTO;
import br.com.carnegieworks.domain.model.PageModel;
import br.com.carnegieworks.domain.model.PageRequestModel;
import br.com.carnegieworks.domain.model.entities.Empresa;
import br.com.carnegieworks.domain.model.entities.Filial;
import br.com.carnegieworks.domain.services.EmpresaService;
import br.com.carnegieworks.domain.services.FilialService;

@RestController
@RequestMapping(path = "filiais", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class FilialController {

	@Autowired
	private FilialService filialService;
	@Autowired
	private EmpresaService empresaService;

	@GetMapping
	public ResponseEntity<PageModel<Filial>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size) {

		Optional<PageRequestModel> optionalPageRequestModel = null;
		Optional<PageModel<Filial>> optionalUserPageModel = null;

		optionalPageRequestModel = Optional.ofNullable(new PageRequestModel(page, size));

		if (optionalPageRequestModel.isPresent()) {

			optionalUserPageModel = this.filialService.listAllOnLazyMode(optionalPageRequestModel);
			if (optionalUserPageModel.isPresent()) {
				return ResponseEntity.ok(optionalUserPageModel.get());
			}

		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/{id}")
	public Filial update(@RequestBody @Valid FilialDTO filialDTO, @PathVariable(name = "id") Long id) {
		return filialService.update(filialDTO, id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Filial insert(@RequestBody @Valid FilialDTO filialDTO) {
		
		Empresa empresa = empresaService.findById(filialDTO.getEmpresa().getId());
		filialDTO.setEmpresa(empresa);
		return this.filialService.save(filialDTO.transfromToFilial());

	}

	@GetMapping(path = "/{id}")
	public Filial findFilialById(@PathVariable(name = "id") Long id) {
		return filialService.findById(id);
	}

	// Gerar teste unitário

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		filialService.delete(id);
	}

}
