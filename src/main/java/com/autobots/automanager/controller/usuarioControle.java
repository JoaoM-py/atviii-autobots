package com.autobots.automanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.LinkAdders.UserLinkAdder;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.selectors.UsuarioSelecionador;


@RestController
public class usuarioControle {
	
	@Autowired
	private RepositorioUsuario repositorio;
	
	@Autowired
	private UserLinkAdder linkAdd;
	
	@Autowired
	private  UsuarioSelecionador selecionador;
	
	
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> obterUsuario(@PathVariable long id) {
		List<Usuario> usuarios = repositorio.findAll();
		Usuario usuario = selecionador.selecionar(usuarios, id);
		if (usuario == null) {
			ResponseEntity<Usuario> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			linkAdd.addLink(usuario);
			ResponseEntity<Usuario> resposta = new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> obterUsuarios() {
		List<Usuario> usuarios = repositorio.findAll();
		if (usuarios.isEmpty()) {
			ResponseEntity<List<Usuario>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			linkAdd.addLink(usuarios);
			ResponseEntity<List<Usuario>> resposta = new ResponseEntity<>(usuarios, HttpStatus.FOUND);
			return resposta;
		}
	}
	
	
	@PostMapping("/usuario/cadastro")
	public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
		HttpStatus status = HttpStatus.CONFLICT;
		if (usuario.getId() == null) {
			repositorio.save(usuario);
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);

	}
	
	
	@DeleteMapping("/usuario/excluir")
	public ResponseEntity<?> excluirCliente(@RequestBody Usuario exclusao) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Usuario usuario = repositorio.getById(exclusao.getId());
		if (usuario != null) {
			repositorio.delete(usuario);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(status);
	}
	
	

}
