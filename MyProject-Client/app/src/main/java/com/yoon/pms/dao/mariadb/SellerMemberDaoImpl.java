package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.SellerMemberDao;

public class SellerMemberDaoImpl implements SellerMemberDao {
  Connection con;

  public SellerMemberDaoImpl(Connection con) throws Exception {
    this.con = con;
  }
}
