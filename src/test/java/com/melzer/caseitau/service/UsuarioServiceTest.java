package com.melzer.caseitau.service;
import com.melzer.caseitau.model.Usuario;
import com.melzer.caseitau.repository.UsuarioRepository;


import java.time.ZonedDateTime;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@SpringBootTest
public class UsuarioServiceTest 
{
	@Autowired
	UsuarioService usuarioService;
	
	@MockBean
	UsuarioRepository usuarioRepository;

	Usuario usuario;
	
	@BeforeEach
	public void inicializarVariaveis()
	{	
		usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome_completo("Caio Melzer");
		usuario.setCpf("123.456.789-10");
		usuario.setEmail("teste@teste.com");
	}
	
	@Test
	public void testarListarUsuarioErro()
	{
		Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> {
			usuarioService.listarUsuario(usuario.getId());
		});
	}
	
	@Test
	public void testarListarUsuarioSucesso()
	{
		Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(usuario);
		assertEquals(usuarioService.listarUsuario(usuario.getId()).getStatusCode().value(), 200);
	}
	
	@Test
	public void testarCadastrarUsuarioErro()
	{
		Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
		assertThrows(ResponseStatusException.class, () -> {
			usuarioService.cadastrarUsuario(usuario);
		});
	}
	
	@Test
	public void testarCadastrarUsuarioSucesso()
	{
		Usuario usuario = new Usuario(); 
		usuario.setNome_completo("Caio Oliveira");
		usuario.setCpf("37130262893");
		usuario.setEmail("melzer.caio@gmail.com");
		Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
		assertEquals(usuarioService.cadastrarUsuario(usuario).getStatusCode().value(), 201);		
	}	
	
	@Test
	public void testarEditarUsuarioErro()
	{
		Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(null);
		assertThrows(ResponseStatusException.class, () -> {
			usuarioService.editarUsuario(usuario);
		});
	}
	
	@Test
	public void testarEditarUsuarioSucesso()
	{
		Usuario usuario = new Usuario();
		usuario.setId(1);
		Mockito.when(usuarioRepository.findById(usuario.getId())).thenReturn(usuario);
		Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
		assertEquals(usuarioService.editarUsuario(usuario).getStatusCode().value(), 200);		
	}
}