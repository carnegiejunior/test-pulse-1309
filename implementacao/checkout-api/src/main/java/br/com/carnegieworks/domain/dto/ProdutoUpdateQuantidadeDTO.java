package br.com.carnegieworks.domain.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProdutoUpdateQuantidadeDTO {

	@NotNull(message = "Quantidade não pode ser nulo")
	private BigDecimal quantidade;
}
