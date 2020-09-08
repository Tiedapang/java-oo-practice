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
		System.out.println("�����������ǳƣ�");
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
				//�鿴�������а�
				check_hotSearch(eventList);
				//���Լ������²���
				basicAction(eventList,user);
				break;
			case 2:
				//�������¼�ͶƱ
				vote_hotSearch(eventList,user);
				basicAction(eventList,user);
				break;
			case 3:
				//��������
				buy_hotSearch(eventList);
				basicAction(eventList,user);
				break;
			case 4:
				//�������
				add_hotSearch(eventList,false);
				basicAction(eventList,user);
				break;
			case 5:
				//�˳�
				Main.choose_function(eventList);
				break;
			}
		}else {
			printTag(user.getName(),false);
			int choose = sc.nextInt();
			switch(choose){
			case 1:
				//�������
				add_hotSearch(eventList,false);
				basicAction(eventList,user);
				break;
			case 2:
				//�˳�
				Main.choose_function(eventList);
				break;
			}
		
		
	}


	}
	/**
	 * ��������¼�
	 * @param eventList:�����¼��б�
	 */
	static void add_hotSearch(List<Event> eventList,boolean flag) {
		System.out.println("��������Ҫ��ӵ������¼������֣�");
		Scanner sc = new Scanner(System.in);
		String description = sc.nextLine();
		//1.�жϸ������Ƿ��Ѿ�����
		String index = isExist(eventList,description);
		//2.������ڵĻ������
		if(!index.equals("not")) {
			System.out.println("�������Ѵ����������б��У�");
		}else {
		//3.��������ڵĻ�����µ������¼�
			Event event = new Event();
			event.setRanking(eventList.size());//������������
			event.setDescription(description);//�������������
			if(flag) {
				event.setSuperHot(true);//��Ƴ�������
			}
			eventList.add(event);
			System.out.println("��ӳɹ�");
		}	
	}



	/**
	 * �������¼�ͶƱ
	 * @param eventList:�����¼��б�
	 * @param user:�û�
	 */
	private static void vote_hotSearch(List<Event> eventList, People user) {
		//1.������ҪͶƱ�������¼�
		System.out.println("��������ҪͶƱ����������:");
		Scanner sc = new Scanner(System.in);
		String description=sc.nextLine();
		//2.�жϸ������Ƿ����
		String index = isExist(eventList,description);
		//3.������Ѵ��ڿ���ͶƱ
		if(!index.equals("not")) {
			System.out.println(String.format("��������ҪͶƱ������Ʊ����(��Ŀǰ����%d)",user.getVotes()));
			//5.У��ͶƱƱ��
			int vote = sc.nextInt();
			if(vote>user.getVotes()) {
				System.out.println("ͶƱʧ�ܣ�");
			}else {
				user.setVotes(user.getVotes()-vote);
				//6.�ж��Ƿ�Ϊ��������
				if(eventList.get(Integer.parseInt(index)).isSuperHot()) {
					eventList.get(Integer.parseInt(index)).setHeat(2*vote);
				}else {
					eventList.get(Integer.parseInt(index)).setHeat(vote);
				}
				System.out.println("ͶƱ�ɹ���");
			}
		}else {
		//4.��������Ѳ����ڣ�
			System.out.println("��Ǹ����������������Ʋ����ڣ�\nͶƱʧ�ܣ�");
		}
		
	}
	/**
	 * ��������
	 * @param eventList:�����¼��б�
	 */
	private static void buy_hotSearch(List<Event> eventList) {
		//1.������Ҫ����������¼�
		System.out.println("��������Ҫ�������������:");
		Scanner sc = new Scanner(System.in);
		String description=sc.nextLine();
		//2.�жϸ������Ƿ����
		String index = isExist(eventList,description);
		//3.������Ѵ��ڿ��Թ���
		if(!index.equals("not")) {
			//5.������Ҫ�������������
			System.out.println("��������Ҫ���������������");
			int rank = sc.nextInt();
			//6.������Ҫ��������ѽ��
			System.out.println("��������Ҫ��������ѽ�");
			int price = sc.nextInt();
			//7.�жϸý���Ƿ��ܹ��򵽸�λ��;�����λ�ý��������빺�����ѽ�����ʧ�ܣ����������λ��ԭ��������
			if(price<1||eventList.get(rank-1).getPrice()>price) {
				System.out.println("����ʧ��");
			}else if(eventList.get(rank-1).getPrice()>0) {
				eventList.get(Integer.parseInt(index)).setPrice(price);
				eventList.set(rank-1, eventList.get(Integer.parseInt(index)));
				eventList.remove(Integer.parseInt(index));
				System.out.println("����ɹ�");
			}else {
				eventList.get(Integer.parseInt(index)).setPrice(price);
				eventSort(eventList);
				System.out.println("����ɹ�");
			}
		}else {
		//4.��������Ѳ����ڣ����ܹ���
			System.out.println("��Ǹ����������������Ʋ����ڣ�\n����ʧ�ܣ�");
		}
	}

	

	/**
	 * �鿴�����¼����а�
	 * @param eventList:�����¼��б�
	 */
	private static void check_hotSearch(List<Event> eventList) {
		//1.�������¼�����
		eventSort(eventList);
		//2.����������а�
		if(eventList!=null) {
			for(int i=0;i<eventList.size();i++) {
				System.out.println(String.format("%d   %s   %d",i+1,eventList.get(i).getDescription(),eventList.get(i).getHeat()));
			}
		}
	}
	public static void printTag(String name,boolean tag) {
		if(tag) {
			System.out.println("��ã�"+name+"������ԣ�\n1.�鿴�������а�\n2.�������¼�ͶƱ\n3.��������\n4.�������\n5.�˳�");
		}else {
			System.out.println("��ã�"+name+"�������������ݣ�����ԣ�\n1.�������\n2.�˳�");
		}
		
	}
	/**
	 * �жϸ����������Ƿ��Ѿ������������б���,
	 * ������ڷ��ظ����������������б��е��±�
	 * �����ڷ���"not"
	 * @param eventList:�����б�
	 * @param description:��������
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
	 * �������б�����
	 * @param eventList:�����б�
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
