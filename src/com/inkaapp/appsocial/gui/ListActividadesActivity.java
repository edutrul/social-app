package com.inkaapp.appsocial.gui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inkaapp.appsocial.adapter.ListActividadesAdapter;
import com.inkaapp.appsocial.bean.Actividad;
import com.inkaapp.appsocial.util.HttpRequest;

public class ListActividadesActivity extends Activity {
	ListActividadesAdapter adapter = null;
	ListView listviewActivities;
	private EditText filterText = null;
	public final static String URI = "http://hacks.lalotech.com/ws/actividades.json";
	public final static String ACTIVIDAD_NID = "com.inkaap.appsocial.gui.ACTIVIDAD_NID";
	public final static String ACTIVIDAD_TID = "com.inkaap.appsocial.gui.ACTIVIDAD_TID";
	public final static String ACTIVIDAD_TID_NOMBRE = "com.inkaap.appsocial.gui.ACTIVIDAD_TID_NOMBRE";
	
	public final static String ORGANIZACION_UID = "com.inkaap.appsocial.gui.ACTIVIDAD_UID";
	public final static String ORGANIZACION_NOMBRE = "com.inkaap.appsocial.gui.ORGANIZACION_NOMBRE";
	public final static String ORGANIZACION_NID = "com.inkaap.appsocial.gui.ORGANIZACION_NID";

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_actividades);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		//Ocultando KeyBoard
		  this.getWindow().setSoftInputMode(
		    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		String jsonResponse = "";
		
		String organizacionUID = getIntent().getStringExtra(ORGANIZACION_UID);
		String organizacionNombre = getIntent().getStringExtra(ORGANIZACION_NOMBRE);
		String actividadTID = getIntent().getStringExtra(ACTIVIDAD_TID);
		String actividadTIDNombre = getIntent().getStringExtra(ACTIVIDAD_TID_NOMBRE);
		String organizacionNID = getIntent().getStringExtra(ORGANIZACION_NID);
		
		filterText = (EditText) findViewById(R.id.search_box);

	    filterText.addTextChangedListener(filterTextWatcher);
		
		List<Actividad> actividades =
		           new ArrayList<Actividad>();
		
		Log.d("debugging", "organizacionUID:" + organizacionUID);
		Log.d("debugging", "organizacionNombre:" + organizacionNombre);
		Log.d("debugging", "actividadTID:" + actividadTID);
		Log.d("debugging", "actividadTIDNombre:" + actividadTIDNombre);
		Log.d("debugging", "actividadTIDNombre:" + actividadTIDNombre);
		
		if (organizacionUID != null) {
			setTitle("Organizador: " + organizacionNombre);
			filterText.setHint("ORGANIZACION: " + organizacionNombre.toUpperCase());
			jsonResponse = HttpRequest.get(URI + "?uid=" + organizacionUID).body();
		}
		else if (actividadTID != null) {
			setTitle("CATEGORIA: " + actividadTIDNombre);
			filterText.setHint("CATEGORIA: " + actividadTIDNombre.toUpperCase());
			jsonResponse = HttpRequest.get(URI + "?tid=" + actividadTID).body();
		}
		else if (organizacionNID != null) {
			jsonResponse = HttpRequest.get(URI).body();
			jsonResponse = HttpRequest.get(URI + "?nidOrganizacion=" + organizacionNID).body();
			setTitle("Organizador: " + organizacionNombre);
			filterText.setHint("ORGANIZACION: " + organizacionNombre.toUpperCase());
		}
		
		// MATCH JSON TO BEAN CLASSES.
		Type listType = new TypeToken<List<Actividad>>(){}.getType();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        actividades = gson.fromJson(jsonResponse, listType);
        
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
	
	public boolean onCreateOptionsMenu(Menu menu) {
        
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.my_menu, menu);
	    return true;
	}
	
	 /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
    	case R.id.itemInicio:
    		startActivity(new Intent(this, ListActividadesActivity.class));
    		return true;
    	case R.id.itemBusquedaCategorias:
    		Intent intent = new Intent(this, ActividadListCategory.class);
    		intent.putExtra("all-categories", "all-categories");
    		startActivity(intent);
    		return true;
    	case R.id.itemMisBusquedas:
    		startActivity(new Intent(this, ActividadListCategory.class));
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
       }
    }
}
