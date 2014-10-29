package de.htwberlin.phnx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener {

	private Button search;
	private Button createEditRessources;
	private Button map;
	private Button editProfile;
	private Button helpButtonOne;
	private Button helpButtonTwo;
	private Button helpButtonThree;

	Context context;
	private Toast toast;
	
	//Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		context = getApplicationContext();
		search = (Button) findViewById(R.id.buttonSearch);
		search.setOnClickListener(this);
		createEditRessources = (Button) findViewById(R.id.buttonCreateEditRessources);
		createEditRessources.setOnClickListener(this);
		map = (Button) findViewById(R.id.buttonMap);
		map.setOnClickListener(this);
		editProfile = (Button) findViewById(R.id.buttonEditProfile);
		editProfile.setOnClickListener(this);
		helpButtonOne = (Button) findViewById(R.id.buttonHelpOne);
		helpButtonOne.setOnClickListener(this);
		helpButtonTwo = (Button) findViewById(R.id.buttonHelpTwo);
		helpButtonTwo.setOnClickListener(this);
		helpButtonThree = (Button) findViewById(R.id.buttonHelpThree);
		helpButtonThree.setOnClickListener(this);

		return true;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSearch:
			// intent = new Intent(MainMenuActivity.this,
			// SearchActivity.class);
			// startActivity(intent);
			//context = getApplicationContext();
			toast = Toast.makeText(context, "Hier wird dann die Search Activity aufgerufen", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.buttonCreateEditRessources:
			// intent = new Intent(MainMenuActivity.this,
			// CreateEditRessourcesActivity.class);
			// startActivity(intent);
			toast = Toast.makeText(context, "Hier wird dann die Create and Editd Ressources Activity aufgerufen", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.buttonMap:
			// intent = new Intent(MainMenuActivity.this,
			// MapActivity.class);
			// startActivity(intent);
			toast = Toast.makeText(context, "Hier wird dann die Map Activity aufgerufen", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.buttonEditProfile:
			Intent intent = new Intent(MainMenuActivity.this, ProfileCreateActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonHelpOne:
			toast = Toast.makeText(context, "Hilfe Text Eins", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.buttonHelpTwo:
			toast = Toast.makeText(context, "Hilfe Text Zwei", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.buttonHelpThree:
			toast = Toast.makeText(context, "Hilfe Text Drei", Toast.LENGTH_SHORT);
			toast.show();
			break;
		}
	}
}
