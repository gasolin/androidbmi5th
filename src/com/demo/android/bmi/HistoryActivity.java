package com.demo.android.bmi;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HistoryActivity extends ListActivity {
	
	private DB mDbHelper;
	private Cursor mCursor;
	private ActionMode.Callback mCallback;
	private ActionMode mMode;
	
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
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterView.AdapterContextMenuInfo info;
		info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		switch (item.getItemId()) { 
		    case R.id.action_delete:
		        mDbHelper.delete(info.id);
	            fillData();
	            break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.context_history, menu);
//		menu.add(0, 001, 0,  "刪除");
	    menu.setHeaderTitle("要怎麼處理這筆項目？");
	    super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		//Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));
//        registerForContextMenu(getListView());
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setAdapter();
		
		mCallback = new ActionMode.Callback() {
			/** Invoked whenever the action mode is shown. This is invoked immediately after onCreateActionMode */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
 
            /** Called when user exits action mode */
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mMode = null;
            }
 
            /** This is called when the action mode is created. This is called by startActionMode() */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("Demo");
                getMenuInflater().inflate(R.menu.context_history, menu);
                return true;
            }
 
            /** This is called when an item in the context menu is selected */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch(item.getItemId()){
                case R.id.action_delete:
                	int position = getListView().getCheckedItemPosition();
                	mCursor.moveToPosition(position);
                	mDbHelper.delete(mCursor.getLong(0));
    	            fillData();
                    mode.finish();    // exists the action mode
                    break;
                }
                return false;
            }
		};
		
		OnItemLongClickListener listener = new OnItemLongClickListener() {
			 
            @Override
            public boolean onItemLongClick(AdapterView<?> view, View row,
					int position, long id) {
                if(mMode!=null)
                    return false;
                else
                    mMode = startActionMode(mCallback);
                    getListView().setItemChecked(position, true);
                return true;
             }
        };

        getListView().setOnItemLongClickListener(listener);
        
        
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
