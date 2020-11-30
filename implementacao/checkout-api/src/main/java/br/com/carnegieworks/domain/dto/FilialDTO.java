package br.com.carnegieworks.domain.dto;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.carnegieworks.domain.model.entities.Empresa;
import br.com.carnegieworks.domain.model.entities.Filial;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class FilialDTO {

	@NotBlank
	@Column(name = "nome", nullable = false, length = 45)
	@Size(max = 45)
	private String nome;
	
	@NotBlank
	@CNPJ
	@Size(max = 15)
	private String cnpj;
	
	@Valid
	@NotNull
	@JsonProperty(value = "empresa")
	private Empresa empresa;
	
	public Filial transfromToFilial() {
		return new Filial(null, this.nome, this.cnpj, this.empresa);
		
	}
	
}
