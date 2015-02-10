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

    private ListView listView;
    private Button btn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
        btn = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView1);
        btn.setVisibility(View.INVISIBLE);
        SharedPreferences settings = getSharedPreferences("mysettings",
                Context.MODE_PRIVATE);
        String result = settings.getString("SI", null);

       listView.addView(btn);
        if (result != null) {
            btn.setText(result);
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

        }
    }
}
