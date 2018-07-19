package com.example.gestor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestor.model.Usuario;
import com.example.gestor.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
