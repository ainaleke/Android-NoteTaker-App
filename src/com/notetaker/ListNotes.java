package com.notetaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListNotes extends Activity 
{
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) //this is the data coming back
	{
		Serializable extra=data.getSerializableExtra("Note");//The Data is currently in serializable format so we have to Get the Data and cast it to NOte
		if (extra !=null) //If there is Serializable data, we need to cast it to Note 
		{
			Notes newNote=(Notes)extra; //Convert the serializable data to type Note
			//Add the Note to the List of Notes
			notes.add(newNote);
			populateList();
		}
	}

	//create an arrayList of type Notes, which will contain an object notes which would hold different notes in that array List
	private List<Notes> notes=new ArrayList<Notes>(); //Notes is a class or in itself an Object-using generics
	private ListView noteListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_notes);
		noteListView = (ListView)findViewById(R.id.notesView); //on screen load,  grab the ListView by finding its id, and then assign it to the object noteListView
		
		noteListView.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,int itemNumber, long id) 
			{
				Intent editNoteIntent = new Intent(view.getContext(),EditNoteActivity.class);
				editNoteIntent.putExtra("Note",notes.get(itemNumber));//this is will get the Note at the Location of the Note.
				startActivity(editNoteIntent);
			}
		});
		//when the application loads up or is inflated, add our notes to the Array List and place it on the View
		//the object notes will hold data: title,note and date-remember the constructor in the Notes class? Its a way 
		notes.add(new Notes("First Note", "Blah Blah",new Date()));//adding the object data which will hold the date, note and  title 
		notes.add(new Notes("Second Note", "Meow Meow",new Date()));//adding the data
		notes.add(new Notes("Third Note", "Art Attack",new Date()));//adding the data
		notes.add(new Notes("Fourth Note", "Harry Potter",new Date()));//adding the data
		notes.add(new Notes("Fifth Note", "Frankenstein",new Date()));//adding the data
				
		populateList();
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_notes, menu);
        return true;
        
    }
	//when the Added Note button is clicked on, it brings up Added Note-using the getString method
	@Override
	public boolean onOptionsItemSelected(MenuItem item) //The Add Note button is for the onOptionsItemSelected Menu Item
	{
		// Handle action bar item clicks here. The action bar will add a new note
	  	//notes.add(new Notes("New Note","Blah Blah",new Date()));
		//populateList();
		//Intent editNoteIntent=new Intent(this,EditNoteActivity.class)
	    //startActivity(editNoteIntent);//we use the startActivityForResult to obtain data
	    startActivityForResult(new Intent(this,EditNoteActivity.class),1); //The 1 is a request code sent to the other activity which receives our req code, you can use anything since its just a single number we are expecting
	    return true;
	} 
	private void populateList() //this populates the list on the ListNotesActivity screen
	{
		//List of values which would hold our notes
		List<String> values=new ArrayList<String>();
		for (Notes notepad : notes) //notepad is of type Notes would loop through the list of Objects notes and pick the titles of the Strings Notepad
		{
			 values.add(notepad.getTitle());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,values);
		 
		noteListView.setAdapter(adapter);
	}	      
}