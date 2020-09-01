package com.app.family.models;

import com.app.family.enums.JobType;

public class Profession {
	
	private String company;
	private String designation;
	private JobType jobType;
	
	public Profession() {
		super();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	
	
}
