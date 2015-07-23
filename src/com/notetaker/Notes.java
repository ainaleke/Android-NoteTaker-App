package com.notetaker;

import java.io.Serializable;
import java.util.Date;

public class Notes implements Serializable
{
	private String title;
	private String note;
	private Date date;

	public Notes(String title, String note, Date date)
	{
		super();
		this.title = title;
		this.note = note;
		this.date = date;
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
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
