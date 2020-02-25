package com.melzer.caseitau.repository;
import com.melzer.caseitau.model.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PontoRepository extends JpaRepository<Ponto, Integer> 
{
	List<Ponto> findAllById(int id);
	
	@Query("select t from Ponto t where t.usuario.id = :id_usuario order by data_ponto desc")
	List<Ponto> relatorioPonto(int id_usuario);
	
	@Query("select t from Ponto t where t.usuario.id = :id_usuario order by data_ponto desc")
	List<Ponto> relatorioPontoHoras(int id_usuario);
	
	@Query(value = "select * from ponto where usuario_id = :id_usuario order by data_ponto desc limit 1", nativeQuery = true)
	Ponto ultimoRegistro(int id_usuario);
}