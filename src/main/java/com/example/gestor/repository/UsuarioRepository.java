package com.example.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestor.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

}
