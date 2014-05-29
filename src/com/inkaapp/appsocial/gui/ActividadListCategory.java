package com.inkaapp.appsocial.gui;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class ActividadListCategory extends Activity {
	ListView listView = null;
	static final String[] CATEGORIES = new String[] 
	  {
		"Celebración de Fechas especiales", 
	    "Talleres de capacitación", 
	    "Donaciones",
	    "Visita de entidades",
	    "Otros",
	  };
	
	HashMap<String,String> items =
	        new HashMap<String,String>();
	HashMap<String,String> itemsFound =
	        new HashMap<String,String>();
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_category);
		
		
		
		items.put("6", "Celebración de Fechas especiales");
		items.put("7", "Talleres de capacitación");
		items.put("8", "Donaciones");
		items.put("9", "Visita de entidades");
		items.put("10", "Otros");

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//		
//		 for (Entry<String, String> entry : items.entrySet()) {
//
//             String savedPref = sharedPreferences.getString(entry.getKey(), "");
//             itemsFound.put(savedPref, entry.getKey());
//             Log.d("debugging", savedPref);
//             Log.d("debugging", savedPref);
//             Toast.makeText(getApplicationContext(), savedPref, Toast.LENGTH_LONG).show();
//
//         }
		 
		    Iterator it = items.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        Log.d("debugging", pairs.getKey() + " = " + pairs.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
	            String savedPref = sharedPref.getString(pairs.getKey().toString(), "");
	            Log.d("debugging", savedPref);
//	            itemsFound.put(savedPref, pairs.getKey().toString());
		    }
		 
		
		//Find ListView in the layout.
		listView = (ListView) findViewById(R.id.list);
		
		
		ArrayAdapter<String> adapter =
		        new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1, CATEGORIES);


		// Assign adapter to ListView
		listView.setAdapter(adapter); 
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
		       // ListView Clicked item index
			   int itemPosition     = position + 6;
			   
			   // ListView Clicked item value
			   String  itemValue    = (String) listView.getItemAtPosition(position);
				  
			    // Show Alert 
			    Toast.makeText(getApplicationContext(),
			      "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
			      .show();
			    Intent intent = new Intent(getApplicationContext(), ListActividadesActivity.class);
			    intent.putExtra(ListActividadesActivity.ACTIVIDAD_TID, String.valueOf(itemPosition));
			    intent.putExtra(ListActividadesActivity.ACTIVIDAD_TID_NOMBRE, itemValue);
			    Log.d("debugging", "itemPosition: " + itemPosition);
			    startActivity(intent);
			  }
			
		});
	}
 
}