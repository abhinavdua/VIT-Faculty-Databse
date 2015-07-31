package com.abhinav.facultydatabasedev;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class FacultyDetails extends Activity {


	Cursor cursor;
	
	//MyDatabase db;
	
	@Override
	public void finish() {
	    super.finish();
	    overridePendingTransition(R.anim.anim3, R.anim.anim4);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clickone);
		getActionBar();
		
		TextView tv1 = (TextView)findViewById(R.id.textView1);
		TextView tv2 = (TextView)findViewById(R.id.textView2);
		TextView tv3 = (TextView)findViewById(R.id.textView3);
		TextView tv4 = (TextView)findViewById(R.id.textView4);
		Intent intent = getIntent();
		String d=intent.getStringExtra("Fname");
		String branch=intent.getStringExtra("branch");
		String str2 = "wrong";
		String str3 = "";
		String str4 = "";
		String str5 = "";
		tv1.setText(d);
		/*Bundle bundle = getIntent().getExtras();*/
		 //   if(bundle != null) {
		   //     tv1.setText(bundle.getString("CABIN"));}
		final MyDatabase db;
		db = new MyDatabase(this);
		cursor = db.getCursor(branch.toLowerCase());
		
		
		 do{
	           
			 	if(cursor.getString(1).contentEquals(d)){
	            str2 =cursor.getString(1); 
	            str3 =cursor.getString(4);
	            str4 =cursor.getString(2);
	            str5 =cursor.getString(3);
	            break;}
	            
			 	
	       //Toast.makeText(this, str1 + str2, Toast.LENGTH_LONG).show();
	    }while(cursor.moveToNext());
		tv1.setText(str2);
		tv2.setText(str3);
		tv3.setText(str4);
		tv4.setText(str5);
		overridePendingTransition(R.anim.anim2, R.anim.anim1);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clickone, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
			
	}
	

