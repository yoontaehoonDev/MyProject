package com.yoon.pms.dao;

import java.util.List;
import com.yoon.pms.domain.Menu;

public interface MenuDao {

  int insert(Menu m) throws Exception;
  int update(int no) throws Exception;
  int delete(int no) throws Exception;
  List<Menu> findAll() throws Exception;
  Menu findByNo(int no) throws Exception;

}
