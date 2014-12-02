package de.htw.berlin.PHNX.Activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXBusinessCardImpl;
import de.htw.berlin.PHNX.impl.PHNXEngine;
import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXNameImpl;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileCreateActivity extends Activity implements OnClickListener {

	private Button arrival;
	private Button departure;
	private Button save;
	private int selection;
	private Calendar dateTime = Calendar.getInstance();
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"MMMM dd yyyy");
	private PHNXEngine engine;
	private EditText name;
	private EditText middleNames;
	private EditText phoneNumer;
	private EditText eMail;
	private EditText hometown;
	private EditText website;
	private ImageView picture;
	private Toast toast;
	private PHNXBusinessCardImpl businessCard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_create_1);
		try {
			engine = PHNXEngine.getPHNXEngine();
		} catch (PHNXException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		name = (EditText) findViewById(R.id.editText1);
		middleNames = (EditText) findViewById(R.id.editText8);
		phoneNumer = (EditText) findViewById(R.id.editText2);
		eMail = (EditText) findViewById(R.id.editText3);
		hometown = (EditText) findViewById(R.id.editText5);
		website = (EditText) findViewById(R.id.editText7);
		picture = (ImageView) findViewById(R.id.imageView1);
		arrival = (Button) findViewById(R.id.button1);
		departure = (Button) findViewById(R.id.button2);
		save = (Button) findViewById(R.id.button3);
		save.setOnClickListener(this);
		// arrival.setText(dateFormatter.format(dateTime.getTime()));
		// departure.setText(dateFormatter.format(dateTime.getTime()));
		// Bla
		arrival.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				selection = 0;
				extracted();
			}

			private void extracted() {
				showDialog(1);
			}

		});
		departure.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				selection = 1;
				extracted();
			}

			private void extracted() {
				showDialog(1);
			}

		});
		return true;

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		return new DatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				dateTime.set(year, monthOfYear, dayOfMonth);
				if (selection == 0) {
					arrival.setText(dateFormatter.format(dateTime.getTime()));
				} else if (selection == 1) {
					departure.setText(dateFormatter.format(dateTime.getTime()));
				}
			}
		}, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime
				.get(Calendar.DAY_OF_MONTH));

	}

	@Override
	public void onClick(View v) {
		boolean cardCreated = false;
		switch (v.getId()) {
		case R.id.button3:
			String[] temp = name.getText().toString().split(" ");
			PHNXNameImpl phnxName = null;
			try {
				phnxName = new PHNXNameImpl(temp[0], temp[1],
						concatenateStringsToIterator(middleNames.getText()
								.toString()));
			} catch (ArrayIndexOutOfBoundsException aie) {
			}
			Date arrivalDate = null;
			Date departureDate = null;
			try {
				arrivalDate = dateFormatter.parse(arrival.getText().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				departureDate = dateFormatter.parse(arrival.getText()
						.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				businessCard = new PHNXBusinessCardImpl(phnxName, null, null,
						null, null, null, arrivalDate, departureDate, null);
				cardCreated = true;
			} catch (IllegalArgumentException iae) {
			}
			try {
				engine.setBusinessCard(businessCard);
				toast = Toast.makeText(getApplicationContext(),
						"Business Card wurde erstellt", Toast.LENGTH_LONG);
				toast.show();
			} catch (SharkKBException e) {
			} catch (PHNXException e) {
							}
			catch (IllegalArgumentException e) {
				toast = Toast.makeText(	getApplicationContext(),
								"Mindestens eine der Pflichtangaben fehlt oder ist fehlerhaft",
								Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		default:
			break;
		}

	}

	private Iterator<String> concatenateStringsToIterator(String strings) {
		String[] temp = strings.split(" ");
		ArrayList<String> tempList = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			tempList.add(temp[i]);
		}
		return tempList.iterator();
	}

}
