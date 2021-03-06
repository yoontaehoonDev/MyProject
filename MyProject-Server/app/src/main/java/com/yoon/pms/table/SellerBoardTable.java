package com.yoon.pms.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.util.JsonFileHandler;
import com.yoon.util.Request;
import com.yoon.util.Response;

public class SellerBoardTable implements DataTable {
  File jsonFile = new File("sellerBoards.json");
  List<Board> list;

  public SellerBoardTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Board.class);

  }

  @Override
  public void service(Request request, Response response) throws Exception {

    Board board = null;
    String[] fields = null;

    switch(request.getCommand()) {
      case "sellerBoard/insert":

        fields = request.getDataList().get(0).split(",");

        board = new Board();

        if(list.size() > 0) {
          board.setId(list.get(list.size() - 1).getNumber() + 1);
          board.setNumber(list.get(list.size() - 1).getNumber() + 1);
        }
        else {
          board.setId(1);
          board.setNumber(1);
        }

        board.setTitle(fields[1]);
        board.setContent(fields[2]);
        board.setWriter(fields[3]);
        board.setView(Integer.parseInt(fields[4]));
        board.setRegisteredDate(new Date(System.currentTimeMillis()));

        list.add(board);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "sellerBoard/selectall":
        for(Board b : list) {
          response.appendData(String.format("%d,%s,%s,%s,%d,%s", 
              b.getNumber(),
              b.getTitle(),
              b.getContent(),
              b.getWriter(),
              b.getView(),
              b.getRegisteredDate()));
        }
        break;

      case "sellerBoard/select":
        int num = Integer.parseInt(request.getDataList().get(0));

        board = getSellerBoardNumber(num);

        if(board != null) {
          response.appendData(String.format("%d,%s,%s,%s,%d,%s", 
              board.getNumber(),
              board.getTitle(),
              board.getContent(),
              board.getWriter(),
              board.getView(),
              board.getRegisteredDate()));
        }
        else {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }
        break;
      case "sellerBoard/update":
        fields = request.getDataList().get(0).split(",");

        board = getSellerBoardNumber(Integer.parseInt(fields[0]));
        if(board == null) {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }

        board.setTitle(fields[1]);
        board.setContent(fields[2]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "sellerBoard/delete":
        num = Integer.parseInt(request.getDataList().get(0));
        board = getSellerBoardNumber(num);
        if(board == null) {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }
        list.remove(board);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Board getSellerBoardNumber(int number) {
    for(Board b : list) {
      if(b.getNumber() == number) {
        return b;
      }
    }
    return null;
  }
}
