package com.yoon.pms.dao;

import java.util.List;
import com.yoon.pms.domain.SellerMember;

public interface SellerMemberDao {

  int insert(SellerMember s) throws Exception;
  int update(int no) throws Exception;
  int delete(int no) throws Exception;
  List<SellerMember> findAll() throws Exception;
  SellerMember findByNo(int no) throws Exception;
}
