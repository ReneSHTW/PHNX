package de.htw.berlin.PHNX.Activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.htw.berlin.PHNX.impl.PHNXEngine;
import de.htw.berlin.PHNX.impl.PHNXException;
//import de.htwberlin.phnx.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class BusinessCardActivity extends Activity implements OnClickListener {

	SimpleAdapter simpleAdpt = null;
	private Button showMainMenuBtn;
	private Button editProfileBtn;
	private Intent intent;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_business_card);
		showMainMenuBtn = (Button) findViewById(R.id.button1);
		editProfileBtn = (Button) findViewById(R.id.button2);
		showMainMenuBtn.setOnClickListener(this);
		editProfileBtn.setOnClickListener(this);
		initList();
		// We get the ListView component from the layout
		ListView lv = (ListView) findViewById(R.id.listView1);

		// This is a simple adapter that accepts as parameter
		// Context
		// Data list
		// The row layout that is used during the row creation
		// The keys used to retrieve the data
		// The View id used to show the data. The key number and the view id
		// must match
		simpleAdpt = new SimpleAdapter(this, planetsList,
				android.R.layout.simple_list_item_1, new String[] { "planet" },
				new int[] { android.R.id.text1 });

		lv.setAdapter(simpleAdpt);
	}

	// The data to show
	List<Map<String, String>> planetsList = new ArrayList<Map<String, String>>();

	private void initList() {
		// We populate the planets

		planetsList.add(createPlanet("planet", "Mercury"));
		planetsList.add(createPlanet("planet", "Venus"));
		planetsList.add(createPlanet("planet", "Mars"));
		planetsList.add(createPlanet("planet", "Jupiter"));
		planetsList.add(createPlanet("planet", "Saturn"));
		planetsList.add(createPlanet("planet", "Uranus"));
		planetsList.add(createPlanet("planet", "Neptune"));
		planetsList.add(createPlanet("planet", "Pluto"));

	}

	private HashMap<String, String> createPlanet(String key, String name) {
		HashMap<String, String> planet = new HashMap<String, String>();
		planet.put(key, name);

		return planet;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			intent = new Intent(BusinessCardActivity.this,
					MainMenuActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(BusinessCardActivity.this,
					ProfileCreateActivity.class);
			startActivity(intent);
			break;

		}

	}

}
