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
    		break;//ֱ���˳�
    	}

		
	}

	public static void printTag(){
		System.out.println("��ӭ�����������а����ǣ�\n1.�û�\n2.����Ա\n3.�˳�");
	}
}
