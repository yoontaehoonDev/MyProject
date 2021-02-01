package com.yoon.pms.handler;

import java.util.regex.Pattern;

import com.yoon.pms.domain.Member;
import com.yoon.util.Prompt;


public class MemberList {

	Node first;
	Node last;
	Node currentNode;
	Member memberNumber;

	int memberCount = 0;

	void add(Member m) {
		Node node = new Node(m);

		if(last == null) {
			last = node;
			first = node;
		}
		else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		memberCount++;
	}

	Member[] toArray() {
		Member[] arr = new Member[memberCount];
		int i = 0;
		for(currentNode = this.first; currentNode != null; currentNode = currentNode.next) {
			arr[i++] = currentNode.member;
		}
		return arr;
	}

	void find(int number) {
		Node cursor;
		for(cursor = first; cursor != null; cursor = cursor.next) {
			Member m = cursor.member;
			if(number == m.number) {
				currentNode = cursor;
				delete();
			}
		}
	}

	void delete() {

		if(currentNode == first) {
			first = currentNode.next;
		}
		else {
			currentNode.prev.next = currentNode.next;
			if(currentNode.next != null) {
				currentNode.next.prev = currentNode.prev;
			}
		}
		if(currentNode == last) {
			last = currentNode.prev;
		}
		memberCount--;

	}

	public String isSame(String message) {
		String id;
		while(true) {
			int flag = 0;
			id = Prompt.inputString(message);
			if(id.length() < 8) {
				System.out.println();
				System.out.println("8자리 이상 입력하세요.");
				flag = 1;
			}
			else {
				for(Node cursor = first; cursor != null; cursor = cursor.next) {
					Member m = cursor.member;
					if(id.equalsIgnoreCase(m.id)) {
						System.out.println("이미 사용중인 아이디 입니다.\n");
						flag = 1;
						break;
					}
				}
			}
			if(flag == 0) {
				break;
			}
		}
		return id;
	}

	Member verifyId(String id) {
		for(Node cursor = first; cursor != null; cursor = cursor.next) {
			Member m = cursor.member;
			if(m.id.equalsIgnoreCase(id)) {
				currentNode = cursor;
				return m;
			}
		}
		return null;
	}

	boolean verifyPassword(String password, Member m) {
		if(password.equals(m.password)) {
			return true;
		}
		return false;
	}

	String minimumLength(String name) {
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

	String emailFormat(String name) {
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

	String phoneFormat(String name) {
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
		Member member;
		Node prev;
		Node next;
		Node(Member m) {
			this.member = m;
		}
	}
}
