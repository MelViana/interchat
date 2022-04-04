package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.TopicosDeInteresse;


public interface TopicosDeInteresseRepository extends JpaRepository<TopicosDeInteresse, Integer> {

	//List<TopicosDeInteresse> findTopicosById(Integer usuarioId);
	
	
}