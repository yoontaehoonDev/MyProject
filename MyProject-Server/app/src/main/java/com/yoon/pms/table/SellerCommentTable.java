package com.yoon.pms.table;

import java.io.File;
import java.util.List;
import com.yoon.pms.domain.Comment;
import com.yoon.util.JsonFileHandler;
import com.yoon.util.Request;
import com.yoon.util.Response;

public class SellerCommentTable implements DataTable {

  File jsonFile = new File("sellerComments.json");
  List<Comment> list;

  public SellerCommentTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Comment.class);

  }

  @Override
  public void service(Request request, Response response) throws Exception {

    Comment comment = null;
    String[] fields = null;

    switch(request.getCommand()) {
      case "sellerComment/insert":

        fields = request.getDataList().get(0).split(",");

        comment = new Comment();

        comment.setCommentId(Integer.parseInt(fields[1]));
        comment.setCommentNumber(Integer.parseInt(fields[2]));
        comment.setCommentWriter(fields[3]);
        comment.setComment(fields[4]);
        list.add(comment);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;

      case "sellerComment/selectall":
        for(Comment c : list) {
          response.appendData(String.format("%d,%s,%s", 
              c.getCommentNumber(),
              c.getCommentWriter(),
              c.getComment()));
        }
        break;

      case "sellerComment/select":
        int num = Integer.parseInt(request.getDataList().get(0));

        comment = getCommentNumber(num);

        if(comment != null) {
          response.appendData(String.format("%d,%s,%s", 
              comment.getCommentNumber(),
              comment.getCommentWriter(),
              comment.getComment()));
        }
        else {
          throw new Exception("해당 번호의 댓글이 없습니다.");
        }
        break;
      case "sellerComment/update":
        fields = request.getDataList().get(0).split(",");

        comment = getCommentNumber(Integer.parseInt(fields[0]));
        if(comment == null) {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }

        comment.setComment(fields[4]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "sellerComment/delete":
        num = Integer.parseInt(request.getDataList().get(0));
        comment = getCommentNumber(num);
        if(comment == null) {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }
        list.remove(comment);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Comment getCommentNumber(int number) {
    for(Comment c : list) {
      if(c.getCommentNumber() == number) {
        return c;
      }
    }
    return null;
  }

}
