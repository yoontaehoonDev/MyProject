package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.BuyerBoardDao;

public class BuyerBoardDaoImpl implements BuyerBoardDao {

  Connection con;

  public BuyerBoardDaoImpl(Connection con) throws Exception {
    this.con = con;
  }
}
