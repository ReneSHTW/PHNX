package de.htw.berlin.PHNX.Activities;

import net.sharkfw.knowledgeBase.SharkKBException;
import de.htw.berlin.PHNX.impl.PHNXEngine;
import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;
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
	private PHNXSharkEngine engine;

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
			engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
		} catch (PHNXException e) {
			throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
		}

		return true;
	}

	@Override
	public void onClick(View v) {
		String amountString = null;
		String contactString = null;
		switch (v.getId()) {
		case R.id.button1: {
			if (isEditTextNotEmpty(resourceNameEdit)
					&& isEditTextNotEmpty(resourceTypeEdit)
					&& isEditTextNotEmpty(ownerEdit)) {
				if (isEditTextNotEmpty(contact)
						&& isEditTextNotEmpty(resourceAmount)) {
					contactString = contact.getText().toString();
					amountString = resourceAmount.getText().toString();
				} else if (isEditTextNotEmpty(contact)) {
					contactString = contact.getText().toString();
				} else if (isEditTextNotEmpty(resourceAmount)) {
					amountString = resourceAmount.getText().toString();
				}
				try {
					engine.createPHNXResource(resourceTypeEdit.getText()
							.toString(), resourceNameEdit.getText().toString(),
							ownerEdit.getText().toString(), contactString,
							amountString, null);
				} catch (SharkKBException e) {
					errorToast();
				} catch (PHNXException e) {
					errorToast();
				}
			} else {
				missingParamsToast();
			}
		}
		toast = Toast.makeText(getApplicationContext(),
				"Resource created!",
				Toast.LENGTH_LONG);
		toast.show();
			break;
		}
	}

	private boolean isEditTextNotEmpty(EditText etText) {
		return (!(etText.getText().toString().matches("")));
	}

	private void missingParamsToast() {
		toast = Toast.makeText(getApplicationContext(),
				"At least one required parameter is missing!",
				Toast.LENGTH_LONG);
		toast.show();
	}

	private void errorToast() {
		toast = Toast.makeText(getApplicationContext(),
				"Couldn't create the resource!", Toast.LENGTH_LONG);
		toast.show();
	}

}
