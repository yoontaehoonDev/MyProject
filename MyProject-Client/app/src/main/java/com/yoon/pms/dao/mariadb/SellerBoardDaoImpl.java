package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.SellerBoardDao;

public class SellerBoardDaoImpl implements SellerBoardDao {
  Connection con;

  public SellerBoardDaoImpl(Connection con) throws Exception {
    this.con = con;
  }
}
