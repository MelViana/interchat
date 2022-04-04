package com.example.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TopicosDeInteresse {

    @Id
    private Long id;

	private String descricao;
    
	@JsonIgnore
	@ManyToMany(mappedBy = "topicosDeInteresse")
    List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	
	public TopicosDeInteresse() {
		super();
	}
		
    public TopicosDeInteresse(Long id, String descricao, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuarios = usuarios;
	}
	
}
