package de.htw.berlin.PHNX.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class SearchActivity extends Activity implements OnClickListener {

	private Button findBtn;
	private Intent intent;
    private CheckBox personBox;
    private CheckBox orgaBox;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
        personBox = (CheckBox) findViewById(R.id.radioButton1);
        orgaBox = (CheckBox) findViewById(R.id.radioButton2);
		findBtn = (Button) findViewById(R.id.button1);
		findBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		{
			switch (v.getId()) {
			case R.id.button1:
                SharedPreferences settings = getSharedPreferences("mysettings",
                        Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("personSearch", personBox.isChecked());
                editor.putBoolean("orgaSearch", orgaBox.isChecked());
                editor.commit();
				intent = new Intent(SearchActivity.this,
						SearchResultActivity.class);
				startActivity(intent);
				break;

			}

		}
	}

}
