package de.htwberlin.phnx;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class MainMenuActivity extends Activity {
    
    private Button arrival;
    private Button departure;
    

private Calendar dateTime = Calendar.getInstance();
private SimpleDateFormat dateFormatter = new SimpleDateFormat(
        "MMMM dd yyyy");



             

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_create_1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		arrival = (Button) findViewById(R.id.button1);
		departure = (Button) findViewById(R.id.button2);
		 arrival.setText(dateFormatter.format(dateTime.getTime())); 
		 departure.setText(dateFormatter.format(dateTime.getTime())); 
			arrival.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					extracted();
				}

				private void extracted() {
					showDialog(1);
				}

			});
		return true;

	}
	
	@Override
    protected Dialog onCreateDialog(int id)
    {
        
        
        
            return new DatePickerDialog(this, new OnDateSetListener()
            {

                @Override
                public void onDateSet(DatePicker view, int year,
                        int monthOfYear, int dayOfMonth)
                {
                    dateTime.set(year, monthOfYear, dayOfMonth);
                    arrival.setText(dateFormatter.format(dateTime.getTime()));
                    departure.setText(dateFormatter.format(dateTime.getTime()));
                }
            }, dateTime.get(Calendar.YEAR),
               dateTime.get(Calendar.MONTH),
               dateTime.get(Calendar.DAY_OF_MONTH));

        
        
    }


}

