package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
  Connection con;

  public OrderDaoImpl(Connection con) throws Exception {
    this.con = con;
  }
}
