package com.example.controller;

import com.example.model.TopicosDeInteresse;
import com.example.model.Usuario;
import com.example.repositories.TopicosDeInteresseRepository;
import com.example.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/listarUsuariosSemelhantes/{id}")
    public ResponseEntity<List<Usuario>> listarUsuariosSemelhantes(@PathVariable Integer id) {
        Usuario usuario = null;
        List<Usuario> allUsers = usuarioRepository.findAll();
        List<Usuario> matches = new ArrayList<>();
        if (usuarioRepository.existsById(id)) {
            usuario = usuarioRepository.getOne(id);
        }
        List<TopicosDeInteresse> interessesUsuarioLogado = usuario.getTopicosDeInteresse();
        for (Usuario usuarioAtual : allUsers) {
            if (!usuarioAtual.getId().equals(id)) {
                for (TopicosDeInteresse topicosDeInteresse : usuarioAtual.getTopicosDeInteresse()) {
                    if (interessesUsuarioLogado.contains(topicosDeInteresse)) {
                        matches.add(usuarioAtual);
                    }
                }
            }
        }
        return ResponseEntity.ok(matches);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (usuarioRepository.existsById(id)) {
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

    @PostMapping("/usuariosFavoritados/adicionar/{id}")
    public ResponseEntity<List<Usuario>> adicionarusuariosFavoritados(@PathVariable Integer id, @RequestBody List<Integer> usuariosID) {
        List<Usuario> returnedUsers = new ArrayList<>();
        List<Integer> usuariosId = new ArrayList<>();

        for (Integer usuarioId : usuariosID) {
            Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
            if (usuario.isPresent()) {
                usuariosId.add(usuario.get().getId());
                returnedUsers.add(usuario.get());
            }
        }
        if (usuarioRepository.existsById(id)) {
            Usuario usuarioLogado = usuarioRepository.getById(id);
            usuarioLogado.setUsuariosFavoritados(returnedUsers);
            usuarioRepository.save(usuarioLogado);
        }
        return ResponseEntity.ok(returnedUsers);
    }


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

}

 

 