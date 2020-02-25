package com.melzer.caseitau.controller;
import com.melzer.caseitau.model.Usuario;
import com.melzer.caseitau.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
@RestController
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	/** Endpoint para listar todos os usuários */
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios()
    {
		return usuarioService.listarUsuarios();
	}
    
    /** Endpoint para listar usuário por Id */
    @GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> consultaUsuarioUnico(@PathVariable(value="id") int id)
	{
		return usuarioService.listarUsuario(id);
	}
    
    /** Endpoint para cadastrar usuários */
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario)
	{
		return usuarioService.cadastrarUsuario(usuario);
	}
    
    /** Endpoint para editar usuários */
    @PutMapping("/usuario")
	public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario)
	{
		return usuarioService.editarUsuario(usuario);
	}
}