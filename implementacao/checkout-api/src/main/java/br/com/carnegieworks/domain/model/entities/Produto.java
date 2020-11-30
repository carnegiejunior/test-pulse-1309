package br.com.carnegieworks.domain.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "produtos")
public class Produto {
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name = "sku", nullable = true, length = 45)
	@Size(max = 45)
	private String sku;
	
	@Column(name = "descricao", nullable = false)
	@Size(min = 5, max = 45, message = "O campo descrição do produto deve ter pelo menos 5 caracteres e no máximo 45")
	@JsonProperty("descricao")
	@NotBlank(message = "A descrição do produto deve ser preenchida")
	private String descricao;

	@Column(name = "quantidade", nullable = false,length = 10, precision = 2)
	@NotNull(message = "Uma quantidade de produto deve ser informada")
	@PositiveOrZero
	@DecimalMax("9999999.99")
	@JsonProperty("quantidade")
	private BigDecimal quantidade = BigDecimal.ZERO;
	
	@Column(name = "preco_unitario", nullable = false,length = 10, precision = 2)
	@NotNull(message = "O preço unitário do produto deve ser informado")
	@JsonProperty("preco-unitario")
	private BigDecimal precoUnitario = BigDecimal.ZERO;
	
	@Column(name = "url_foto", nullable = false)
	@JsonProperty("url-foto-produto")
	private String urlFotoProduto;
	
}
