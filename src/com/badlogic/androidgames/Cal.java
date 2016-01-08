package com.badlogic.androidgames;

public class Cal 
{
	private long id;
	  private int cal;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public int getVal() {
	    return cal;
	  }

	  public void setVal(int cal) {
	    this.cal = cal;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return cal + "";
	  }
}
