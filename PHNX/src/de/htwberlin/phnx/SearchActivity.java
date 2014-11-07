package de.htwberlin.phnx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SearchActivity extends Activity implements OnClickListener {

	private Button findBtn;
	private Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		findBtn = (Button) findViewById(R.id.button1);
		findBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		{
			switch (v.getId()) {
			case R.id.button1:
				intent = new Intent(SearchActivity.this,
						SearchResultActivity.class);
				startActivity(intent);
				break;

			}

		}
	}

}
