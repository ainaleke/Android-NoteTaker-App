package com.notetaker2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
		//showToast("Loading...Please Wait");
		
		 Thread logoTimer = new Thread() 
		 { //Initialize the thread
	            public void run()
	            {
	                try
	                {
	                    int logoTimer = 0; //Initialize the timer for this activity to load up
	                    while(logoTimer < 4000)
	                    {
	                        sleep(100);
	                        logoTimer = logoTimer +100; //Incrememnt the logo timer by 100ms and increment this timer by that margi
	                    };
	                    //Once Done,Move to the New Activity once you are done with this activity.
	                    //Intent listNotesIntent=new Intent(this,ListNotesActivity.class);
	                    startActivity(new Intent(getApplicationContext(),ListNotesActivity.class));
	                } 
	                catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                 
	                finally{
	                    finish();
	                }
	            }
	        };
	         
	        logoTimer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
