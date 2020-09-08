package com.pojo;

public class Event {
	String description ;//热搜事件描述
	int heat=0;//热搜事件热度
	int ranking ;//热搜事件排名
	int price=0;//购买热搜所需金额
	boolean isSuperHot=false;//是否为超级热搜
	
	
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
