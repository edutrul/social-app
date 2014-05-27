package com.inkaapp.appsocial.bean;

import com.google.gson.annotations.Expose;


public class Organizacion {
	@Expose
	private String titulo;
	@Expose
	private String descripcion;
	private String image;
	
	public Organizacion(String titulo, String descripcion, String image) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.image = image;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
