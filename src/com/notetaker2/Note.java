package com.notetaker2;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable{
	//this is means this class can be inflated and deflated to be transported through different formats
	private String title;
	private String note;
	private Date date;
	
	public Note(){}
	
	public Note(String title, String note, Date date)
	{
		super();
		this.title=title;
		this.note=note;
		this.date=date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	
	
}
