package com.yoon.util;

import java.util.regex.Pattern;


public class List {

	private Node first;
	private Node last;


	public int size = 0;

	public void add(Object obj) {
		Node node = new Node(obj);

		if(last == null) {
			last = node;
			first = node;
		}
		else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		size++;
	}

	public Object[] toArray() {
		Object[] arr = new Object[size];
		int i = 0;
		for(Node cursor = this.first; cursor != null; cursor = cursor.next) {
			arr[i++] = cursor.obj;
		}
		return arr;
	}

	public Object get(int index) {
		if(index < 0 || index >= this.size) {
			return null;
		}

		int count = 0;
		for(Node cursor = first; cursor != null; cursor = cursor.next) {
			if(index == count++) {
				return cursor.obj;
			}
		}
		return null;
	}

	public boolean delete(Object obj) {
		Node cursor = first;
		while (cursor != null) {
			if (cursor.obj.equals(obj)) {
				this.size--;
				if (first == last) {
					first = last = null;
					return true;
				}
				if (cursor == first) {
					first = cursor.next;
					cursor.prev = null;
				} else {
					cursor.prev.next = cursor.next;
					if (cursor.next != null) {
						cursor.next.prev = cursor.prev;
					}
				}
				if (cursor == last) {
					last = cursor.prev;
				}
				return true;
			}
			cursor = cursor.next;
		}
		return false;
	}

	public Object delete (int index) {
		if(index < 0 || index >= this.size) {
			return null;
		}

		Object deleted = null;

		int count = 0;
		for(Node cursor = first; cursor != null; cursor = cursor.next) {
			if (index == count++) {
				deleted = cursor.obj;
				this.size--;
				if (first == last) {
					first = last = null;
					break;
				}
				if (cursor == first) {
					first = cursor.next;
					cursor.prev = null;
				} else {
					cursor.prev.next = cursor.next;
					if (cursor.next != null) {
						cursor.next.prev = cursor.prev;
					}
				}
				if (cursor == last) {
					last = cursor.prev;
				}
				break;
			}
			cursor = cursor.next;
		}
		return deleted;

	}



	public String minimumLength(String name) {
		while(true) {
			String info = Prompt.inputString(name);
			if(info.length() < 8) {
				System.out.println();
				System.out.println("8자리 이상 입력하세요.");
			}
			else {
				return info;
			}
		}
	}

	public String emailFormat(String name) {
		while(true) {
			String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
			String email = Prompt.inputString(name);
			if(Pattern.matches(pattern, email)) {
				return email;
			}
			else {
				System.out.println("E-Mail 형식(abc@abc.abc)이 아닙니다.");
			}
		}
	}

	public String phoneFormat(String name) {
		while(true) {
			String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
			String phone = Prompt.inputString(name);
			if(Pattern.matches(pattern, phone)) {
				return phone;
			}
			else {
				System.out.println("전화번호 형식(000-0000-0000)이 아닙니다.");
			}
		}
	}


	static class Node {
		Object obj;
		Node prev;
		Node next;

		Node(Object obj) {
			this.obj = obj;
		}
	}
}
