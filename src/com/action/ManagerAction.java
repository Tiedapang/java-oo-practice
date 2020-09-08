package com.action;

import java.util.List;
import java.util.Scanner;

import com.pojo.Event;
import com.pojo.People;
import com.twu.Main;

public class ManagerAction {
	
	public void adminFunc(List<Event> eventList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入您的昵称：");
		String name = sc.nextLine();//管理员姓名
		System.out.println("请输入您的密码：");
		String password = sc.nextLine();//管理员密码
		//1.验证管理员姓名和密码，如果管理员账户没问题，继续操作，如果账户不对，退出
		boolean account_pass = checkAccount(name,password);
		if(account_pass) {
			
			basicAction(eventList,name);
		}else {
			System.out.println("抱歉，您登陆账户姓名或者密码错误，请重试：");
			Main.choose_function(eventList);
		}
	}
	
	private static void basicAction(List<Event> eventList,String name) {
		printTag(name);
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		switch(choose){
		case 1:
			//查看热搜排行榜
			check_hotSearch(eventList);
			//可以继续以下操作
			basicAction(eventList,name);
			break;
		case 2:
			//添加热搜
			UserAction.add_hotSearch(eventList,false);
			basicAction(eventList,name);
			break;
		case 3:
			//添加超级热搜
			UserAction.add_hotSearch(eventList,true);
			basicAction(eventList,name);
			break;
		case 4:
			//退出
			Main.choose_function(eventList);
			break;
		}	
	}
	
	/**
	 * 查看热搜事件排行榜
	 * @param eventList:热搜事件列表
	 */
	private static void check_hotSearch(List<Event> eventList) {
		if(eventList!=null&&eventList.size()>0) {
			//1.给热搜事件排名
			UserAction.eventSort(eventList);
			//2.输出热搜排行榜
			for(int i=0;i<eventList.size();i++) {
				System.out.println(String.format("%d   %s   %d",i+1,eventList.get(i).getDescription(),eventList.get(i).getHeat()));
			}
		}else {
			System.out.println("抱歉，暂无热搜数据");
		}
	}
	/**
	 * 验证管理员账户是否与设定账户名和密码一致
	 * @param name:管理员姓名
	 * @param password:管理员密码
	 * @return
	 */
	public boolean checkAccount(String name, String password) {
		if(People.managerName.equals(name)&&People.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	public static void printTag(String name) {
		System.out.println("你好，"+name+"，你可以：\n1.查看热搜排行榜\n2.添加热搜\n3.添加超级热搜\n4.退出");	
	}

}
