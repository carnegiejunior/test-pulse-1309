package br.com.carnegieworks.domain.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.carnegieworks.domain.model.Produto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
public class ProdutoDTO {

	
	@Column(name = "sku", nullable = true, length = 45)
	@Size(max = 45)
	private String sku;
	
	@Column(name = "descricao", nullable = false)
	@Size(min = 5, max = 45, message = "O campo descrição do produto deve ter pelo menos 5 caracteres e no máximo 45")
	@NotBlank(message = "A descrição do produto deve ser preenchida")
	private String descricao;

	@Column(name = "quantidade", nullable = false)
	@NotNull(message = "Uma quantidade de produto deve ser informada")
	private BigDecimal quantidade = BigDecimal.ZERO;
	
	@Column(name = "preco_unitario", nullable = false, length = 10, precision = 2)
	@NotNull(message = "O precço unitário do produto deve ser informado")
	@JsonProperty("preco-unitario")
	private BigDecimal precoUnitario = BigDecimal.ZERO;
	
	@Column(name = "url_foto", nullable = false)
	@JsonProperty("url-foto-produto")
	private String urlFotoProduto;
	
	public Produto transformToProduto() {
		return new Produto(null, this.sku, this.descricao , this.quantidade, this.precoUnitario, this.urlFotoProduto);
		
	}
}
