package de.htw.berlin.PHNX.Activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;
//import de.htwberlin.phnx.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessCardActivity extends Activity implements OnClickListener {

	SimpleAdapter simpleAdpt = null;
	private Button showMainMenuBtn;
	private Button editProfileBtn;
	private Intent intent;
	private PHNXSharkEngine engine;
	private TextView nameView;
	private TextView telNumberView;
	private TextView eMailView;
	private Toast toast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_business_card);
		try {
			engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
		} catch (PHNXException e) {
			throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
		}

		showMainMenuBtn = (Button) findViewById(R.id.button1);
		editProfileBtn = (Button) findViewById(R.id.button2);
		nameView = (TextView) findViewById(R.id.textView2);
		telNumberView = (TextView) findViewById(R.id.textView3);
		eMailView = (TextView) findViewById(R.id.textView4);
		showMainMenuBtn.setOnClickListener(this);
		editProfileBtn.setOnClickListener(this);
		initList();
		ListView lv = (ListView) findViewById(R.id.listView1);
		simpleAdpt = new SimpleAdapter(this, planetsList, android.R.layout.simple_list_item_1, new String[] { "planet" }, new int[] { android.R.id.text1 });
		lv.setAdapter(simpleAdpt);
		try {
			PHNXBusinessCard test = engine.getPHNXBusinessCard("bosche@hotmail.de");
			//nameView.setText(test.getName().getPrintableFullName());
			eMailView.setText(test.getContact().getEmailAddress());
			
		} catch (SharkKBException e) {
			errorToast();
			e.printStackTrace();
		} catch (PHNXException e) {
			errorToast();
			e.printStackTrace();
		}
		 catch (IllegalArgumentException e){
				errorToast();
				e.printStackTrace();
		 }
		 catch (NullPointerException e){
				errorToast();
				e.printStackTrace();
		 }
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
			intent = new Intent(BusinessCardActivity.this, MainMenuActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			intent = new Intent(BusinessCardActivity.this, ProfileCreateActivity.class);
			startActivity(intent);
			break;

		}

	}
	
	private void errorToast() {
		toast = Toast.makeText(getApplicationContext(), "Couldn't create the profile!", Toast.LENGTH_LONG);
		toast.show();
	}

}
