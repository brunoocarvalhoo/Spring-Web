package com.senai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.senai.model.Produto;
import com.senai.repository.Produtos;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/produto")
public class ProdutoControler {

	@Autowired(required = true)
	private Produtos produtos;

	@RequestMapping("/novo")
	public ModelAndView produto() {
		// Retorna a view que deve ser chamada, no caso home (home.jsp) aqui o .jsp é
		// omitido

		ModelAndView mv = new ModelAndView("cadastroProduto.html");
		mv.addObject(new Produto());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Produto produto) {

		String retorno;

		if (produto.getId() != null) {

			retorno = "redirect:/produto/pesquisa";
		} else {

			produtos.save(produto);
			retorno = "redirect:produto/novo";
		}
		return retorno;
	};

	@RequestMapping("/pesquisa")
	public ModelAndView pesquisar() {

		List<Produto> todosProdutos = produtos.findAll();

		ModelAndView mv = new ModelAndView("pesquisaProduto.html");
		mv.addObject("produtos", todosProdutos);
		return mv;

	};

	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Produto produto) {

		ModelAndView mv = new ModelAndView("cadastroProduto.html");
		mv.addObject(produto);

		return mv;

	};

	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Produto produto) {

		produtos.deleteById(produto.getId());

		return "redirect:/produto/pesquisa";
	}
}
