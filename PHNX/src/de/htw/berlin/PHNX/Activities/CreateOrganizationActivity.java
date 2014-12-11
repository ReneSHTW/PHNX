package de.htw.berlin.PHNX.Activities;

import de.htw.berlin.PHNX.impl.PHNXEngine;
import de.htw.berlin.PHNX.impl.PHNXException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateOrganizationActivity extends Activity implements
		OnClickListener {

	private EditText nameOrganization;
	private EditText contactPerson;
	private EditText urlAdress;
	private ImageButton picture;
	private Button createButton;
	private Toast toast;
	private PHNXEngine engine;

	// Location of oranization

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.organization_create);
		nameOrganization = (EditText) findViewById(R.id.editText1);
		contactPerson = (EditText) findViewById(R.id.editText2);
		urlAdress = (EditText) findViewById(R.id.editText4);
		createButton = (Button) findViewById(R.id.button1);
		createButton.setOnClickListener(this);
		try {
			engine = PHNXEngine.getPHNXEngine();
		} catch (PHNXException e) {

			throw new IllegalStateException(
					"Couldn't retrieve the PHNX Engine");
		}
		// Location
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1: {
			if (isEditTextNotEmpty(nameOrganization)
					&& isEditTextNotEmpty(urlAdress)) {
				// engine.createOranization()
			} else {
				toast = Toast
						.makeText(
								getApplicationContext(),
								"Mindestens eine der Pflichtangaben fehlt oder ist fehlerhaft",
								Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		}
		}

	}

	private boolean isEditTextNotEmpty(EditText etText) {
		return (!(etText.getText().toString().matches("")));
	}

}
