package de.htw.berlin.PHNX.Activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessCardActivity extends Activity implements OnClickListener {


	private Button showMainMenuBtn;
	private Button editProfileBtn;
	private Intent intent;
	private PHNXSharkEngine engine;
	private TextView nameView;
	private TextView telNumberView;
	private TextView eMailView;
    private TextView professionView;
    private TextView arrivalView;
    private TextView departureView;
    private TextView orgaView;
	private Toast toast;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd yyyy");

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
        professionView = (TextView) findViewById(R.id.textView10);
        arrivalView = (TextView) findViewById(R.id.textView13);
        departureView = (TextView) findViewById(R.id.textView15);
        orgaView = (TextView) findViewById(R.id.textView6);
		showMainMenuBtn.setOnClickListener(this);
		editProfileBtn.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String result = settings.getString("SI", null);
        String result2 = settings.getString("SIOrga", null);
        toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
        toast.show();

		try {
			PHNXBusinessCard test = engine.getPHNXBusinessCard(result);
			nameView.setText(test.getName().getPrintableFullName());
			eMailView.setText(test.getContact().getEmailAddress());
			telNumberView.setText(test.getContact().getMobileNumber());
            professionView.setText(test.getPrintableProfessionalDegree());
           /* SimpleDateFormat sdfDestination = new SimpleDateFormat(
                    "dd/MM/yyyy, ha");
            Date date = sdfSource.parse(test.getArrival().toString());*/
            arrivalView.setText(test.getArrival().toString());
            departureView.setText(test.getDeparture().toString());

			
		} catch (SharkKBException e) {
			errorToast();
			e.printStackTrace();
		} catch (PHNXException e) {
			errorToast();
			e.printStackTrace();
		}
		 catch (IllegalArgumentException e){
				//errorToast();
				e.printStackTrace();
		 }
		 catch (NullPointerException e){
				//errorToast();
				e.printStackTrace();
		 }
         if (result2 != null)  {
            orgaView.setText(result2);
        }

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
