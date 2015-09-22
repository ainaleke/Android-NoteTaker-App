package com.notetaker2;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EditNoteActivity extends Activity {
	private boolean isEditable=true;
	public static RelativeLayout editNoteLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_note);
		final Button saveButton=(Button)findViewById(R.id.saveButton);
		final Button cancelButton=(Button)findViewById(R.id.cancelButton);
		final EditText titleEditText=(EditText)findViewById(R.id.titleEditText);
		final EditText noteEditText=(EditText)findViewById(R.id.noteEditText);
		final TextView dateTextView=(TextView)findViewById(R.id.dateTextView);

		//check to see if the data called extra gotten from the Intent has data
		Serializable extra=getIntent().getSerializableExtra("Note");
		
		if (extra!=null)
		{
			Note note = (Note)extra;
			//set all of the things on Edit Note
			titleEditText.setText(note.getTitle());
			noteEditText.setText(note.getNote());
			
			DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String date=dateFormat.format(note.getDate());//Calendar.getInstance().getTime());
			dateTextView.setText(date);
			
			isEditable=false;
			titleEditText.setEnabled(false);
			noteEditText.setEnabled(false);
			saveButton.setText("Edit");
			
			
		}
		cancelButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED,new Intent());
				finish();
			}
			
		});
		//once the EditNote activity launches, we need to check if there is data 
		saveButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				
				//if the Text Fields are editable disable them, change Save to Edit and change the boolean variable to false
					if(isEditable){
						/**
						//when the save button is clicked we want the view to set the date						
						 **/
						//create a new intent which will house the data which will be passed back
						Intent returnIntent=new Intent();
						Note note=new Note(titleEditText.getText().toString(),
								noteEditText.getText().toString(),Calendar.getInstance().getTime());
						returnIntent.putExtra("Note", note);
						setResult(RESULT_OK,returnIntent);
						finish();
					}
					//if all fields are not editable then enable all of them including the boolean variable and change button text to Save
					else 
					{
						isEditable=true;
						titleEditText.setEnabled(true);
						noteEditText.setEnabled(true);
						saveButton.setText("Save");
					}	
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.back_menu_item) {
			//now we create an intent to go back to the List Notes Activity
			Intent listNotesIntent=new Intent(this,ListNotesActivity.class);
			startActivity(listNotesIntent);
			
			return true;
		}
		
		
		if(id == R.id.deleteNote)
		{
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setMessage(R.string.deleteDialogMessage);
			builder.setTitle("Confirm Delete");
			//set the Buttons for th Alert Dialog
			builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					// TODO Auto-generated method stub
					
				}
		   	});
			
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			builder.create().show();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
