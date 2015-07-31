package com.abhinav.facultydatabasedev;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class About extends Activity {
	private TextView tv1;
	private ListView list2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		//tv1 = (TextView) findViewById(R.id.tv);
		//tv1.setText("Developers"+"\n"+"Abhinav Dua"+"\n"+"Vatsal Mahajan"+"\n"+"Himanshu Verma");
		// Show the Up button in the action bar.
		/*list2 = (ListView) findViewById(R.id.listView2);
		List<String> arr_devs = new ArrayList<String>();
        arr_devs.add("Abhinav Dua");
        arr_devs.add("Vatsal Mahajan");
        arr_devs.add("Himanshu Verma");
        
        ArrayAdapter<String> arrayadap = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                arr_devs );

        list2.setAdapter(arrayadap); 
        
        list2.setOnItemClickListener(new OnItemClickListener() 
		{
				public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) 
				{	
					//String name = d.getString(1);
					String name1 = "Developer + UI + Guy behind this android application";
					String name2 = "Developer + UI";
					String name3 = "Database";
					String name = (String) list2.getItemAtPosition(position);
					
					Intent intent2 = new Intent (getApplication() , Display_Dev_Info.class );
					if(name.contentEquals("Abhinav Dua"))
					{
						intent2.putExtra("Fname", name1);
					}
					else if(name.contentEquals("Vatsal Mahajan"))
					{
						intent2.putExtra("Fname", name2);
					}
					else if(name.contentEquals("Himanshu Verma"))
					{
						intent2.putExtra("Fname", name3);
					}
					startActivity(intent2);
			          
			                     
			       			
			                
			       
					}
				
		});*/
        
		setupActionBar();
	}

	
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
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
