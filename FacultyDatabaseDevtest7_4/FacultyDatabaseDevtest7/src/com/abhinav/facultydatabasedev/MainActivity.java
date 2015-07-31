package com.abhinav.facultydatabasedev;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity{

	
	String c;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.school_sel, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter1);
		
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
			.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
	

	
	
	public void sendmessage(View view)
	{
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		c=spinner.getSelectedItem().toString();
		if(c.contentEquals("School of Computer Science and Engineering"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "scse");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("School of Information Technology and Engineering"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "site");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("School of Electronics Engineering"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "sense");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("School of Bio-Sciences and Technology"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "sbst");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("School of Mechanical and Building Sciences"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "smbs");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("School of Electrical Engineering"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "sel");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("School of Social Sciences and Languages"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "sssl");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		if(c.contentEquals("VIT Business School"))
		{
		Intent intent= new Intent(this,FacultyList.class);
		intent.putExtra("branch", "vitbs");
		startActivity(intent);
		overridePendingTransition(R.anim.anim1, R.anim.anim2);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		
		inflater.inflate(R.menu.action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			Intent intent1 = new Intent(this , About.class);
			startActivity(intent1);
			break;
		
	}
		return super.onOptionsItemSelected(item);
	
	}	}

