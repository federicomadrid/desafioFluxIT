package com.example.gestor.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.gestor.model.TipoDocumento;
import com.example.gestor.model.Usuario;

import com.example.gestor.service.TipoDocumentoService;
import com.example.gestor.service.UsuarioService;

import com.example.gestor.storage.StorageFileNotFoundException;
import com.example.gestor.storage.StorageService;



import com.example.gestor.service.DocumentoService;

import com.example.gestor.model.Documento;



@Controller
public class DocumentoController {
	
	@Autowired
	private DocumentoService service;
	@Autowired
	private TipoDocumentoService serviceTipoDocumento;
	@Autowired
	private UsuarioService serviceUsuario;
	
	private final StorageService storageService;
	
	@Autowired
    public DocumentoController(StorageService storageService) {
        this.storageService = storageService;
    }
	
		
	@GetMapping("/")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/documentoList");
		mv.addObject("documentos", service.findAll());
		
		mv.addObject("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(DocumentoController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
		
		return mv;
	}
	
	@GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
	
	@ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
	
	@GetMapping("/add")
	public ModelAndView add(Documento documento) {
		
		ModelAndView mv = new ModelAndView("/documentoForm");
		mv.addObject("documento", documento);
		List<TipoDocumento> tiposDocumento = serviceTipoDocumento.findAll();
		mv.addObject("tiposDocumento", tiposDocumento);
		List<Usuario> usuarios = serviceUsuario.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}

			
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
				
		return add(service.findOne(id));
				
		
	}
	
	
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) throws IOException {
		
		//Path p = storageService.load(service.findOne(id).getNombreArchivo());
		storageService.delete(service.findOne(id).getNombreArchivo());
		
		service.delete(id);
		
		return findAll();
	}
	
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Documento documento, BindingResult result, @RequestParam(required = false, name = "file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
		
		if (documento.getId() == null)
		{
			
			storageService.store(file);
	        redirectAttributes.addFlashAttribute("message",
	                "You successfully uploaded " + file.getOriginalFilename() + "!");
	        
	        documento.setNombreArchivo(file.getOriginalFilename());
		}
		
        service.save(documento);
		
		return findAll();
	}

}
