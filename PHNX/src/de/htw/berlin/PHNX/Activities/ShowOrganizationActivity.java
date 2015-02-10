package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import net.sharkfw.knowledgeBase.SharkKBException;

import de.htw.berlin.PHNX.impl.PHNXException;
import de.htw.berlin.PHNX.impl.PHNXOrganizationImpl;
import de.htw.berlin.PHNX.impl.PHNXSharkEngineImpl;
import de.htw.berlin.PHNX.interfaces.PHNXOrganization;
import de.htw.berlin.PHNX.interfaces.PHNXSharkEngine;

public class ShowOrganizationActivity extends Activity implements OnClickListener {
	
	private Button editOrganization;
	private Intent intent;
    private TextView orgaName;
    private Button contactBtn;
    private PHNXSharkEngine engine;
    private PHNXOrganization orga = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_organization_card);
		//editOrganization = (Button) findViewById(R.id.button5);
		//editOrganization.setOnClickListener(this);
        orgaName = (TextView) findViewById(R.id.textView1);
        contactBtn = (Button) findViewById(R.id.button3);
        try {
            engine = PHNXSharkEngineImpl.getPHNXSharkEngine();
        } catch (PHNXException e) {
            throw new IllegalStateException("Couldn't retrieve the PHNX Engine");
        }
        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String result = settings.getString("SIOrga", null);
        if (result != null) {
            try {
                orga = engine.getPHNXOrganization(result);
            } catch (SharkKBException e) {
                e.printStackTrace();
            }
        }
        if (orga != null) {
            orgaName.setText(orga.getName());
            contactBtn.setText(orga.getContactPersonEmailAddress());
        }
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*case R.id.button5: {
			intent = new Intent(ShowOrganizationActivity.this, CreateOrganizationActivity.class);
			startActivity(intent);
			break;
		}*/
		
		
		
		}
		
	}
	
	
}
