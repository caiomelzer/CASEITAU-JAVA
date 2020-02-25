package com.melzer.caseitau.service;
import com.melzer.caseitau.model.Usuario;
import com.melzer.caseitau.model.Ponto;
import com.melzer.caseitau.model.Relatorio;
import com.melzer.caseitau.repository.PontoRepository;
import com.melzer.caseitau.repository.UsuarioRepository;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PontoService 
{
	@Autowired
	PontoRepository pontoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Ponto registrarPonto(int id_usuario, String tipo_ponto)
	{
		/** Verifica se o Usuário existe. */		
		Usuario usuario = usuarioRepository.findById(id_usuario);
		if(usuario == null){
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuário não encontrado");
		}

		/** Verifica se o tipo de ponto é Entrada ou saída.  */
		if(!tipo_ponto.contentEquals("entrada") && !tipo_ponto.contentEquals("saida")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo de registro inválido");
		}
		
		/** Verifica o tipo de ponto do último registro do usuário, caso tenha.  */
		Ponto ultimo_ponto = pontoRepository.ultimoRegistro(id_usuario);
        System.out.println(ultimo_ponto);
        
        if(ultimo_ponto != null) {
        	if(ultimo_ponto.getTipo_ponto().contentEquals(tipo_ponto)){
    			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não é possível efetuar um registro do mesmo tipo.");
    		}
        }
        
        /** Preenche os dados para o novo ponto.  */
		Ponto ponto = new Ponto();
		ponto.setData_ponto(ZonedDateTime.now());
		ponto.setUsuario(usuario);
		ponto.setTipo_ponto(tipo_ponto);
		if(ultimo_ponto != null){
			/** Computa o intervalo entre o ponto de entrada e saida */
			if(ultimo_ponto.getTipo_ponto().contentEquals("entrada")){
				ponto.setMinutos_dia(ChronoUnit.MINUTES.between(ultimo_ponto.getData_ponto(),ponto.getData_ponto()));
			}
        }
		
		/** Efetua registro do ponto.  */
		return pontoRepository.save(ponto);
	}
	public ResponseEntity<Relatorio> consultarPontoUsuario(int id_usuario)
	{
		List<Ponto> lista_ponto = pontoRepository.relatorioPonto(id_usuario);
		long total_minutos = 0;
		if(lista_ponto != null && lista_ponto.size() > 0){
			/** Computa a coluna minutos_dia do tipo de ponto saida.  */
			for(Ponto ponto : lista_ponto) {
				System.out.println(ponto.getMinutos_dia());
				if(ponto.getTipo_ponto().contentEquals("saida")) {
					total_minutos += ponto.getMinutos_dia();
				}
			}
			
			/** Cria um relatório com o total de minutos.  */
			Relatorio relatorio = new Relatorio();
			relatorio.setRelatorio_ponto(lista_ponto);
			relatorio.setTotal_horas(total_minutos);
			return new ResponseEntity<Relatorio>(relatorio, HttpStatus.OK);
		}
		else
		{
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não tem nenhum registro de ponto.");
		}
	}
}