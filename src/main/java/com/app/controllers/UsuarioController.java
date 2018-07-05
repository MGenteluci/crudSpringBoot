package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.models.Usuario;
import com.app.repository.PerfilRepository;
import com.app.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private PerfilRepository pr;
	
	@RequestMapping(value="/usuarios", method=RequestMethod.GET)
	public ModelAndView listarUsuarios() {
		ModelAndView mv = new ModelAndView("usuarios");
		
		Iterable<Usuario> usuarios = ur.findAll();
		mv.addObject("usuarios", usuarios);
		
		return mv;
	}
	
	//CADASTRAR MEMBRO
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public String cadastrarUsuario() {
		return "usuario";
	}
	
	@RequestMapping(value="/usuario", method=RequestMethod.POST)
	public String cadastrarUsuario(Usuario usuario) {
		
		usuario.setPerfil(pr.findById(1));
		
		ur.save(usuario);
		return "redirect:/usuarios";
	}
	
	//CADASTRAR ADMINISTRADOR
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String cadastrarAdmin() {
		return "admin";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.POST)
	public String cadastrarAdmin(Usuario usuario) {
		usuario.setPerfil(pr.findById(2));
		
		ur.save(usuario);
		return "redirect:/admin";
	}
	
	//DELETAR USUÁRIO
	@RequestMapping(value="/deletarUsuario")
	public String deletar(long id) {
		Usuario usuario = ur.findById(id);
		
		ur.delete(usuario);
		return "redirect:/usuarios";
	}
	
}