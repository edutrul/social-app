package com.inkaapp.appsocial.gui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inkaapp.appsocial.util.HttpHelper;
import com.inkaapp.appsocial.util.UtilHelper;

public class ActividadFullActivity extends Activity {
	
	public String organizacionUID;
	public String organizacionNombre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_actividad);
		fillData();
		
	}
	
	public void fillData() {
		String nid = getIntent().getStringExtra(ListActividadesActivity.ACTIVIDAD_NID);
		String jsonResponse = HttpHelper.connect(ListActividadesActivity.URI + "?nid=" + nid);
	    try {
			JSONArray joArray = new JSONArray(jsonResponse);
			for (int i = 0; i < joArray.length(); i++) {
				JSONObject jsonNode = joArray.getJSONObject(i);
				String imagen = jsonNode.optString("imagen");
				String titulo = jsonNode.optString("node_title");
				String objetivo = jsonNode.optString("objetivo");
				String descripcion = jsonNode.optString("descripcion");
				String fechaInicio = jsonNode.optString("objetivo");
				String fechaFin = jsonNode.optString("objetivo");
				organizacionNombre = jsonNode.optString("organizacion");
			    organizacionUID = jsonNode.optString("uid");
				String verDetalleURI = jsonNode.optString("ver_detalle");
				
				String lugarEncuentroLat = jsonNode.optString("lugar_encuentro_lat");
				String lugarEncuentroLong = jsonNode.optString("lugar_encuentro_long");
				String lugarActividadLat = jsonNode.optString("lugar_actividad_lat");
				String lugarActividadLong = jsonNode.optString("lugar_actividad_long");
				
				((ImageView) findViewById(R.id.imgImagen)).setImageDrawable(
			    		  UtilHelper.LoadImageFromWebOperations(imagen));
				
				((TextView) findViewById(R.id.txtNombreActividad)).setText(titulo);
				((TextView) findViewById(R.id.txtObjetivo)).setText(objetivo);
				((TextView) findViewById(R.id.txtDescripcion)).setText(descripcion);
				((TextView) findViewById(R.id.txtFechaInicio)).setText("17 de Mayo 2014");
				((TextView) findViewById(R.id.txtFechaFin)).setText("18 de Mayo 2014");
				((TextView) findViewById(R.id.txtOrganizacionNombre)).setText(organizacionNombre);
				((TextView) findViewById(R.id.txtLinkDetalle)).setText(verDetalleURI);
				
				((TextView) findViewById(R.id.txtNumeroActividadesRealizadas)).setText("2");
				((TextView) findViewById(R.id.txtPuntuacionPromedio)).setText("4.5");
				((TextView) findViewById(R.id.txtNumeroActividadesTipo)).setText("2");
				((TextView) findViewById(R.id.txtPuntuacionPromedioTipo)).setText("1");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onClickApoyarActividad(View view) {
		System.out.println("APOYANDO ESTÁ ACTIVIDAD");
		
		   // Show Alert 
		   Toast.makeText(getApplicationContext(),
		     "UD ESTÁ PARTICIPANDO DE ESTÁ ACTIVIDAD! SU ACTIVIDAD HA SIDO AGENDADO CON ÉXITO. LE NOTIFICAREMOS MEDIANTE UN CORREO :" , Toast.LENGTH_LONG)
		     .show();
		
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
	
	public boolean onCreateOptionsMenu(Menu menu) {
        
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.my_menu, menu);
	    return true;
	}
}
