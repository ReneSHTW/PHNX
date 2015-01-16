package de.htw.berlin.PHNX.Activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.classes.PHNXContact;
import de.htw.berlin.PHNX.classes.PHNXName;
import de.htw.berlin.PHNX.impl.PHNXBusinessCardImpl;
import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXBusinessCard;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd yyyy");
	private EditText name;
	private EditText middleNames;
	private EditText phoneNumer;
	private EditText eMail;
	private EditText hometown;
	private EditText website;
	private ImageView picture;
	private Toast toast;
	private String picturePath;
	private PHNXBusinessCardImpl businessCard;
	private PHNXContact phnxContact;
	private PHNXName phnxName;
	private PHNXSharkEngine engine;
	private static final int SELECT_PHOTO = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_create_1);
		try {
			engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
		} catch (PHNXException e) {
			throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		name = (EditText) findViewById(R.id.editText1);
		eMail = (EditText) findViewById(R.id.editText8);
		phoneNumer = (EditText) findViewById(R.id.editText2);
		middleNames = (EditText) findViewById(R.id.editText3);
		hometown = (EditText) findViewById(R.id.editText5);
		website = (EditText) findViewById(R.id.editText7);
		picture = (ImageView) findViewById(R.id.imageView1);
		arrival = (Button) findViewById(R.id.button1);
		departure = (Button) findViewById(R.id.button2);
		save = (Button) findViewById(R.id.button3);
		save.setOnClickListener(this);
		picture.setOnClickListener(this);
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
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				dateTime.set(year, monthOfYear, dayOfMonth);
				if (selection == 0) {
					arrival.setText(dateFormatter.format(dateTime.getTime()));
				} else if (selection == 1) {
					departure.setText(dateFormatter.format(dateTime.getTime()));
				}
			}
		}, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH));

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button3:
			if (isEditTextNotEmpty(eMail) && isEditTextNotEmpty(name)) {
				String[] temp = name.getText().toString().split(" ");
				if (isEditTextNotEmpty(middleNames)) {
					phnxName = new PHNXName(temp[0], temp[1], concatenateStringsToIterator(middleNames.getText().toString()));
				} else {
					phnxName = new PHNXName(temp[0], temp[1], null);
				}

				Date arrivalDate = null;
				Date departureDate = null;
				try {
					arrivalDate = dateFormatter.parse(arrival.getText().toString());
				} catch (ParseException e) {
					toast = Toast.makeText(getApplicationContext(), "Parsing Error!", Toast.LENGTH_LONG);
					toast.show();
				}
				try {
					departureDate = dateFormatter.parse(arrival.getText().toString());

				} catch (ParseException e) {
					toast = Toast.makeText(getApplicationContext(), "Parsing Error", Toast.LENGTH_LONG);
					toast.show();
				}
				phnxContact = new PHNXContact(eMail.getText().toString(), null, null, null, null);

				try {
					engine.createPHNXBusinessCard(phnxName, phnxContact, null, null, arrivalDate, departureDate, null);
					PHNXBusinessCard test = engine.getPHNXBusinessCard(phnxContact.getEmailAddress());
					toast = Toast.makeText(getApplicationContext(), "ARRIVAL: " + test.getArrival(), Toast.LENGTH_LONG);
					toast.show();
					successToast();
				} catch (SharkKBException e) {
					errorToast();

				} catch (PHNXException e) {
					errorToast();
				} catch (ParseException e) {
					errorToast();

				}

				break;

			} else {
				missingParamsToast();
			}
		case R.id.imageView1: {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, SELECT_PHOTO);
		}
			
		default: {
			break;
		}

		}
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = imageReturnedIntent.getData();
	            picture.setImageURI(selectedImage);
	            picturePath = picture.toString();
	            
	        }
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

	private boolean isEditTextNotEmpty(EditText etText) {
		return (!(etText.getText().toString().matches("")));
	}

	private void missingParamsToast() {
		toast = Toast.makeText(getApplicationContext(), "At least one required parameter is missing!", Toast.LENGTH_LONG);
		toast.show();
	}

	private void errorToast() {
		toast = Toast.makeText(getApplicationContext(), "Couldn't create the profile!", Toast.LENGTH_LONG);
		toast.show();
	}

	private void successToast() {
		toast = Toast.makeText(getApplicationContext(), "Profile created!", Toast.LENGTH_LONG);
		toast.show();
	}

}
