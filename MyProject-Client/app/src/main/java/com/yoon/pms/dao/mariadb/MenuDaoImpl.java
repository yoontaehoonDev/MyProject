package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import com.yoon.pms.dao.MenuDao;

public class MenuDaoImpl implements MenuDao {
  Connection con;

  public MenuDaoImpl(Connection con) throws Exception {
    this.con = con;
  }



}
