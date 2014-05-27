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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inkaapp.appsocial.adapter.ListOrganizacionesAdapter;
import com.inkaapp.appsocial.bean.Organizacion;
import com.inkaapp.appsocial.util.HttpRequest;

public class ListOrganizacionesActivity extends Activity {
	ListOrganizacionesAdapter adapter = null;
	ListView listviewOrganizaciones;
	List<Organizacion> organizaciones;
	private EditText filterText = null;
	public final static String URI = "http://hacks.lalotech.com/ws/organizaciones.json";
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_organizaciones);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		
		String jsonResponse = HttpRequest.get(URI).body();
		
		filterText = (EditText) findViewById(R.id.search_box);

	    filterText.addTextChangedListener(filterTextWatcher);
		
		organizaciones =
		           new ArrayList<Organizacion>();
		
		// MATCH JSON TO BEAN CLASSES.
		Type listType = new TypeToken<List<Organizacion>>(){}.getType();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		organizaciones = gson.fromJson(jsonResponse, listType);
        Log.d("debugging", "" + organizaciones.get(0).getTitulo());
		
		
		adapter = new ListOrganizacionesAdapter(
				ListOrganizacionesActivity.this, organizaciones);
		
		listviewOrganizaciones = (ListView) findViewById(R.id.list_organizaciones);
		listviewOrganizaciones.setAdapter(adapter);
		
		listviewOrganizaciones.setOnItemClickListener(ListViewActivitiesOnItemClickListener);
		
	}
	
	private OnItemClickListener ListViewActivitiesOnItemClickListener = 
			new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
		       // ListView Clicked item index
			   int itemPosition     = position;
			   // ListView Clicked item value
			   Organizacion  organizacion  = (Organizacion) listviewOrganizaciones.getItemAtPosition(itemPosition);
			   
			   // Show Alert 
			   Toast.makeText(getApplicationContext(),
			     "Position :" + itemPosition + "  ListItem : " + organizacion.getTitulo() + organizacion.getNid() , Toast.LENGTH_LONG)
			     .show();
			   Intent intent = new Intent(getApplicationContext(), ListActividadesActivity.class);
			   intent.putExtra(ListActividadesActivity.ORGANIZACION_NOMBRE, organizacion.getTitulo());
			   intent.putExtra(ListActividadesActivity.ORGANIZACION_NID, organizacion.getNid());
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
    		startActivity(new Intent(this, ListOrganizacionesActivity.class));
    		return true;
    	case R.id.itemBusquedaCategorias:
    		startActivity(new Intent(this, ActividadListCategory.class));
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
       }
    }
}
