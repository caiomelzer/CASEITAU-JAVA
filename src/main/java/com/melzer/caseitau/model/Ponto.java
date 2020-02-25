package com.melzer.caseitau.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
@Entity
@Table(name = "ponto")
public class Ponto 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private ZonedDateTime data_ponto;
	@NotNull
	private String tipo_ponto;
	@ManyToOne
	private Usuario usuario;
	
	private Long minutos_dia;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ZonedDateTime  getData_ponto() {
		return data_ponto;
	}

	public void setData_ponto(ZonedDateTime  data_ponto) {
		this.data_ponto = data_ponto;
	}

	public String getTipo_ponto() {
		return tipo_ponto;
	}

	public void setTipo_ponto(String tipo_ponto) {
		this.tipo_ponto = tipo_ponto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getMinutos_dia() {
		return minutos_dia;
	}

	public void setMinutos_dia(Long minutos_dia) {
		this.minutos_dia = minutos_dia;
	}
	
}