package com.example.gestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.gestor.model.TipoDocumento;
import com.example.gestor.model.Usuario;
import com.example.gestor.service.TipoDocumentoService;
import com.example.gestor.service.UsuarioService;
import com.example.gestor.storage.StorageProperties;
import com.example.gestor.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorApplication.class, args);
	}
	
	@Bean
    CommandLineRunner initStorage(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
	
	@Bean
    CommandLineRunner initTipoDocumento(TipoDocumentoService tipoDocumentoService) {
        return (args) -> {
            tipoDocumentoService.save(new TipoDocumento("Publico"));
            tipoDocumentoService.save(new TipoDocumento("Privado"));
            tipoDocumentoService.save(new TipoDocumento("Draft"));
        };
    }
	
	@Bean
    CommandLineRunner initUsuario(UsuarioService usuarioService) {
        return (args) -> {
        	usuarioService.save(new Usuario("Usuario 1"));
        	usuarioService.save(new Usuario("Usuario 2"));
        	usuarioService.save(new Usuario("Usuario 3"));
        };
    }
}


