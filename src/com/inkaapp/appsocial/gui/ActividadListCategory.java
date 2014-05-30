package com.inkaapp.appsocial.gui;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class ActividadListCategory extends Activity {
	ListView listView = null;
	String[] CATEGORIES = new String[] 
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
	String[] arrItemsFound;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_category);
		
		String categoryOpt = "";
		categoryOpt = getIntent().getStringExtra("all-categories");
		
		items.put("6", "Celebración de Fechas especiales");
		items.put("7", "Talleres de capacitación");
		items.put("8", "Donaciones");
		items.put("9", "Visita de entidades");
		items.put("10", "Otros");

	    if (categoryOpt != null && categoryOpt.equalsIgnoreCase("all-categories")) {
	    	arrItemsFound = CATEGORIES;
	    	itemsFound = items;
	    }
	    else {
		
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);

		 
		    Iterator it = items.entrySet().iterator();
		    int i = 0;
		    boolean flag = false;
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        Log.d("debugging", pairs.getKey() + " = " + pairs.getValue());
		        String savedPrefValue = sp.getString(pairs.getKey().toString(), "no-data");
		        if (pairs.getValue().toString().equalsIgnoreCase(savedPrefValue)) {
		            itemsFound.put(pairs.getKey().toString(), savedPrefValue);
		        	flag = true;
		        	i++;
		        }
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    if (flag) {
		    	Log.d("debugging", "tamanio max: " + i);
		    	arrItemsFound = new String[i];
		    	i = 0;
		    	Iterator it2 = itemsFound.entrySet().iterator();
			    while (it2.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it2.next();
			        arrItemsFound[i] = pairs.getValue().toString();
			        it2.remove(); // avoids a ConcurrentModificationException
			        i++;
			    }
		    }
	    }

		
		//Find ListView in the layout.
		listView = (ListView) findViewById(R.id.list);
		
		ArrayAdapter<String> adapter =
		        new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1, arrItemsFound);


		// Assign adapter to ListView
		listView.setAdapter(adapter); 
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
		       // ListView Clicked item index
//			   int itemPosition     = position + 6;
			   String itemPosition = "5";
				  
			   // ListView Clicked item value
			   String  itemValue    = (String) listView.getItemAtPosition(position);
			   Log.d("debugging", "position:" + "" + position);
			   Log.d("debugging", "itemValue:" + "" + itemValue);
			   Iterator it = items.entrySet().iterator();
			   while (it.hasNext()) {
			       Map.Entry pairs = (Map.Entry)it.next();
			       Log.d("debugging", "----"+ pairs.getKey() + " = " + pairs.getValue());
			       if (pairs.getValue().toString().equalsIgnoreCase(itemValue)) {
			          itemPosition =  pairs.getKey().toString();
			       }
			       it.remove(); // avoids a ConcurrentModificationException
			   }
			   
			    // Show Alert 
			    Toast.makeText(getApplicationContext(),
			      "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
			      .show();
			    Intent intent = new Intent(getApplicationContext(), ListActividadesActivity.class);
			    intent.putExtra(ListActividadesActivity.ACTIVIDAD_TID, itemPosition);
			    intent.putExtra(ListActividadesActivity.ACTIVIDAD_TID_NOMBRE, itemValue);
			    Log.d("debugging", "itemPosition: " + itemPosition);
			    startActivity(intent);
			  }
		});
	}
 
}