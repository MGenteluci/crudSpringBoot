package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.models.Perfil;
import com.app.models.Usuario;

public interface PerfilRepository extends CrudRepository<Perfil, String>{

	Perfil findById(long id);
	
}