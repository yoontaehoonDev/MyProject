package com.yoon.util;

public interface ObjectFactory<T> {
  T create(String csvStr);
}
