package com.inkaapp.appsocial.bean;

public class Actividad {
	public String nid;
	private String titulo;
	private String image;
	private String descripcion;
	private String objetivo;
	private double lugarEncuentroLat;
	private double lugarEncuentrolong;
	private double lugarActividadLat;
	private double lugarActividadlong;
	private long fechaInicio;
	private long fechaFin;
	private Organizacion organizacion;
	
	public Actividad() {
		super();
	}
	
	public Actividad(String nid, String titulo, String image, String descripcion,
			String objetivo, double lugarEncuentroLat,
			double lugarEncuentrolong, double lugarActividadLat,
			double lugarActividadlong, long fechaInicio, long fechaFin, Organizacion organizacion) {
		super();
		this.nid = nid;
		this.titulo = titulo;
		this.image = image;
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
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	
}
