package com.twu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.action.ManagerAction;
import com.action.UserAction;
import com.pojo.Event;


public class Main {
	public static void main(String[] args) {
    	List<Event>eventList = new ArrayList<Event>();
    	choose_function(eventList);
    }

	public static void choose_function(List<Event> eventList) {
		UserAction ua = new UserAction();
		ManagerAction ma = new ManagerAction();
		Scanner sc = new Scanner(System.in);
    	printTag();
    	int adminTag = sc.nextInt();
		switch(adminTag){
    	case 1:
    		ua.adminFunc(eventList);
    		break;
    	case 2:	
    		ma.adminFunc(eventList);
    		break;
    	case 3:
    		break;//直接退出
    	}

		
	}

	public static void printTag(){
		System.out.println("欢迎来到热搜排行榜，你是？\n1.用户\n2.管理员\n3.退出");
	}
}
