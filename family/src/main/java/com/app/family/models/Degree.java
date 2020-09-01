package com.app.family.models;

public class Degree {
	
	private String degreeName;
	private String stream;
	private String boardUniversity;
	private String yearCompletion;
	private String institution;
	
	public Degree() {
		super();
	}
	
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}	
	public String getBoardUniversity() {
		return boardUniversity;
	}
	public void setBoardUniversity(String boardUniversity) {
		this.boardUniversity = boardUniversity;
	}
	public String getYearCompletion() {
		return yearCompletion;
	}
	public void setYearCompletion(String yearCompletion) {
		this.yearCompletion = yearCompletion;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}	
}
