package com.inkaapp.appsocial.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Actividad {
	@Expose
	public String nid;
	@Expose
	public String uid;
	@Expose
	@SerializedName("node_title")
	private String titulo;
	@Expose
	private String imagen;
	@Expose
	private String descripcion;
	@Expose
	private String objetivo;
	@Expose
	private double lugarEncuentroLat;
	@Expose
	private double lugarEncuentrolong;
	@Expose
	private double lugarActividadLat;
	@Expose
	private double lugarActividadlong;
	
	@SerializedName("views_php_15")
	@Expose
	private long fechaInicio;
	@SerializedName("views_php_16")
	@Expose
	private long fechaFin;
	@Expose
	private Organizacion organizacion;
	
	public Actividad() {
		super();
	}
	
	public Actividad(String nid, String uid, String titulo, String image, String descripcion,
			String objetivo, double lugarEncuentroLat,
			double lugarEncuentrolong, double lugarActividadLat,
			double lugarActividadlong, long fechaInicio, long fechaFin, Organizacion organizacion) {
		super();
		this.nid = nid;
		this.uid = uid;
		this.titulo = titulo;
		this.imagen = image;
		this.descripcion = descripcion;
		this.objetivo = objetivo;
		this.lugarEncuentroLat = lugarEncuentroLat;
		this.lugarEncuentrolong = lugarEncuentrolong;
		this.lugarActividadLat = lugarActividadLat;
		this.lugarActividadlong = lugarActividadlong;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.organizacion = organizacion;
	}
	
	public void setNid(String nid) {
		this.nid = nid;
	}
	
	public String getNid() {
		return nid;
	}
	
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImage() {
		return imagen;
	}

	public void setImage(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public double getLugarEncuentroLat() {
		return lugarEncuentroLat;
	}

	public void setLugarEncuentroLat(double lugarEncuentroLat) {
		this.lugarEncuentroLat = lugarEncuentroLat;
	}

	public double getLugarEncuentrolong() {
		return lugarEncuentrolong;
	}

	public void setLugarEncuentrolong(double lugarEncuentrolong) {
		this.lugarEncuentrolong = lugarEncuentrolong;
	}

	public double getLugarActividadLat() {
		return lugarActividadLat;
	}

	public void setLugarActividadLat(double lugarActividadLat) {
		this.lugarActividadLat = lugarActividadLat;
	}

	public double getLugarActividadlong() {
		return lugarActividadlong;
	}

	public void setLugarActividadlong(double lugarActividadlong) {
		this.lugarActividadlong = lugarActividadlong;
	}

	public long getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(long fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public long getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(long fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "nid: " + nid +
		        "uid: " + uid +
				"titulo: " + titulo + 
				"imagen: " + imagen +
				"descripcion: " + descripcion +
				"objetivo: " + objetivo + 
				"lugarActividadLat: " + lugarActividadLat +
				"lugarActividadlong: " + lugarActividadlong +
				"lugarEncuentroLat: " + lugarEncuentroLat +
				"lugarEncuentrolong: " + lugarEncuentrolong +
				"fechaInicio: " + fechaInicio +
				"fechaFin: " + fechaFin +
		        "organizacion.getTitulo(): " + organizacion.getTitulo() +
		        "organizacion.getDescripcion(): " + organizacion.getDescripcion();
	}
	
	
}
