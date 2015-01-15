package de.htw.berlin.PHNX.Activities;

import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateOrganizationActivity extends Activity implements OnClickListener {

	private EditText nameOrganization;
	private EditText contactPerson;
	private EditText urlAdress;
	private ImageButton picture;
	private Button createButton;
	private Toast toast;
	private PHNXSharkEngine engine;
	private String imagePath;
	private static final int SELECT_PHOTO = 100;

	// Location of oranization

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.organization_create);
		nameOrganization = (EditText) findViewById(R.id.editText1);
		contactPerson = (EditText) findViewById(R.id.editText2);
		urlAdress = (EditText) findViewById(R.id.editText4);
		createButton = (Button) findViewById(R.id.button1);
		picture = (ImageButton) findViewById(R.id.imageButton1);
		createButton.setOnClickListener(this);
		picture.setOnClickListener(this);
		try {
			engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
		} catch (PHNXException e) {

			throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
		}
		// Location
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1: {
			if (isEditTextNotEmpty(nameOrganization) && isEditTextNotEmpty(urlAdress)) {
				// engine.createOranization()
			} else {
				toast = Toast.makeText(getApplicationContext(), "Mindestens eine der Pflichtangaben fehlt oder ist fehlerhaft", Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		}
		case R.id.imageButton1: {
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			break;
		}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		switch (requestCode) {
		case SELECT_PHOTO:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = imageReturnedIntent.getData();
				picture.setImageURI(selectedImage);
				imagePath = picture.toString();

			}
		}
	}

	private boolean isEditTextNotEmpty(EditText etText) {
		return (!(etText.getText().toString().matches("")));
	}

}
