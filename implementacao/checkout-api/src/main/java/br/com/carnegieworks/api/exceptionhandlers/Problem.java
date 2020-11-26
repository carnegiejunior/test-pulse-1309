package br.com.carnegieworks.api.exceptionhandlers;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	private LocalDateTime dateTime;
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
}
