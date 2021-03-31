package com.yoon.pms.dao;

import java.util.List;
import com.yoon.pms.domain.Order;

public interface OrderDao {

  int insert(Order o) throws Exception;
  int update(int no) throws Exception;
  int delete(int no) throws Exception;
  List<Order> findAll() throws Exception;
  Order findByNo(int no) throws Exception;
}
