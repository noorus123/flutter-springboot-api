package com.app.family.models;

public class Child {
	
	String name;
	String age;
	String gender;
	String labelName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	@Override
	public String toString() {
		return "Child [name=" + name + ", age=" + age + ", gender=" + gender + ", labelName=" + labelName + "]";
	}
	
	
	
	

}
