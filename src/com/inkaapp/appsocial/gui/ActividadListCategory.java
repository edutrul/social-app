package com.inkaapp.appsocial.gui;
 
import android.app.Activity;
import android.content.Intent;
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
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_category);
		
		//Find ListView in the layout.
		listView = (ListView) findViewById(R.id.list);
		

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1, CATEGORIES);


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