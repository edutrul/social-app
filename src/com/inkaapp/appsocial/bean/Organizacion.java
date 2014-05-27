package com.inkaapp.appsocial.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Organizacion {
	@Expose
	private String titulo;
	@Expose
	private String descripcion;
	@Expose
	private String nid;
	@Expose
	private String imagen;
	
	public Organizacion(String nid, String titulo, String descripcion, String imagen) {
		super();
		this.nid = nid;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "nid: " + nid +
				"titulo: " + titulo + 
				"image: " + imagen;
	}
}
