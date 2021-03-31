package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.SellerBoardCommentDao;

public class SellerBoardCommentDaoImpl implements SellerBoardCommentDao {
  Connection con;

  public SellerBoardCommentDaoImpl(Connection con) throws Exception {
    this.con = con;
  }
}
