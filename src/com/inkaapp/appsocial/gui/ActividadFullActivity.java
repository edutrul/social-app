package com.inkaapp.appsocial.gui;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inkaapp.appsocial.bean.Actividad;
import com.inkaapp.appsocial.util.HttpRequest;
import com.squareup.picasso.Picasso;

public class ActividadFullActivity extends Activity {
	
	public String organizacionUID;
	public String organizacionNombre;
	public List<Actividad> actividades;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_actividad);
		fillData();
		
	}
	
	public void fillData() {
		String nid = getIntent().getStringExtra(ListActividadesActivity.ACTIVIDAD_NID);
		String jsonResponse;
		jsonResponse = HttpRequest.get(ListActividadesActivity.URI + "?nid=" + nid).body();
		
		// MATCH JSON TO BEAN CLASSES.
		Type listType = new TypeToken<List<Actividad>>(){}.getType();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        actividades = gson.fromJson(jsonResponse, listType);
						
				Actividad actividad = actividades.get(0);
				Picasso.with(getApplicationContext()).load(actividad.getImage())
				  .into((ImageView) findViewById(R.id.imgImagen));
				
				organizacionUID = actividad.getUid();
				organizacionNombre = actividad.getOrganizacion().getTitulo();
				
				((TextView) findViewById(R.id.txtNombreActividad)).setText(actividad.getTitulo());
				((TextView) findViewById(R.id.txtObjetivo)).setText(actividad.getObjetivo());
				((TextView) findViewById(R.id.txtDescripcion)).setText(actividad.getDescripcion());
				((TextView) findViewById(R.id.txtFechaInicio)).setText(
						new SimpleDateFormat("yyyy-MM-dd").format(
								new Date(actividad.getFechaInicio() * 1000)));
				((TextView) findViewById(R.id.txtFechaFin)).setText(
						new SimpleDateFormat("yyyy-MM-dd").format(
								new Date(actividad.getFechaFin() * 1000)));
				((TextView) findViewById(R.id.txtOrganizacionNombre)).setText(actividad.getOrganizacion().getTitulo());
				((TextView) findViewById(R.id.txtLinkDetalle)).setText("www.google.com.pe");
				((TextView) findViewById(R.id.txtCategoria)).setText(actividad.getCategoria());
				
				((TextView) findViewById(R.id.txtNumeroActividadesRealizadas)).setText("2");
				((TextView) findViewById(R.id.txtPuntuacionPromedio)).setText("4.5");
				((TextView) findViewById(R.id.txtNumeroActividadesTipo)).setText("2");
				((TextView) findViewById(R.id.txtPuntuacionPromedioTipo)).setText("1");
	}
	
	public void onClickApoyarActividad(View view) {
		System.out.println("APOYANDO ESTÁ ACTIVIDAD");
		
		   // Show Alert 
		   Toast.makeText(getApplicationContext(),
		     "UD ESTÁ PARTICIPANDO DE ESTÁ ACTIVIDAD! SU ACTIVIDAD HA SIDO AGENDADO CON ÉXITO. LE NOTIFICAREMOS MEDIANTE UN CORREO :" , Toast.LENGTH_LONG)
		     .show();
		   
		   Calendar cal = Calendar.getInstance();              
           Intent intent = new Intent(Intent.ACTION_EDIT);
           intent.setType("vnd.android.cursor.item/event");
           intent.putExtra("beginTime", cal.getTimeInMillis());
           intent.putExtra("allDay", true);
           intent.putExtra("rrule", "FREQ=YEARLY");
           intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
           intent.putExtra("title", "A Test Event from android app");
           startActivity(intent);
	}
	
	public void onClickCompartirRedesSociales(View view) {
		System.out.println("COMPARTIR REDES SOCIALES");
		 Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    sharingIntent.setType("text/plain");
		    String shareBody = "InkaApp en la #Hackatrix";
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, "Share via"));
	}
	
	public void onClickMasActividades(View view) {
		Intent intent = new Intent(getApplicationContext(), ListActividadesActivity.class);
		intent.putExtra(ListActividadesActivity.ORGANIZACION_UID, organizacionUID);
		intent.putExtra(ListActividadesActivity.ORGANIZACION_NOMBRE, organizacionNombre);
		startActivity(intent);
	}
	
	public void onClickGuardarBusqueda(View view) {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"MY_SHARED_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(, "");
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.my_menu, menu);
	    return true;
	}
}
