package com.yoon.util;

public class StackIterator implements Iterator {
	Stack stack;

	public StackIterator(Stack stack) throws CloneNotSupportedException {
		this.stack = stack.clone();
	}

	@Override
	public boolean hasNext() {
		return this.stack.size() > 0;
	}

	@Override
	public Object next() {
		return this.stack.pop();
	}
}
