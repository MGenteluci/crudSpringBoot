package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.models.Perfil;
import com.app.models.Usuario;
import com.app.repository.PerfilRepository;

@Controller
public class PerfilController {

	@Autowired
	private PerfilRepository pr;
	
	@RequestMapping(value="/perfil", method=RequestMethod.GET)
	public String abrirPerfil() {
		return "perfil";
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.POST)
	public String cadastrarPerfil(Perfil perfil) {
		pr.save(perfil);
		return "redirect:/perfis";
	}
	
	@RequestMapping("/perfis")
	public ModelAndView listarPerfis() {
		ModelAndView mv = new ModelAndView("perfis");
		Iterable<Perfil> perfis = pr.findAll();
		mv.addObject("perfis", perfis);
		return mv;
	}
	
	@RequestMapping(value="/editarPerfil/{id}", method=RequestMethod.GET)
	public ModelAndView editarPerfil(@PathVariable("id") long id) {
		Perfil perfil = pr.findById(id);
		ModelAndView mv = new ModelAndView("perfil");
		mv.addObject("perfil", perfil);
		return mv;
	}
	
	@RequestMapping(value="/remover", method=RequestMethod.GET)
	public String remover(long id) {
		
		Perfil perfil = pr.findById(id);
		for(Usuario usuario: perfil.getUsuarios()) {
			usuario.setPerfil(null);
		}
		
		pr.delete(perfil);
		
		return "redirect:/perfis";
	}
	
}