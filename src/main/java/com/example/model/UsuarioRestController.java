package com.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.repositories.TopicosDeInteresseRepository;
import com.example.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TopicosDeInteresseRepository topicosDeInteresseRepository;

	@PostMapping
	public ResponseEntity<Usuario> adicionar(@RequestBody Usuario novo) {
		return ResponseEntity.ok(usuarioRepository.save(novo));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		System.out.println("LISTAGEM DE USUARIOS");
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		System.out.println("Ele está entrando no método");
		if (usuarioRepository.existsById(id)) {
			System.out.println("O deletado existe");
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario novo) {
		if (usuarioRepository.existsById(id)) {
			Usuario old = usuarioRepository.getOne(id);

			old.setNome(novo.getNome());
			old.setEmail(novo.getEmail());
			old.setSenha(novo.getSenha());
			old.setNivel(novo.getNivel());

			old = usuarioRepository.save(old);

			return ResponseEntity.ok(old);
		}
		return ResponseEntity.notFound().build();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/usuario/topicosDeInteresse/{id}")
	public ResponseEntity<List<TopicosDeInteresse>> getTopicosByUserId(@PathVariable Integer id) {
		Usuario usuario = usuarioRepository.getById(id);
		return (ResponseEntity<List<TopicosDeInteresse>>) usuario.getTopicosDeInteresse();
	}
	
//	@PostMapping("/topicosDeInteresse/{id}")
//	public ResponseEntity<Usuario> adicionarInteresse(@PathVariable Integer id, @RequestBody TopicosDeInteresse topico) {
//		Usuario usuario = usuarioRepository.getById(id);
//		usuario.addInteresse(topico);
//		return ResponseEntity.ok(usuarioRepository.save(usuario));
//	}

	
	@PutMapping("/topicosDeInteresse/{id}")
	public ResponseEntity<Usuario> atualizarTopicosDeInteresse(@PathVariable Integer id, @RequestBody List<TopicosDeInteresse> topicos) {
		if (usuarioRepository.existsById(id)) {
			Usuario old = usuarioRepository.getOne(id);

			old.editInteresses(topicos);
			old = usuarioRepository.save(old);

			return ResponseEntity.ok(old);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<List<Usuario>> buscarUsuarios(@PathVariable Integer id) {
		System.out.println("LISTAGEM DE USUARIOS");
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	
}

 