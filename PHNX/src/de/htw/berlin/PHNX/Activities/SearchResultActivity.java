package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SearchResultActivity extends Activity implements View.OnClickListener {


    private Button personBtn;
    private Button organBtn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
        personBtn = (Button) findViewById(R.id.button);
        organBtn = (Button) findViewById(R.id.button5);
        personBtn.setOnClickListener(this);
        organBtn.setOnClickListener(this);
        personBtn.setVisibility(View.INVISIBLE);
        organBtn.setVisibility(View.INVISIBLE);
        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String result = settings.getString("SI", null);
        String result2 = settings.getString("SIOrga", null);

        boolean personS = settings.getBoolean("personSearch", false);
        boolean orgaS = settings.getBoolean("orgaSearch", false);
        if (result != null && personS) {
            personBtn.setText(result);
            personBtn.setVisibility(View.VISIBLE);
        }
        if (result2!= null && orgaS) {
            organBtn.setText(result2);
            organBtn.setVisibility(View.VISIBLE);
        }


	}


    @Override
    public void onClick(View v) {
        {
            switch (v.getId()) {
                case R.id.button:
                    Intent intent = new Intent(SearchResultActivity.this,
                            BusinessCardActivity.class);
                    startActivity(intent);
                    break;

            }
            switch (v.getId()) {
                case R.id.button5:
                    Intent intent = new Intent(SearchResultActivity.this,
                            ShowOrganizationActivity.class);
                    startActivity(intent);
                    break;

            }

        }
    }
}
