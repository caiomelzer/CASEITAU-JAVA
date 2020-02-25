package com.melzer.caseitau.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.melzer.caseitau.model.Relatorio;
import com.melzer.caseitau.model.Ponto;
import com.melzer.caseitau.service.PontoService;

@RestController
public class PontoController 
{
	@Autowired
	PontoService pontoService;

	@PostMapping("/ponto/registrar/{tipo_ponto}")
	public Ponto registrarPonto(@RequestBody Ponto ponto, @PathVariable("tipo_ponto") String tipo_ponto)
	{
		return pontoService.registrarPonto(ponto.getUsuario().getId(), tipo_ponto);
	}
	
	@GetMapping("/ponto/{id}")
	public ResponseEntity<Relatorio> consultaPontoUsuario(@PathVariable(value="id") int id)
	{
		return pontoService.consultarPontoUsuario(id);
	}
}