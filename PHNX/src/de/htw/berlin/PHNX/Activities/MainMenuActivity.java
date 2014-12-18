package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener
{

	private Button search;
	private Button createEditRessources;
	private Button map;
	private Button editProfile;
	private Button helpButtonOne;
	private Button helpButtonTwo;
	private Button helpButtonThree;
	private Button organizationButton;

	private Context context;
	private Toast toast;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
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
		organizationButton = (Button) findViewById(R.id.buttonOrganization);
		organizationButton.setOnClickListener(this);

		return true;

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.buttonSearch:
			intent = new Intent(MainMenuActivity.this, SearchActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonCreateEditRessources:
			intent = new Intent(MainMenuActivity.this, CreateResourceActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonMap:
			intent = new Intent(MainMenuActivity.this, MainMapActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonEditProfile:
			intent = new Intent(MainMenuActivity.this, BusinessCardActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonOrganization:
			intent = new Intent(MainMenuActivity.this, ShowOrganizationActivity.class);
			startActivity(intent);	
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
