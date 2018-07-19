package com.example.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestor.model.Documento;

@Repository
public interface DocumentoRepository  extends JpaRepository<Documento, Long> {

}
