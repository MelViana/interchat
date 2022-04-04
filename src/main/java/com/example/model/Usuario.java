package com.example.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.http.ResponseEntity;

import javax.persistence.JoinColumn;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private String nivel;

	@ManyToMany
	@JoinTable(name = "usuario_topicos_de_interesse", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "topico_de_intesse_id"))
	List<TopicosDeInteresse> topicosDeInteresse;

	public Usuario() {

	}

	public Usuario(Integer id, String nome, String email, String senha, String nivel) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.nivel = nivel;

	}

	public void addInteresse(List<TopicosDeInteresse> topicos) {
		this.getTopicosDeInteresse().addAll(topicos);
//		if (topico.getUsuarios() == null) {
//			System.out.println("CAIU NO IF");
//			List<Usuario> usuarios = new ArrayList<Usuario>();
//			usuarios.add(this);
//			System.out.println("ADICIONOU O USUARIO");
//		} else {
//			topico.getUsuarios().add(this);
//		}

	}
	
	public void editInteresses(List<TopicosDeInteresse> topicos) {
		this.getTopicosDeInteresse().removeAll(topicosDeInteresse);
		
		this.addInteresse(topicos);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public List<TopicosDeInteresse> getTopicosDeInteresse() {
		return topicosDeInteresse;
	}

	public void setTopicosDeInteresse(List<TopicosDeInteresse> topicosDeInteresse) {
		this.topicosDeInteresse = topicosDeInteresse;
	}

}
