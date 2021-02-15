package com.yoon.util;

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
    }
    return deleted;
  }

  public int size() {
    return this.size;
  }

  public static class Node {
    Object obj;
    Node prev;
    Node next;

    Node(Object obj) {
      this.obj = obj;
    }
  }


  public Iterator iterator() throws CloneNotSupportedException {
    return new Iterator() {
      int cursor = 0;

      @Override
      public boolean hasNext() {
        return cursor < List.this.size();
      }

      @Override
      public Object next() {
        return List.this.get(cursor++);
      }
    };
  }
}
