package com.pojo;

public class People {
	String name;//����
	int votes = 10;//Ʊ��
	public static String managerName = "admin"; 
	public static String password = "admin123";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public People(String name) {
		this.name=name;
		
	}
	
}
