package com.inkaapp.appsocial.bean;

public class Organizacion {
	private String titulo;
	private String descripcion;
	private String image;
	private String uid;
	
	public Organizacion(String uid, String titulo, String descripcion, String image) {
		super();
		this.uid = uid;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.image = image;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUid() {
		return uid;
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
