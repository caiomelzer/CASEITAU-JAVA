package com.melzer.caseitau.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;
import com.melzer.caseitau.model.*;
import com.melzer.caseitau.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@SpringBootTest
public class PontoServiceTest 
{
	@Autowired
	PontoService pontoService;
	
	@MockBean
	PontoRepository pontoRepository;

	@MockBean
	UsuarioRepository usuarioRepository;
	
	Usuario usuario;
	Ponto ponto;
	
	@BeforeEach
	public void inicializarVariaveis()
	{	
		usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome_completo("Caio Melzer");
		usuario.setCpf("123.456.789-10");
		usuario.setEmail("teste@teste.com");
		ponto = new Ponto();
		ponto.setId(usuario.getId());
		ponto.setTipo_ponto("entrada");
	}
	
	@Test
	public void testarPontoEntradaSucesso()
	{
		Mockito.when(usuarioRepository.findById(1)).thenReturn(usuario);
		Mockito.when(pontoRepository.ultimoRegistro(1)).thenReturn(null);
		Mockito.when(pontoRepository.save(ArgumentMatchers.any(Ponto.class))).thenReturn(ponto);
		assertNotNull(pontoService.registrarPonto(1,"entrada"));
	}
	
	@Test
	public void testarPontoSaidaSucesso()
	{
		Mockito.when(usuarioRepository.findById(1)).thenReturn(usuario);
		Mockito.when(pontoRepository.ultimoRegistro(1)).thenReturn(null);
		Mockito.when(pontoRepository.save(ArgumentMatchers.any(Ponto.class))).thenReturn(ponto);
		assertNotNull(pontoService.registrarPonto(1,"entrada"));
		Mockito.when(pontoRepository.save(ArgumentMatchers.any(Ponto.class))).thenReturn(ponto);
		assertNotNull(pontoService.registrarPonto(1,"saida"));
	}
	
	@Test
	public void testarPontoEntradaErro()
	{
		Mockito.when(usuarioRepository.findById(1)).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> {
			pontoService.registrarPonto(1,"entrrada");
		});
	}
	
	@Test
	public void testarPontoEntradaErro2()
	{
		Ponto ultimoPonto = new Ponto();
		ultimoPonto.setTipo_ponto("entrada");
		Mockito.when(usuarioRepository.findById(1)).thenReturn(usuario);
		Mockito.when(pontoRepository.ultimoRegistro(1)).thenReturn(ultimoPonto);
		assertThrows(ResponseStatusException.class, () -> {
			pontoService.registrarPonto(1,"entrada");
		});
	}
	
	@Test
	public void testarPontoSaidaErro()
	{
		Ponto ultimoPonto = new Ponto();
		ultimoPonto.setTipo_ponto("saida");
		Mockito.when(usuarioRepository.findById(1)).thenReturn(usuario);
		Mockito.when(pontoRepository.ultimoRegistro(1)).thenReturn(ultimoPonto);
		assertThrows(ResponseStatusException.class, () -> {
			pontoService.registrarPonto(1,"saida");
		});
	}
	
	@Test
	public void testarPontoSaidadeErro2()
	{	
		Mockito.when(usuarioRepository.findById(1)).thenReturn(usuario);
		Mockito.when(pontoRepository.ultimoRegistro(1)).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> {
			pontoService.registrarPonto(1,"saida");
		});
	}
}