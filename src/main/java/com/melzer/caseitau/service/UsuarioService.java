package com.melzer.caseitau.service;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.melzer.caseitau.model.Usuario;
import com.melzer.caseitau.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	/** Serviço que retorna uma lista de Usuários. */
	public ResponseEntity<List<Usuario>> listarUsuarios()
	{
		return new ResponseEntity<List<Usuario>>(usuarioRepository.findAll(), HttpStatus.OK);
	}
	
	/** Serviço que retorna um Usuário baseado em seu Id. */
	public ResponseEntity<Usuario> listarUsuario(int id)
	{
		Usuario usuario = usuarioRepository.findById(id);
		if(usuario != null)
		{
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		else
		{
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado - Verifique o parâmetro Id informado");
		}
	}
	
	/** Serviço para cadastrar novos Usuários. */
	public ResponseEntity<Usuario> cadastrarUsuario(Usuario usuario)
	{
	    if(usuario.getId() != 0) 
	    {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro não efetuado - Parametros incorretos.");
	    }
	    if(usuario.getCpf().length() != 11) {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro não efetuado - CPF inválido.");
	    }
	    Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(usuario.getEmail());
        if(!mat.matches()){
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro não efetuado - Email inválido.");
        }
	    return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
	}
	
	/** Serviço para editar Usuários. */	
	public ResponseEntity<Usuario> editarUsuario(Usuario usuario)
	{
		Usuario usuarioOld = usuarioRepository.findById(usuario.getId());
		if(usuarioOld == null){
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado - Verifique o parâmetro Id informado.");
		}
		if(usuario.getData_cadastro() != null){
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alteração não permitida - Remova o parametro de data.");
		}
		return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);
	}
}