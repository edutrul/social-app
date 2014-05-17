package com.inkaapp.appsocial.gui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.inkaapp.appsocial.adapter.ListActividadesAdapter;
import com.inkaapp.appsocial.bean.Actividad;
import com.inkaapp.appsocial.bean.Organizacion;
import com.inkaapp.appsocial.util.HttpHelper;

public class ListActividadesActivity extends Activity {
	ListActividadesAdapter adapter = null;
	ListView listviewActivities;
	private EditText filterText = null;
	public final static String URI = "http://hacks.lalotech.com/ws/actividades.json";
	public final static String ACTIVIDAD_NID = "com.inkaap.appsocial.gui.ACTIVIDAD_NID";
	public final static String ORGANIZACION_UID = "com.inkaap.appsocial.gui.ACTIVIDAD_UID";
	public final static String ORGANIZACION_NOMBRE = "com.inkaap.appsocial.gui.ACTIVIDAD_NOMBRE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_actividades);
		String jsonResponse = "";
		String organizacionUID = getIntent().getStringExtra(ORGANIZACION_UID);
		String organizacionNombre = getIntent().getStringExtra(ORGANIZACION_NOMBRE);
		filterText = (EditText) findViewById(R.id.search_box);

		
	    filterText.addTextChangedListener(filterTextWatcher);
		
		List<Actividad> actividades =
		           new ArrayList<Actividad>();
		
		if (organizacionUID != null) {
			setTitle("Organizador: " + organizacionNombre);
			filterText.setHint("ORGANIZACION: " + organizacionNombre.toUpperCase());
			jsonResponse = HttpHelper.connect(URI + "?uid=" + organizacionUID);
		}
		else {
			jsonResponse = HttpHelper.connect(URI);
		}
		
	    try {
			JSONArray joArray = new JSONArray(jsonResponse);
			for (int i = 0; i < joArray.length(); i++) {
				JSONObject jsonNode = joArray.getJSONObject(i);
				String titulo = jsonNode.optString("node_title");
				String nid = jsonNode.optString("nid");
				String imagen = jsonNode.optString("imagen");
				String uid = jsonNode.optString("uid");
				actividades.add(new Actividad(nid, titulo, imagen, "descripcion", "Objetivo", 
						151051515, -12151510, 150151554, -245465464, 
						1400202469, 1400202476, new Organizacion(uid, "org a", "org desc a", "image a")));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
//		actividades.add(new Actividad("title 2", "test 3", "descripcion", "Objetivo", 
//				151051515, -12151510, 150151554, -245465464, 
//				1400202469, 1400202476, new Organizacion("org b", "org desc b", "image b")));
		
		adapter = new ListActividadesAdapter(
				ListActividadesActivity.this, actividades);
		
		listviewActivities = (ListView) findViewById(R.id.list_activities);
		listviewActivities.setAdapter(adapter);
		
		listviewActivities.setOnItemClickListener(ListViewActivitiesOnItemClickListener);
		
	}
	
	private OnItemClickListener ListViewActivitiesOnItemClickListener = 
			new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
		       // ListView Clicked item index
			   int itemPosition     = position;
			   // ListView Clicked item value
			   Actividad  actividad    = (Actividad) listviewActivities.getItemAtPosition(itemPosition);
			   
			   // Show Alert 
			   Toast.makeText(getApplicationContext(),
			     "Position :" + itemPosition + "  ListItem : " + actividad.getTitulo() + actividad.getNid() , Toast.LENGTH_LONG)
			     .show();
			   Intent intent = new Intent(getApplicationContext(), ActividadFullActivity.class);
			   intent.putExtra(ACTIVIDAD_NID, actividad.getNid());
			   startActivity(intent);
			
		}
	};
	
	private TextWatcher filterTextWatcher = new TextWatcher() {

	    public void afterTextChanged(Editable s) {
	    	System.out.println("afterTextChanged: " + s);
	    }

	    public void beforeTextChanged(CharSequence s, int start, int count,
	            int after) {
	    	System.out.println("beforeTextChanged: " + s + "" + start + "" + count);
	    }

	    public void onTextChanged(CharSequence s, int start, int before,
	            int count) {
	    	System.out.println("Typed: " + s);
	        adapter.getFilter().filter(s);
	    }

	};

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    filterText.removeTextChangedListener(filterTextWatcher);
	}
	
}
