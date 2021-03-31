package com.yoon.pms.dao;

import java.util.List;
import com.yoon.pms.domain.BuyerMember;

public interface BuyerMemberDao {

  int insert(BuyerMember b) throws Exception;
  int update(int no) throws Exception;
  int delete(int no) throws Exception;
  List<BuyerMember> findAll() throws Exception;
  BuyerMember findByNo(int no) throws Exception;

}
