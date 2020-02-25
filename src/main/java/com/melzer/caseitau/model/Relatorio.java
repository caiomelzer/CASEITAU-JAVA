package com.melzer.caseitau.model;
import java.util.List;
public class Relatorio {
	private List<Ponto> relatorioPonto;
	private long total_horas;
	
	public List<Ponto> getRelatorioPontos(){
		return relatorioPonto;
	}
	public void setRelatorio_ponto(List<Ponto> relatorioPonto){
		this.relatorioPonto = relatorioPonto;
	}
	public long getTotal_horas() {
		return total_horas;
	}
	public void setTotal_horas(long total_horas){
		this.total_horas = total_horas;
	}
}