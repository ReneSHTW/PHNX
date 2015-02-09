package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import net.sharkfw.knowledgeBase.SharkKBException;

import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class MainActivity extends Activity {


   // public static final String PREFS_NAME = "MyPrefsFile";
    private PHNXSharkEngine engine;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_use_welcome_screen);
       /* try {
            engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
        } catch (PHNXException e) {
            throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
        }*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Button button2 = (Button) findViewById(R.id.button2);
        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String result = settings.getString("SI", null);
        try {
            if (result != null /*&& engine.getPHNXBusinessCard(result) != null*/) {
                Intent intent = new Intent(MainActivity.this,
                        MainMenuActivity.class);
                startActivity(intent);
            }
        }/* catch (PHNXException e) {
            e.printStackTrace();
        } catch (SharkKBException e) {
            e.printStackTrace();
        }*/
          catch (IllegalArgumentException e) {
              e.printStackTrace();
          }
        button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ProfileCreateActivity.class);
				startActivity(intent);
			}

		});

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
