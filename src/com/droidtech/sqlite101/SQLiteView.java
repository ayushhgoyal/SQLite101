package com.droidtech.sqlite101;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLiteView extends Activity {

	TextView tvData;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteviewlayout);
		tvData = (TextView) findViewById(R.id.textView1);

		DatabaseHandler db = new DatabaseHandler(this);
		String allData = db.getContacts();

		tvData.setText(allData);
		
	}

}
