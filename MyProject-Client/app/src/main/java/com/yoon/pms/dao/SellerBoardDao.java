package com.yoon.pms.dao;

import java.util.List;
import com.yoon.pms.domain.Board;

public interface SellerBoardDao {

  int insert(Board b) throws Exception;
  int update(int no) throws Exception;
  int delete(int no) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
}
