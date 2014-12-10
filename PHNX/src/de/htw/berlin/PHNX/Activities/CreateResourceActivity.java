package de.htw.berlin.PHNX.Activities;

import de.htw.berlin.PHNX.impl.PHNXEngine;
import de.htw.berlin.PHNX.impl.PHNXException;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateResourceActivity extends Activity implements OnClickListener {

	private EditText resourceNameEdit;
	private EditText resourceTypeEdit;
	private EditText ownerEdit;
	private EditText resourceAmount;
	private EditText contact;
	private ImageButton image;
	private Button createBtn;
	private Toast toast;
	private PHNXEngine engine;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_resource);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		resourceNameEdit = (EditText) findViewById(R.id.editText1);
		resourceTypeEdit = (EditText) findViewById(R.id.editText2);
		ownerEdit = (EditText) findViewById(R.id.editText3);
		resourceAmount = (EditText) findViewById(R.id.editText4);
		contact = (EditText) findViewById(R.id.editText5);
		image = (ImageButton) findViewById(R.id.imageButton1);
		createBtn = (Button) findViewById(R.id.button1);
		createBtn.setOnClickListener(this);
		try {
			engine = PHNXEngine.getPHNXEngine();
		} catch (PHNXException e) {

		}

		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1: {
			if (isEditTextNotEmpty(resourceNameEdit)
					&& isEditTextNotEmpty(resourceTypeEdit)
					&& isEditTextNotEmpty(ownerEdit)) {
				// engine.createPHNXResource(params)

			} else {
				toast = Toast
						.makeText(
								getApplicationContext(),
								"Mindestens eine der Pflichtangaben fehlt oder ist fehlerhaft",
								Toast.LENGTH_LONG);
				toast.show();
			}
		}
			break;
		}
	}

	private boolean isEditTextNotEmpty(EditText etText) {
		return (!(etText.getText().toString().matches("")));
	}

}
