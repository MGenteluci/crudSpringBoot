package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.models.Perfil;
import com.app.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findById(long id);
	
}