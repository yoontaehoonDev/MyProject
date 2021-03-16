package com.yoon.pms.table;

import java.io.File;
import java.sql.Date;
import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.util.JsonFileHandler;
import com.yoon.util.Request;
import com.yoon.util.Response;

public class BuyerMemberTable {

  File jsonFile = new File("buyerMembers.json");
  List<BuyerMember> list;

  public BuyerMemberTable() {
    list = JsonFileHandler.loadObjects(jsonFile, BuyerMember.class);

  }

  public void service(Request request, Response response) throws Exception {

    BuyerMember buyer = null;
    String[] fields = null;

    switch(request.getCommand()) {
      case "buyerMember/insert":

        fields = request.getDataList().get(0).split(",");

        buyer = new BuyerMember();

        if(list.size() > 0) {
          buyer.setNumber(list.get(list.size() - 1).getNumber() + 1);
        }
        else {
          buyer.setNumber(1);
        }

        buyer.setHash(Integer.parseInt(fields[0]));
        buyer.setNumber(Integer.parseInt(fields[1]));
        buyer.setId(fields[2]);
        buyer.setPassword(fields[3]);
        buyer.setName(fields[4]);
        buyer.setNickname(fields[5]);
        buyer.setEmail(fields[6]);
        buyer.setPhone(fields[7]);
        buyer.setRegisteredDate(new Date(System.currentTimeMillis()));

        list.add(buyer);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;

    }
  }
}
