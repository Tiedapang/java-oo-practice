package com.pojo;

public class Event {
	String description ;//�����¼�����
	int heat=0;//�����¼��ȶ�
	int ranking ;//�����¼�����
	int price=0;//��������������
	boolean isSuperHot=false;//�Ƿ�Ϊ��������
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isSuperHot() {
		return isSuperHot;
	}
	public void setSuperHot(boolean isSuperHot) {
		this.isSuperHot = isSuperHot;
	}
	

	
	
}
