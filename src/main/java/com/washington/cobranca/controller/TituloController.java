package com.washington.cobranca.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.washington.cobranca.model.StatusTitulo;
import com.washington.cobranca.model.Titulo;
import com.washington.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	Titulos titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		//TODO: salvar no banco
		titulos.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("message","Titulo salvo com sucesso!");
		
		return mv;
	}
	
	@RequestMapping("/pesquisa")
	public ModelAndView pesquisarTitulo() {
		ModelAndView mv = new ModelAndView("PesquisaTitulo");		
		mv.addObject("Titulos", titulos.findAll().toArray());
		return mv;
	}
	
	@ModelAttribute("StatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
