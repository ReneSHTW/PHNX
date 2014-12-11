package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShowOrganizationActivity extends Activity implements OnClickListener {
	
	private Button editOrganization;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_organization_card);
		editOrganization = (Button) findViewById(R.id.button5);
		editOrganization.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button5: {
			intent = new Intent(ShowOrganizationActivity.this, CreateOrganizationActivity.class);
			startActivity(intent);
			break;
		}
		
		
		
		}
		
	}
	
	
}
