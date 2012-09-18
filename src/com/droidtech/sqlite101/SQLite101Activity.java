package com.droidtech.sqlite101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLite101Activity extends Activity implements OnClickListener {

	Button bAdd, bView, bViewEntry, bUpdate, bDelete;
	TextView tName;
	TextView tNumber;
	EditText eName, eNumber, eRow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bAdd = (Button) findViewById(R.id.button1);
		bView = (Button) findViewById(R.id.button2);
		bViewEntry = (Button) findViewById(R.id.button3);
		bUpdate = (Button) findViewById(R.id.button4);
		bDelete = (Button) findViewById(R.id.button5);
		tName = (TextView) findViewById(R.id.textView1);
		tNumber = (TextView) findViewById(R.id.textView2);
		eName = (EditText) findViewById(R.id.editText1);
		eNumber = (EditText) findViewById(R.id.editText2);
		eRow = (EditText) findViewById(R.id.editText3);

		bAdd.setOnClickListener(this);
		bView.setOnClickListener(this);
		bViewEntry.setOnClickListener(this);
		bUpdate.setOnClickListener(this);
		bDelete.setOnClickListener(this);

		// DatabaseHandler db = new DatabaseHandler(this);
		//
		// db.addContact(new Contact("Ravi", "9100000000"));
		// db.addContact(new Contact("Srinivas", "9199999999"));
		// db.addContact(new Contact("Tommy", "9522222222"));
		// db.addContact(new Contact("Karthik", "9533333333"));
	}

	@Override
	public void onClick(View v) {
		if (v == bAdd) {

			String name = eName.getText().toString();
			String phoneNumber = eNumber.getText().toString();

			DatabaseHandler db = new DatabaseHandler(this);
			db.addContact(new Contact(name, phoneNumber));

			// Clearing the edit view
			eName.setText("");
			eNumber.setText("");
			
//			//Generating toast on entry insertion
//			
//			Toast.makeText(this, name + " " + phoneNumber+ " added ", Toast.LENGTH_SHORT).show();
//			
			
		}

		if (v == bView) {
			// start SQLiteView activity
			Intent intent = new Intent("start.sql.view");
			startActivity(intent);
		}

		if (v == bViewEntry) {
			String s1 = eRow.getText().toString();
			long l = Long.parseLong(s1);

			DatabaseHandler db = new DatabaseHandler(this);
			String returnedName = db.getName(l);
			String returnedNumber = db.getNumber(l);

			eName.setText(returnedName);
			eNumber.setText(returnedNumber);

		}

		if (v == bUpdate) {
			String s1 = eRow.getText().toString();
			String name = eName.getText().toString();
			String phoneNumber = eNumber.getText().toString();
			long l = Long.parseLong(s1);

			DatabaseHandler db = new DatabaseHandler(this);
			db.editEntry(l, name, phoneNumber);

		}

		if (v == bDelete) {
			String s1 = eRow.getText().toString();
			long l = Long.parseLong(s1);

			DatabaseHandler db = new DatabaseHandler(this);
			db.deleteEntry(l);
		}
	}
}