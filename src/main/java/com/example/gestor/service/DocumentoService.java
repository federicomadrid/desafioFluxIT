package com.example.gestor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestor.model.Documento;
import com.example.gestor.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository repository;
	
	public List<Documento> findAll() {
		return repository.findAll();
	}
	
	public Documento findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public Documento save(Documento post) {
		return repository.save(post);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
