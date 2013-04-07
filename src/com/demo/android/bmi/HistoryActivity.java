package com.demo.android.bmi;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HistoryActivity extends ListActivity {
	
	private DB mDbHelper;
	private Cursor mCursor;
	
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
		mDbHelper = new DB(this);
	    mDbHelper.open();
	    
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//			    android.R.layout.simple_list_item_1,
//			    records);
//		setListAdapter(adapter);

//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//				R.array.records,
//				android.R.layout.simple_list_item_1);
//		setListAdapter(adapter);

//		setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                records));
	    
	    fillData();
	}

	private void fillData() {
	    mCursor = mDbHelper.getAll();
	    startManagingCursor(mCursor);
		
	    String[] from_column = new String[]{DB.KEY_ITEM, DB.KEY_CREATED};
	    int[] to_layout = new int[]{R.id.text1, R.id.text2};

	    // Now create a simple cursor adapter
//	    SimpleCursorAdapter adapter =
//	                new SimpleCursorAdapter(this, R.layout.list_row,
//	                		mCursor, from_column, to_layout);
	    
	    // Now create list with cursor
	    ListCursorAdapter adapter =
                new ListCursorAdapter(this, mCursor);
	    
	    setListAdapter(adapter);
	    
	    getListView().setOnItemClickListener(new OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//	    		Cursor c = ((SimpleCursorAdapter)getListView().getAdapter()).getCursor();
//	    		c.moveToPosition(position);
//	    		mDbHelper.delete(c.getLong(0));

	    		mDbHelper.delete(id);
	    	    fillData();
	    	  }
	    	});
    }
	
	public class ListCursorAdapter extends CursorAdapter {
		public ListCursorAdapter(Context context, Cursor c) {
			super(context, c);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// TODO Auto-generated method stub
			TextView text1 = (TextView)view.findViewById(R.id.text1);
			TextView text2 = (TextView)view.findViewById(R.id.text2);
			text1.setText(cursor.getString(
					cursor.getColumnIndex(DB.KEY_ITEM)));
			text2.setText(cursor.getString(
					cursor.getColumnIndex(DB.KEY_CREATED)));
		}
		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.list_row, parent, false);
			bindView(v, context, cursor);
			return v;
		}
	
	}
	
}
