package com.melzer.caseitau.repository;
import com.melzer.caseitau.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findById(int id);
}