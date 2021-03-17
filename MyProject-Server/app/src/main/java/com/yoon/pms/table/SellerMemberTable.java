package com.yoon.pms.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.JsonFileHandler;
import com.yoon.util.Request;
import com.yoon.util.Response;

public class SellerMemberTable implements DataTable {

  File jsonFile = new File("sellerMembers.json");
  List<SellerMember> list;

  public SellerMemberTable() {
    list = JsonFileHandler.loadObjects(jsonFile, SellerMember.class);

  }

  @Override
  public void service(Request request, Response response) throws Exception {

    SellerMember seller = null;
    String[] fields = null;

    switch(request.getCommand()) {
      case "sellerMember/insert":

        fields = request.getDataList().get(0).split(",");

        seller = new SellerMember();

        if(list.size() > 0) {
          seller.setHash(list.get(list.size() - 1).getNumber() + 1);
          seller.setNumber(list.get(list.size() - 1).getNumber() + 1);
        }
        else {
          seller.setHash(1);
          seller.setNumber(1);
        }

        seller.setId(fields[1]);
        seller.setPassword(fields[2]);
        seller.setName(fields[3]);
        seller.setEmail(fields[4]);
        seller.setPhone(fields[5]);
        seller.setAddress(fields[6]);
        seller.setBusinessName(fields[7]);
        seller.setBusinessNumber(fields[8]);
        seller.setBusinessHour(fields[9]);
        seller.setCategory(fields[10]);
        seller.setCategoryId(Integer.parseInt(fields[11]));
        seller.setRegisteredDate(new Date(System.currentTimeMillis()));

        list.add(seller);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "sellerMember/selectall":
        for(SellerMember b : list) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%s", 
              b.getNumber(),
              b.getId(),
              b.getName(),
              b.getEmail(),
              b.getPhone(),
              b.getAddress(),
              b.getBusinessName(),
              b.getBusinessNumber(),
              b.getBusinessHour(),
              b.getCategory(),
              b.getCategoryId(),
              b.getRegisteredDate()));
        }
        break;

      case "sellerMember/select":
        int num = Integer.parseInt(request.getDataList().get(0));

        seller = getSellerNumber(num);

        if(seller != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%s", 
              seller.getNumber(),
              seller.getId(),
              seller.getName(),
              seller.getEmail(),
              seller.getPhone(),
              seller.getAddress(),
              seller.getBusinessName(),
              seller.getBusinessNumber(),
              seller.getBusinessHour(),
              seller.getCategory(),
              seller.getCategoryId(),
              seller.getRegisteredDate()));
        }
        else {
          throw new Exception("해당 번호의 Seller가 없습니다.");
        }
        break;
      case "sellerMember/update":
        fields = request.getDataList().get(0).split(",");

        seller = getSellerNumber(Integer.parseInt(fields[0]));
        if(seller == null) {
          throw new Exception("해당 번호의 Seller가 없습니다.");
        }

        seller.setId(fields[1]);
        seller.setPassword(fields[2]);
        seller.setName(fields[3]);
        seller.setEmail(fields[4]);
        seller.setPhone(fields[5]);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "sellerMember/delete":
        num = Integer.parseInt(request.getDataList().get(0));
        seller = getSellerNumber(num);
        if(seller == null) {
          throw new Exception("해당 번호의 Seller가 없습니다.");
        }
        list.remove(seller);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private SellerMember getSellerNumber(int number) {
    for(SellerMember s : list) {
      if(s.getNumber() == number) {
        return s;
      }
    }
    return null;
  }
}
