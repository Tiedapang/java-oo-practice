package com.action;

import java.util.List;
import java.util.Scanner;

import com.pojo.Event;
import com.pojo.People;
import com.twu.Main;

public class ManagerAction {
	
	public void adminFunc(List<Event> eventList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������ǳƣ�");
		String name = sc.nextLine();//����Ա����
		System.out.println("�������������룺");
		String password = sc.nextLine();//����Ա����
		//1.��֤����Ա���������룬�������Ա�˻�û���⣬��������������˻����ԣ��˳�
		boolean account_pass = checkAccount(name,password);
		if(account_pass) {
			
			basicAction(eventList,name);
		}else {
			System.out.println("��Ǹ������½�˻���������������������ԣ�");
			Main.choose_function(eventList);
		}
	}
	
	private static void basicAction(List<Event> eventList,String name) {
		printTag(name);
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		switch(choose){
		case 1:
			//�鿴�������а�
			check_hotSearch(eventList);
			//���Լ������²���
			basicAction(eventList,name);
			break;
		case 2:
			//�������
			UserAction.add_hotSearch(eventList,false);
			basicAction(eventList,name);
			break;
		case 3:
			//��ӳ�������
			UserAction.add_hotSearch(eventList,true);
			basicAction(eventList,name);
			break;
		case 4:
			//�˳�
			Main.choose_function(eventList);
			break;
		}	
	}
	
	/**
	 * �鿴�����¼����а�
	 * @param eventList:�����¼��б�
	 */
	private static void check_hotSearch(List<Event> eventList) {
		if(eventList!=null&&eventList.size()>0) {
			//1.�������¼�����
			UserAction.eventSort(eventList);
			//2.����������а�
			for(int i=0;i<eventList.size();i++) {
				System.out.println(String.format("%d   %s   %d",i+1,eventList.get(i).getDescription(),eventList.get(i).getHeat()));
			}
		}else {
			System.out.println("��Ǹ��������������");
		}
	}
	/**
	 * ��֤����Ա�˻��Ƿ����趨�˻���������һ��
	 * @param name:����Ա����
	 * @param password:����Ա����
	 * @return
	 */
	public boolean checkAccount(String name, String password) {
		if(People.managerName.equals(name)&&People.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	public static void printTag(String name) {
		System.out.println("��ã�"+name+"������ԣ�\n1.�鿴�������а�\n2.�������\n3.��ӳ�������\n4.�˳�");	
	}

}
