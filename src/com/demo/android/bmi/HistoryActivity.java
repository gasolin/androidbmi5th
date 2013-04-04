package com.demo.android.bmi;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class HistoryActivity extends ListActivity {
	
	static final String[] records = new String[] {
	    /*"20",
	    "21",
	    "22",
	    "24",
	    "23",
	    "22",
	    "20"*/
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		//Tell the list view which view to display when the list is empty
                getListView().setEmptyView(findViewById(R.id.empty));
		setAdapter();
	}
	
	private void setAdapter() {
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//			    android.R.layout.simple_list_item_1,
//			    records);
//		setListAdapter(adapter);

//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//				R.array.records,
//				android.R.layout.simple_list_item_1);
//		setListAdapter(adapter);

		setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                records));
    }
}
