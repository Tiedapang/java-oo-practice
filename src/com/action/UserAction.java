package com.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.pojo.Event;
import com.pojo.People;
import com.twu.Main;

public class UserAction {
	public static void adminFunc(List<Event> eventList) {
		System.out.println("请输入您的昵称：");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		People user = new People(name);
		basicAction(eventList,user);
		}
		
			
	private static void basicAction(List<Event> eventList, People user) {
		Scanner sc = new Scanner(System.in);
		if(eventList!=null&&eventList.size()!=0) {
			printTag(user.getName(),true);
			int choose = sc.nextInt();
			switch(choose){
			case 1:
				//查看热搜排行榜
				check_hotSearch(eventList);
				//可以继续以下操作
				basicAction(eventList,user);
				break;
			case 2:
				//给热搜事件投票
				vote_hotSearch(eventList,user);
				basicAction(eventList,user);
				break;
			case 3:
				//购买热搜
				buy_hotSearch(eventList);
				basicAction(eventList,user);
				break;
			case 4:
				//添加热搜
				add_hotSearch(eventList,false);
				basicAction(eventList,user);
				break;
			case 5:
				//退出
				Main.choose_function(eventList);
				break;
			}
		}else {
			printTag(user.getName(),false);
			int choose = sc.nextInt();
			switch(choose){
			case 1:
				//添加热搜
				add_hotSearch(eventList,false);
				basicAction(eventList,user);
				break;
			case 2:
				//退出
				Main.choose_function(eventList);
				break;
			}
		
		
	}


	}
	/**
	 * 添加热搜事件
	 * @param eventList:热搜事件列表
	 */
	static void add_hotSearch(List<Event> eventList,boolean flag) {
		System.out.println("请输入你要添加的热搜事件的名字：");
		Scanner sc = new Scanner(System.in);
		String description = sc.nextLine();
		//1.判断该热搜是否已经存在
		String index = isExist(eventList,description);
		//2.如果存在的话不添加
		if(!index.equals("not")) {
			System.out.println("该热搜已存在于热搜列表中！");
		}else {
		//3.如果不存在的话添加新的热搜事件
			Event event = new Event();
			event.setRanking(eventList.size());//设置热搜排名
			event.setDescription(description);//输入的热搜描述
			if(flag) {
				event.setSuperHot(true);//设计超级热搜
			}
			eventList.add(event);
			System.out.println("添加成功");
		}	
	}



	/**
	 * 给热搜事件投票
	 * @param eventList:热搜事件列表
	 * @param user:用户
	 */
	private static void vote_hotSearch(List<Event> eventList, People user) {
		//1.输入需要投票的热搜事件
		System.out.println("请输入你要投票的热搜名称:");
		Scanner sc = new Scanner(System.in);
		String description=sc.nextLine();
		//2.判断该热搜是否存在
		String index = isExist(eventList,description);
		//3.如果热搜存在可以投票
		if(!index.equals("not")) {
			System.out.println(String.format("请输入你要投票的热搜票数：(你目前还有%d)",user.getVotes()));
			//5.校验投票票数
			int vote = sc.nextInt();
			if(vote>user.getVotes()) {
				System.out.println("投票失败！");
			}else {
				user.setVotes(user.getVotes()-vote);
				//6.判断是否为超级热搜
				if(eventList.get(Integer.parseInt(index)).isSuperHot()) {
					eventList.get(Integer.parseInt(index)).setHeat(2*vote);
				}else {
					eventList.get(Integer.parseInt(index)).setHeat(vote);
				}
				System.out.println("投票成功！");
			}
		}else {
		//4.如果该热搜不存在，
			System.out.println("抱歉，您输入的热搜名称不存在！\n投票失败！");
		}
		
	}
	/**
	 * 购买热搜
	 * @param eventList:热搜事件列表
	 */
	private static void buy_hotSearch(List<Event> eventList) {
		//1.输入需要购买的热搜事件
		System.out.println("请输入你要购买的热搜名称:");
		Scanner sc = new Scanner(System.in);
		String description=sc.nextLine();
		//2.判断该热搜是否存在
		String index = isExist(eventList,description);
		//3.如果热搜存在可以购买
		if(!index.equals("not")) {
			//5.输入需要购买的热搜排名
			System.out.println("请输入你要购买的热搜排名：");
			int rank = sc.nextInt();
			//6.输入需要购买的热搜金额
			System.out.println("请输入你要购买的热搜金额：");
			int price = sc.nextInt();
			//7.判断该金额是否能购买到该位置;如果该位置金额大于输入购买热搜金额，购买失败；否则顶替掉该位置原本的热搜
			if(price<1||eventList.get(rank-1).getPrice()>price) {
				System.out.println("购买失败");
			}else if(eventList.get(rank-1).getPrice()>0) {
				eventList.get(Integer.parseInt(index)).setPrice(price);
				eventList.set(rank-1, eventList.get(Integer.parseInt(index)));
				eventList.remove(Integer.parseInt(index));
				System.out.println("购买成功");
			}else {
				eventList.get(Integer.parseInt(index)).setPrice(price);
				eventSort(eventList);
				System.out.println("购买成功");
			}
		}else {
		//4.如果该热搜不存在，不能购买
			System.out.println("抱歉，您输入的热搜名称不存在！\n购买失败！");
		}
	}

	

	/**
	 * 查看热搜事件排行榜
	 * @param eventList:热搜事件列表
	 */
	private static void check_hotSearch(List<Event> eventList) {
		//1.给热搜事件排名
		eventSort(eventList);
		//2.输出热搜排行榜
		if(eventList!=null) {
			for(int i=0;i<eventList.size();i++) {
				System.out.println(String.format("%d   %s   %d",i+1,eventList.get(i).getDescription(),eventList.get(i).getHeat()));
			}
		}
	}
	public static void printTag(String name,boolean tag) {
		if(tag) {
			System.out.println("你好，"+name+"，你可以：\n1.查看热搜排行榜\n2.给热搜事件投票\n3.购买热搜\n4.添加热搜\n5.退出");
		}else {
			System.out.println("你好，"+name+"，暂无热搜数据，你可以：\n1.添加热搜\n2.退出");
		}
		
	}
	/**
	 * 判断该热搜描述是否已经存在于热搜列表中,
	 * 如果存在返回该热搜描述在热搜列表中的下标
	 * 不存在返回"not"
	 * @param eventList:热搜列表
	 * @param description:热搜描述
	 * @return
	 */
	static String isExist(List<Event> eventList, String description) {
		boolean isExist = false;
		if(eventList!=null) {
			for(int i=0;i<eventList.size();i++) {
				if(eventList.get(i).getDescription().equals(description)) {
					return i+"";
				}
			}
		}
		return "not";
	}
	/**
	 * 对热搜列表排序
	 * @param eventList:热搜列表
	 */
	static void eventSort(List<Event> eventList) {
		Collections.sort(eventList, new Comparator<Event>() {

			@Override
			public int compare(Event o1, Event o2) {
				if(o1.getPrice()>o2.getPrice()) {
					return -1;
				}else if(o1.getPrice()<o2.getPrice()) {
					return 1;
				}else {
					if(o1.getHeat()>o2.getHeat()) {
						return -1;
					}else if(o1.getHeat()<o2.getHeat()) {
						return 1;
					}else {
						return 0;
					}
				}
			}
		});
		
	}


}
