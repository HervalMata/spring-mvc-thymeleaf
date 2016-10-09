package com.washington.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.washington.cobranca.model.StatusTitulo;
import com.washington.cobranca.model.Titulo;
import com.washington.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	Titulos titulos;
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo,Errors erros,RedirectAttributes redirectAttributes) {		
		if(erros.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		
		titulos.save(titulo);
		redirectAttributes.addFlashAttribute("message","Titulo salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping("/pesquisa")
	public ModelAndView pesquisarTitulo() {
		ModelAndView mv = new ModelAndView("PesquisaTitulo");		
		mv.addObject("Titulos", titulos.findAll().toArray());
		return mv;
	}
	
	@RequestMapping("{codigo}") 
	public ModelAndView edicao(@PathVariable Long codigo) {
		
		Titulo titulo = titulos.findOne(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);		
		return mv;
	}
	
	@ModelAttribute("StatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
