package com.yoon.util;

public class ListIterator implements Iterator {
	List list;
	int cursor = 0;

	public ListIterator(List list) {
		this.list = list;
	}

	@Override
	public boolean hasNext() {
		return cursor < list.size();
	}

	@Override
	public Object next() {
		return list.get(cursor++);
	}

}
