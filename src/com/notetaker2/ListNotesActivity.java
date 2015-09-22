package com.notetaker2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.notetaker2.R.color;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ListNotesActivity extends Activity {
	
	private List<Note> notes=new ArrayList<Note>();
	private ListView notesListView;
	private int editingNoteId=-1; //-1 means we are not currently adding a Note but we are editing a note.

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	
		super.onCreateContextMenu(menu, v, menuInfo);
		//menu.setHeaderTitle("Select Action");
		//menu.add(0,v.getId(),0,"Delete");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}


	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		//ArrayAdapter number=new ArrayAdapter(this, editingNoteId, notes);
		int id=item.getItemId();
		if(id==R.id.deleteItem)
		{
			notes.remove(info.position);
			populateList();
			return true;
		}
		
		return super.onContextItemSelected(item);
		
		
	}



	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        notesListView = (ListView)findViewById(R.id.notesListView);
        
        notesListView.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int itemNumber, long id) {
				//we use the view.getContext because we are inside the Listener and no more inside our activity so we cant use this but the view object to get our current context
				Intent editNoteIntent= new Intent(view.getContext(),EditNoteActivity.class);
				editNoteIntent.putExtra("Note", notes.get(itemNumber));
				editingNoteId=itemNumber;
				startActivityForResult(editNoteIntent,1);
				
			}
        	
        });
        
        registerForContextMenu(notesListView);
		//as soon as the activity loads up it should build a list of notes using the object created and the list of notes
		notes.add(new Note("First Note","Blah Blah",new Date()));
		notes.add(new Note("Second Note","Blah Blah",new Date()));
		notes.add(new Note("Third Note","Blah Blah",new Date()));
		notes.add(new Note("Fourth Note","Blah Blah",new Date()));
		notes.add(new Note("Fifth Note","Blah Blah",new Date()));
		
		populateList();
		
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	RelativeLayout listNoteLayout=(RelativeLayout)findViewById(R.id.notetakerLayout);
    	
        int id = item.getItemId();
        //Add Note Menu Item
        if (id == R.id.add_note) {
        	//when the add note button is clicked, create new Note
        	//notes.add(new Note("Added Note","Meow Meow", new Date()));
        	Intent editNoteIntent=new Intent(this,EditNoteActivity.class);
        	startActivityForResult(editNoteIntent, 1);
            return true;
        }
        
        if(id==R.id.white)
        {
        	listNoteLayout.setBackgroundResource(R.color.white);
        	//EditNoteActivity.editNoteLayout.setBackgroundResource(R.color.white);
        	return true;
        }
        else if(id==R.id.teal)
        {
        	listNoteLayout.setBackgroundResource(R.color.teal);
        	//EditNoteActivity.editNoteLayout.setBackgroundResource(R.color.white);
        	return true;
        }
        else if(id==R.id.silver)
        {
        	listNoteLayout.setBackgroundResource(R.color.silver);
        	//EditNoteActivity.editNoteLayout.setBackgroundResource(R.color.white);
        	return true;
        }
        
        else if(id==R.id.cream_yellow)
        {
        	listNoteLayout.setBackgroundResource(R.color.cream_yellow);
        	EditNoteActivity.editNoteLayout.setBackgroundResource(R.color.cream_yellow);
        	return true;
        }
        
        return super.onOptionsItemSelected(item);
    }


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode==RESULT_CANCELED)
		{
			return;
		}
		//Serializable is a way to inflate and deflate data that is passed through files or databases
		Serializable extra=data.getSerializableExtra("Note");
		if (extra != null) //if extra is not null, it means there is data in the serializable data
		{
			//Now we will cast the serializable data to be of type Note
			Note newNote=(Note)extra;
			
			if(editingNoteId > -1) //-1 is for edits so if its > -1 we are adding a Note
			{
				notes.set(editingNoteId, newNote);
				editingNoteId=-1;
			}
			else
			{
				notes.add(newNote);
			}
			populateList();//redraw all the items in the list
		}
	}


	private void populateList() 
	{
		//create a list of Note Titles
		List<String> noteTitles=new ArrayList<String>();
		
		//now loop through every Note in the notes collection/list and get everyNote object 
		for(Note everyNote:notes)
		{
			noteTitles.add(everyNote.getTitle());
		}
		 
		//after creating the data then create the adapter to bind the list View to the Data created
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noteTitles);
		//after creating the adapter, call the setAdapter on the notesListView
		notesListView.setAdapter(adapter);
	}
}
