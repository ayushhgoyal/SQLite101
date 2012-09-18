package com.droidtech.sqlite101;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "contactsManager";
	private static final String TABLE_CONTACTS = "contacts";

	// Now, column names

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "names";
	private static final String KEY_NUMBER = "number";

    

	private DatabaseHandler handler;
	private final Context myContext;
	private SQLiteDatabase myDatabase;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);												
		myContext = context;
	}

	// public DatabaseHandler (Context c){
	// myContext = c;
	// }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
				+ " TEXT, " + KEY_NUMBER + " TEXT);";
		db.execSQL(CREATE_CONTACTS_TABLE); 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
		// Cursor c ;
		// c.getString(c.getColumnIndex(KEY_ID));
		onCreate(db);
	}

	// adding new contact
	public void addContact(Contact contact) {
		try {
			// SQLiteDatabase db = this.getWritableDatabase();
			handler = new DatabaseHandler(myContext);
			myDatabase = handler.getWritableDatabase();

			ContentValues cv = new ContentValues();
			cv.put(KEY_NAME, contact.getName());
			cv.put(KEY_NUMBER, contact.getPhoneNumber());

			myDatabase.insert(TABLE_CONTACTS, null, cv);

			handler.close();
			// Toast.makeText(myContext, "It worked",
			// Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			// Toast.makeText(myContext, "It didn't work",
			// Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	} 

	public String getContacts() {
		handler = new DatabaseHandler(myContext);
		myDatabase = handler.getReadableDatabase();
		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_NUMBER };
		Cursor c = myDatabase.query(TABLE_CONTACTS, columns, null, null, null,
				null, null);
		String result = "";

		int iRow = c.getColumnIndex(KEY_ID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iNumber = c.getColumnIndex(KEY_NUMBER);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName)
					+ " \t" + c.getString(iNumber) + "\n";
		}
		
		
		handler.close();

		return result;

	}

	public String getName(long l) {
		handler = new DatabaseHandler(myContext);
		myDatabase = handler.getReadableDatabase();

		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_NUMBER };
		Cursor c = myDatabase.query(TABLE_CONTACTS, columns,
				KEY_ID + " = " + l, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		handler.close();
		return null;
	}

	public String getNumber(long l) {
		handler = new DatabaseHandler(myContext);
		myDatabase = handler.getReadableDatabase();

		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_NUMBER };
		Cursor c = myDatabase.query(TABLE_CONTACTS, columns,
				KEY_ID + " = " + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String number = c.getString(2);
			return number;
		}
		handler.close();
		return null;
	}

	public void editEntry(long l, String name, String phoneNumber) {
		handler = new DatabaseHandler(myContext);
		myDatabase = handler.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_NUMBER, phoneNumber);

		myDatabase.update(TABLE_CONTACTS, cv, KEY_ID + "=" + l, null);
		handler.close();

	}

	public void deleteEntry(long l) {
		// TODO Auto-generated method stub
		handler = new DatabaseHandler(myContext);
		myDatabase = handler.getWritableDatabase();
		myDatabase.delete(TABLE_CONTACTS, KEY_ID + "=" + l, null);
		//myDatabase.close();
		handler.close();
	}

	// public void deleteEntry(long l) {
	// myDatabase.delete(TABLE_CONTACTS, KEY_ID + "=" + l, null);
	// }

}
