package com.inkaapp.appsocial.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.inkaapp.appsocial.bean.Actividad;
import com.inkaapp.appsocial.gui.R;
import com.squareup.picasso.Picasso;

public class ListActividadesAdapter extends BaseAdapter implements Filterable {

   private List<Actividad> actividades;
   private LayoutInflater lInflater;
   
   private List<Actividad> filteredData;
   private Context context;
   private ValueFilter valueFilter;
   
   public ListActividadesAdapter(Context context, List<Actividad> actividades) {
      this.lInflater = LayoutInflater.from(context);
      this.context = context;
      this.actividades = actividades;
      
      this.filteredData = actividades;
   }
   
   @Override
   public int getCount() { return actividades.size(); }

   @Override
   public Object getItem(int arg0) { return actividades.get(arg0); }

   @Override
   public long getItemId(int arg0) { return arg0; }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      ContenedorView contenedor = null;
//      System.out.println("getview:"+position+" "+convertView);
      if (convertView == null) {
    	  // inflate method returns a view.
    	 convertView = lInflater.inflate(R.layout.activity_actividad, null);
         
         contenedor = new ContenedorView();
         contenedor.actividadImagen = (ImageView) convertView.findViewById(R.id.actividadImagen);
         contenedor.actividadNombre = (TextView) convertView.findViewById(R.id.actividadNombre);
         contenedor.actividadFecha = (TextView) convertView.findViewById(R.id.actividadFecha);
         
         convertView.setTag(contenedor);
      } else
         contenedor = (ContenedorView) convertView.getTag();

      Actividad actividad = (Actividad) getItem(position);
      contenedor.actividadNombre.setText(actividad.getTitulo());
      contenedor.actividadFecha.setText("" + "" + "");

      Picasso.with(context).load(actividad.getImage()).into(contenedor.actividadImagen);
	      
      return convertView;
   }
   
   class ContenedorView {
	  ImageView actividadImagen;
	  TextView actividadNombre;
      TextView actividadFecha;
      TextView actividadEstado;
   }
   
   @Override
   public Filter getFilter() {
       if(valueFilter == null) {

           valueFilter = new ValueFilter();
       }

       return valueFilter;
   }
   
   private class ValueFilter extends Filter {

	    //Invoked in a worker thread to filter the data according to the constraint.
	    @Override
	    protected FilterResults performFiltering(CharSequence constraint) {
	        FilterResults results = new FilterResults();
	        if (constraint != null && constraint.length() > 0) {
	            ArrayList<Actividad> filterList=new ArrayList<Actividad>();
                Iterator<Actividad> it = filteredData.iterator();
                while (it.hasNext()) {
                	Actividad actividad = it.next();
              	    if (actividad.getTitulo().toUpperCase().
              	        contains(constraint.toString().toUpperCase())) {
              		   System.out.println("OK");
              		   filterList.add(actividad);
              	    }
                }
	            results.count = filterList.size();
	            results.values = filterList;
	        }
	        else{
	            results.count = filteredData.size();
	            results.values = filteredData;
	        }
	        return results;
	    }

	    //Invoked in the UI thread to publish the filtering results in the user interface.
	    @SuppressWarnings("unchecked")
	    @Override
	    protected void publishResults(CharSequence constraint,
	            FilterResults results) {
	    	actividades = (ArrayList<Actividad>) results.values;
	        notifyDataSetChanged();
	    }
	}
   
}
