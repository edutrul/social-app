package com.inkaapp.appsocial.util;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;

public class UtilHelper {
	
	   public static Drawable LoadImageFromWebOperations(String url) {
		    try {
		        InputStream is = (InputStream) new URL(url).getContent();
		        Drawable d = Drawable.createFromStream(is, "src name");
		        return d;
		    } catch (Exception e) {
		        return null;
		    }
		}
}
