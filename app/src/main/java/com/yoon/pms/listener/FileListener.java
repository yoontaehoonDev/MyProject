package com.yoon.pms.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoon.context.ApplicationContextListener;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.Log;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.Order;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.CsvObject;

public class FileListener implements ApplicationContextListener {

  File buyerMemberListFile = new File("buyerMemberList.json");
  File sellerMemberListFile = new File("sellerMemberList.json");
  File buyerCommentListFile = new File("buyerCommentList.json");
  File sellerCommentListFile = new File("sellerCommentList.json");
  File integratedCommentListFile = new File("integratedCommentList.json");
  File buyerBoardListFile = new File("buyerBoardList.json");
  File sellerBoardListFile = new File("sellerBoardList.json");
  File integratedBoardListFile = new File("integratedBoardList.json");
  File logListFile = new File("logList.json");
  File menuListFile = new File("menuList.json");
  File orderListFile = new File("orderList.json");

  List<BuyerMember> buyerMemberList;
  List<SellerMember> sellerMemberList;
  List<Comment> buyerCommentList;
  List<Comment> sellerCommentList;
  List<Comment> integratedCommentList;
  List<Board> buyerBoardList;
  List<Board> sellerBoardList;
  List<Board> integratedBoardList;
  List<Log> logList;
  List<Menu> menuList;
  List<Order> orderList;

  @Override
  public void contextInitialized(Map<String,Object> context) {

    buyerMemberList = loadObjects(buyerMemberListFile, BuyerMember.class);
    sellerMemberList = loadObjects(sellerMemberListFile, SellerMember.class);
    buyerCommentList = loadObjects(buyerCommentListFile, Comment.class);
    sellerCommentList = loadObjects(sellerCommentListFile, Comment.class);
    integratedCommentList = loadObjects(integratedCommentListFile, Comment.class);
    buyerBoardList = loadObjects(buyerBoardListFile, Board.class);
    sellerBoardList = loadObjects(sellerBoardListFile, Board.class);
    integratedBoardList = loadObjects(integratedBoardListFile, Board.class);
    logList = loadObjects(logListFile, Log.class);
    menuList = loadObjects(menuListFile, Menu.class);
    orderList = loadObjects(orderListFile, Order.class);

    context.put("buyerMemberList", buyerMemberList);
    context.put("sellerMemberList", sellerMemberList);
    context.put("buyerCommentList", buyerCommentList);
    context.put("sellerCommentList", sellerCommentList);
    context.put("integratedCommentList", integratedCommentList);
    context.put("buyerBoardList", buyerBoardList);
    context.put("sellerBoardList", sellerBoardList);
    context.put("integratedBoardList", integratedBoardList);
    context.put("logList", logList);
    context.put("menuList", menuList);
    context.put("orderList", orderList);

  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {

    saveObjects(buyerMemberListFile, buyerMemberList);
    saveObjects(sellerMemberListFile, sellerMemberList);
    saveObjects(buyerCommentListFile, buyerCommentList);
    saveObjects(sellerCommentListFile, sellerCommentList);
    saveObjects(integratedCommentListFile, integratedCommentList);
    saveObjects(buyerBoardListFile, buyerBoardList);
    saveObjects(sellerBoardListFile, sellerBoardList);
    saveObjects(integratedBoardListFile, integratedBoardList);
    saveObjects(logListFile, logList);
    saveObjects(menuListFile, menuList);
    saveObjects(orderListFile, orderList);

  }

  private <T> List<T> loadObjects(File file, Class<T> elementType) {
    try(BufferedReader in = new BufferedReader(new FileReader(file))) {

      StringBuilder strBuilder = new StringBuilder();
      String str = null;
      while((str = in.readLine()) != null) {
        strBuilder.append(str);
      }

      Type listType = TypeToken.getParameterized(ArrayList.class, elementType).getType();
      List<T> list = new Gson().fromJson(strBuilder.toString(), listType);
      System.out.printf("%s 파일 데이터 로딩\n", file.getName());

      return list;
    }
    catch (Exception e) {
      System.out.printf("%s 파일 데이터 로딩 실패\n", file.getName());
      e.printStackTrace();
      return null;
    }
  }

  private <T extends CsvObject> void saveObjects(File file, List<T> list) {
    try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      out.write(new Gson().toJson(list));
      System.out.printf("%s 파일 데이터 저장\n", file.getName());
    }
    catch (Exception e) {
      System.out.printf("%s 파일 데이터 저장 실패\n", file.getName());
      e.printStackTrace();
    }
  }
}
