package br.com.carnegieworks.domain.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "filiais")
public class Filial {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@NotBlank
	@Column(name = "nome", nullable = false, length = 45)
	@Size(max = 45)
	private String nome;
	
	@NotBlank
	@CNPJ
	@Column(name = "cnpj", nullable = false, length = 15)
	@Size(max = 15)
	private String cnpj;
	
	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)
	@JsonProperty(value = "empresa")
	private Empresa empresa;
}
