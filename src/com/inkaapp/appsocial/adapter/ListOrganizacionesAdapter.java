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

import com.inkaapp.appsocial.bean.Organizacion;
import com.inkaapp.appsocial.gui.R;
import com.squareup.picasso.Picasso;

public class ListOrganizacionesAdapter extends BaseAdapter implements Filterable {

   private List<Organizacion> organizaciones;
   private LayoutInflater lInflater;
   
   private List<Organizacion> filteredData;
   private Context context;
   private ValueFilter valueFilter;
   
   public ListOrganizacionesAdapter(Context context, List<Organizacion> organizaciones) {
      this.lInflater = LayoutInflater.from(context);
      this.context = context;
      this.organizaciones = organizaciones;
      
      this.filteredData = organizaciones;
   }
   
   @Override
   public int getCount() { return organizaciones.size(); }

   @Override
   public Object getItem(int arg0) { return organizaciones.get(arg0); }

   @Override
   public long getItemId(int arg0) { return arg0; }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      ContenedorView contenedor = null;
//      System.out.println("getview:"+position+" "+convertView);
      if (convertView == null) {
    	  // inflate method returns a view.
    	 convertView = lInflater.inflate(R.layout.activity_organizacion, null);
         
         contenedor = new ContenedorView();
         contenedor.organizacionImagen = (ImageView) convertView.findViewById(R.id.organizacionImagen);
         contenedor.organizacionNombre = (TextView) convertView.findViewById(R.id.organizacionNombre);
         
         convertView.setTag(contenedor);
      } else
         contenedor = (ContenedorView) convertView.getTag();

      Organizacion organizacion = (Organizacion) getItem(position);
      contenedor.organizacionNombre.setText(organizacion.getTitulo());

      Picasso.with(context).load(organizacion.getImagen()).into(contenedor.organizacionImagen);
	      
      return convertView;
   }
   
   class ContenedorView {
	  ImageView organizacionImagen;
	  TextView organizacionNombre;
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
	            ArrayList<Organizacion> filterList = new ArrayList<Organizacion>();
                Iterator<Organizacion> it = filteredData.iterator();
                while (it.hasNext()) {
                	Organizacion organizacion = it.next();
              	    if (organizacion.getTitulo().toUpperCase().
              	        contains(constraint.toString().toUpperCase())) {
              		   System.out.println("OK");
              		   filterList.add(organizacion);
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
	    	organizaciones = (ArrayList<Organizacion>) results.values;
	        notifyDataSetChanged();
	    }
	}
   
}
