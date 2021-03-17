package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.App;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class SellerBoardReturnHandler extends AbstractSellerBoardHandler {

  public SellerBoardReturnHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
    super(sellerBoardList, sellerCommentList);
  }

  @Override
  public void service() {
    App.location = 1;
    return;
  }


}
