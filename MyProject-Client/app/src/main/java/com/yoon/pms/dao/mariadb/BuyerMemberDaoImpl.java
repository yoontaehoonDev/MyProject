package com.yoon.pms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.yoon.pms.dao.BuyerMemberDao;
import com.yoon.pms.domain.BuyerMember;

public class BuyerMemberDaoImpl implements BuyerMemberDao {
  Connection con;

  public BuyerMemberDaoImpl(Connection con) throws Exception {
    this.con = con;
  }

  @Override
  public int insert(BuyerMember b) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into memb(id, pwd, name, nick_name, email, tel, addr, rdt, state, m_no)"
            + " values(?,password(?),?,?,?,?,?,?,?,?)"
        )) {
      stmt.setString(1, b.getId());
      stmt.setString(2, b.getPassword());
      stmt.setString(3, b.getName());
      stmt.setString(4, b.getNickname());
      stmt.setString(5, b.getEmail());
      stmt.setString(6, b.getPhone());
      stmt.setString(7, b.getAddress());
      stmt.setDate(8, b.getRegisteredDate());
      stmt.setInt(9, b.getDivision());
      stmt.setInt(10, b.getNumber());

      return stmt.executeUpdate();
    }
  }
  @Override
  public int delete(int no) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }
  @Override
  public List<BuyerMember> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public BuyerMember findByNo(int no) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public int update(int no) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }
}
