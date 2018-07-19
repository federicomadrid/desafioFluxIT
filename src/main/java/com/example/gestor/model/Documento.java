package com.example.gestor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	@ManyToOne
	private TipoDocumento tipoDocumento;

	
	@ManyToOne
	private Usuario owner;
	
	private String descripcion;
	
	private String nombreArchivo;
	
	@Column(updatable = false)
	private Date created;
	
	@PrePersist
	protected void onCreate() {
	    created = new Date();
	}
	
	
	public Documento() {
	}

	public Documento(String descripcion, TipoDocumento tipoDocumento, Usuario owner, String nombreArchivo) {
		this.tipoDocumento = tipoDocumento;
		this.owner = owner;
		this.descripcion = descripcion;
		this.nombreArchivo = nombreArchivo;
	}

	public Long getId() {
		return id;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}

	

	

	
	
	
		
	
}
