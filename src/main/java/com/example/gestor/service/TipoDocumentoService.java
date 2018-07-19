package com.example.gestor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestor.model.TipoDocumento;
import com.example.gestor.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {

	@Autowired
	private TipoDocumentoRepository repository;
	
	public List<TipoDocumento> findAll() {
		return repository.findAll();
	}
	
	public TipoDocumento findOne(Long id) {
		return repository.findById(id).get();
	}
	
	public TipoDocumento save(TipoDocumento tipoDocumento) {
		return repository.save(tipoDocumento);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
