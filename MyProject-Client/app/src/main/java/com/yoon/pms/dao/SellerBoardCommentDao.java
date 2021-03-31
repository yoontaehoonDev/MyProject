package com.yoon.pms.dao;

import java.util.List;
import com.yoon.pms.domain.Comment;

public interface SellerBoardCommentDao {

  int insert(Comment c) throws Exception;
  int update(int no) throws Exception;
  int delete(int no) throws Exception;
  List<Comment> findAll() throws Exception;
  Comment findByNo(int no) throws Exception;
}
