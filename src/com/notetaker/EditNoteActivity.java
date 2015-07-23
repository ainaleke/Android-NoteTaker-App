package com.notetaker;

import java.io.Serializable;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class EditNoteActivity extends Activity {
	private boolean isInEditMode=true;
	
	//When the activity is created or when the app comes up
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note); //load up the view called activity_main 
        final EditText titleEditText=(EditText)findViewById(R.id.titleEditText);
        final EditText noteEditText=(EditText)findViewById(R.id.noteEditText);
        final Button saveButton = (Button)findViewById(R.id.saveButton);
        
        //In order to tell if we have some data in the note that is gotten
        Serializable extra=getIntent().getSerializableExtra("Note");
        if (extra != null) //if the data in the Note is not empty then
        {
        	Notes note = (Notes)extra; //cast the serializable-extra to a note
        	titleEditText.setText(note.getTitle());
        	noteEditText.setText(note.getNote());
        	
        	//If there is data, then disable the Title Edit Text Field, otherwise leave it enabled
        	isInEditMode=false;
        	titleEditText.setEnabled(false);//Make the titleEdit Text non-editable
        	noteEditText.setEnabled(false);//make the note Edit Text editable
        	saveButton.setText("Edit");
        	
        }        
        saveButton.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
		      if(isInEditMode) //Value is true by default, so this will execute if button is clicked
			 {
				 /**isInEditMode=false; //when the button is clicked, it goes into edit mode and sets the bool value to false
                 saveButton.setText("Edit"); //When the save button is clicked, it should set the text of the button to Edit	
                 titleEditText.setEnabled(false);
    			 noteEditText.setEnabled(false); **/
    			 Intent returnIntent = new Intent();
    			 Notes note=new Notes(titleEditText.getText().toString(),
    					 noteEditText.getText().toString(),
    					 Calendar.getInstance().getTime());
    			 returnIntent.putExtra("Note", note);//when the save button is clicked put the data into the Object and hide it there
    			 setResult(RESULT_OK,returnIntent);
    			 finish();
    			 
			  }
			 else  //inEditMode is false---If button is clicked again, this is what will happen
			 {
				 isInEditMode=true;
				 saveButton.setText("Save");
				 titleEditText.setEnabled(true);
				 noteEditText.setEnabled(true);
			 } 
			} 
		});
    } 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //the add note button
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        startActivity(new Intent(this,ListNotes.class));//when this back button is clicked go back to the ListNotes Activity screen
        return super.onOptionsItemSelected(item);
    }
}
 