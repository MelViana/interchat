package com.example.controller;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.TopicosDeInteresse;
import com.example.repositories.TopicosDeInteresseRepository;
import com.example.repositories.UsuarioRepository;

@RestController
@RequestMapping("/topicosDeInteresses")
public class TopicosDeInteresseRestController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TopicosDeInteresseRepository topicosDeInteresseRepository;
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<List<TopicosDeInteresse>> listarTopicosDeInteresse(@PathVariable Integer id) {
		System.out.println("LISTAGEM DE TÓPICOS");
		return ResponseEntity.ok(usuarioRepository.findById(id).get().getTopicosDeInteresse());
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deletarTopicosDeInteresse(@PathVariable Integer id) {
//		
//		Connection connection = null;
//		  try {
//			  
//			  System.out.println("trying to conectar");
//			  
//		      // Load the JDBC driver
//		      String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver
//		      Class.forName(driverName);
//
//		      // Create a connection to the database
//		      String serverName = "localhost";
//		      String mydatabase = "bd_interchat";
//		      String url = "jdbc:mysql://" + serverName +  "/" + mydatabase; // a JDBC url
//		      String username = "root";
//		      String password = "";
//		      connection = DriverManager.getConnection(url, username, password);
//		  } catch (ClassNotFoundException e) {
//			  System.out.println("catch 1");
//		      // Could not find the database driver
//		  } catch (SQLException e) {
//			  System.out.println("catch 2");
//		      // Could not connect to the database
//		  }
//		  
//		  try {
//		        Statement stmt = (Statement) connection.createStatement();
//
//
//		        System.out.println("Trying to deletar");
//		        
//		        // Use DELETE
//		        String sql = "delete from bd_interchat.usuario_topicos_de_interesse where usuario_id = 4;";
//
//		        // Execute deletion
//		        ((java.sql.Statement) stmt).executeUpdate(sql);
//		    } catch (SQLException e) {
//		    }
//		  
//		  return ResponseEntity.noContent().build();
//		
//	}
	
}

