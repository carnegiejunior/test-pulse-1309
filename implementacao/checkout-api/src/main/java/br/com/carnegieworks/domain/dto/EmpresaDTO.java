package br.com.carnegieworks.domain.dto;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.carnegieworks.domain.model.entities.Filial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class EmpresaDTO {

	@Column(name = "nome", nullable = false, length = 45)
	@Size(max = 45)
	private String nome;
	
	@OneToMany(mappedBy = "Filial")
	@NotNull
	private List<Filial> filiais = new ArrayList<Filial>();
}
