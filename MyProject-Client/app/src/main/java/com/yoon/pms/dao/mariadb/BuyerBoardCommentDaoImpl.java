package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.BuyerBoardCommentDao;

public class BuyerBoardCommentDaoImpl implements BuyerBoardCommentDao {
  Connection con;

  public BuyerBoardCommentDaoImpl(Connection con) throws Exception {
    this.con = con;
  }


}
