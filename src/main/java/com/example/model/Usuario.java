package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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
    @JoinTable(name = "usuario_usuarios_favoritados", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "usuario_favoritado_id"))
    List<Usuario> usuariosFavoritados;

    @ManyToMany
    @JoinTable(name = "usuario_topicos_de_interesse", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "topico_de_intesse_id"))
    List<TopicosDeInteresse> topicosDeInteresse;

    public Usuario() {

    }

    public Usuario(Integer id, String nome, String email, String senha, String nivel, List<Usuario> usuariosFavoritados) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
        this.usuariosFavoritados = usuariosFavoritados;
    }

    public void addInteresse(List<TopicosDeInteresse> topicos) {
        this.getTopicosDeInteresse().addAll(topicos);
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

    public List<Usuario> getUsuariosFavoritados() {
        return usuariosFavoritados;
    }

    public void setUsuariosFavoritados(List<Usuario> usuariosFavoritados) {
        this.usuariosFavoritados = usuariosFavoritados;
    }

    public void setTopicosDeInteresse(List<TopicosDeInteresse> topicosDeInteresse) {
        this.topicosDeInteresse = topicosDeInteresse;
    }

}

