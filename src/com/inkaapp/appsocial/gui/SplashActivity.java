package com.inkaapp.appsocial.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.inkaapp.appsocial.gui.R;



public class SplashActivity extends Activity {

	private final int delayMillis = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				Intent intent = new Intent(getApplicationContext(), ListActividadesActivity.class);
				intent.putExtra("splash", "splash");
				startActivity(intent);
				finish();
			}
		}, delayMillis);
        
    }
    
	
}
