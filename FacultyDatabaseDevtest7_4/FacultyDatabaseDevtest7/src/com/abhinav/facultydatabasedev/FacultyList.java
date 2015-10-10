package com.abhinav.facultydatabasedev;

import java.util.Arrays;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;

public class FacultyList extends Activity implements SearchView.OnQueryTextListener{
	SearchView mSearchView;
	ListView list1;
	Filter f;
	//ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display__spinner__info);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Cursor employees;
		//Cursor hello;
		
		final MyDatabase db;
		
		Intent intent = getIntent();
		final String m=intent.getStringExtra("branch");

		db = new MyDatabase(this);
		employees = db.getCursor(m.toLowerCase());
		int n = db.getCnt(m.toLowerCase());


		
		
		list1 = (ListView) findViewById(R.id.listView1);
		String s[]=new String[n];
		int i=0;
		do{
			
			String s2 = employees.getString(1).toLowerCase();

			final StringBuilder result = new StringBuilder(s2.length());
			String[] words = s2.split("\\s");
			for(int i2=0,l=words.length;i2<l;++i2) {
			  if(i2>0) result.append(" ");      
			  result.append(Character.toUpperCase(words[i2].charAt(0)))
			        .append(words[i2].substring(1));

			}
			
			System.out.println(result);
			s[i]=result.toString();
			
			i++;
			
		}while(employees.moveToNext());
		
		
		
		
		
		Arrays.sort(s);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
		list1.setAdapter(adapter);
		f = adapter.getFilter();
		
		list1.setFastScrollEnabled(true);
		list1.isSmoothScrollbarEnabled();
		list1.setTextFilterEnabled(true);
		list1.setOnItemClickListener(new OnItemClickListener()
		{
				public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) 
				{
					
					 String name = (String) list1.getItemAtPosition(position);
					
					
								Intent intent1 = new Intent (getApplication() , FacultyDetails.class );
								intent1.putExtra("Fname", name.toUpperCase());
								intent1.putExtra("branch", m);
			                startActivity(intent1);
			                overridePendingTransition(R.anim.anim1, R.anim.anim2);
			                     
			       			
			                
			       
					}
				
		});
		overridePendingTransition(R.anim.anim2, R.anim.anim1);
	}
	
	public int getScrollPosition(int childposition, int groupposition) {
	    return childposition;
	}
	
	public String getIndicatorForPosition(int childposition, int groupposition) {
	    return null;
	}
	

	
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		ActionBar s = getActionBar();
		//s.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2C3E50")));

	}
	@Override
	public void finish() {
	    super.finish();
	    overridePendingTransition(R.anim.anim3, R.anim.anim4);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display__spinner__info, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        mSearchView.setFocusable(true);
        
        setupSearchView();
 
        return super.onCreateOptionsMenu(menu);
	}

	
	 private void setupSearchView() {
	        mSearchView.setIconifiedByDefault(false);
	        mSearchView.setOnQueryTextListener(this);
	         
	        mSearchView.setQueryHint("Search Here");
	    }

	    public boolean onQueryTextChange(String newText) {
	        if (TextUtils.isEmpty(newText)) {
	        	
	        	f.filter(null);
	        } else {
	        	f.filter(newText);
	        }
	        return true;
	    }

	    public boolean onQueryTextSubmit(String query) {
	        return false;
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



