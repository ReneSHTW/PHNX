package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.sharkfw.knowledgeBase.SharkKBException;

import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

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
    private PHNXSharkEngine engine;
    private String name = "";
    private String result2;
	private Context context;
	private Toast toast;
    private TextView welcomeView;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
        try {
            engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
        } catch (PHNXException e) {
            throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
        }
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
        welcomeView = (TextView) findViewById(R.id.textView1);
        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String result = settings.getString("SI", null);

            if (result != null /*&& engine.getPHNXBusinessCard(result) != null*/) {
                try {
                    welcomeView.setText("Welcome " + engine.getPHNXBusinessCard(result).getName().getFirstName() + "!");
                } catch (PHNXException e) {
                    e.printStackTrace();
                } catch (SharkKBException e) {
                    e.printStackTrace();
                }
            }

        result2 = settings.getString("SIOrga", null);


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
			//intent = new Intent(MainMenuActivity.this, MainMapActivity.class);
			//startActivity(intent);
			break;
		case R.id.buttonEditProfile:
			intent = new Intent(MainMenuActivity.this, BusinessCardActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonOrganization:
            if (result2 != null) {
                intent = new Intent(MainMenuActivity.this, ShowOrganizationActivity.class);

            }
            else {
                intent = new Intent(MainMenuActivity.this, CreateOrganizationActivity.class);
            }

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
