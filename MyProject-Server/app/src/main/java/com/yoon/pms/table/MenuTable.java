package com.yoon.pms.table;

import java.io.File;
import java.util.List;
import com.yoon.pms.domain.Menu;
import com.yoon.util.JsonFileHandler;
import com.yoon.util.Request;
import com.yoon.util.Response;

public class MenuTable implements DataTable {
  File jsonFile = new File("menus.json");
  List<Menu> list;

  public MenuTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Menu.class);

  }

  @Override
  public void service(Request request, Response response) throws Exception {

    Menu menu = null;
    String[] fields = null;

    switch(request.getCommand()) {
      case "menu/insert":

        fields = request.getDataList().get(0).split(",");

        menu = new Menu();

        if(list.size() > 0) {
          menu.setNumber(list.get(list.size() - 1).getNumber() + 1);
        }
        else {
          menu.setNumber(1);
        }

        menu.setOwner(fields[1]);
        menu.setName(fields[2]);
        menu.setPrice(Integer.parseInt(fields[3]));
        menu.setExplain(fields[4]);
        list.add(menu);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;

      case "menu/selectall":
        for(Menu m : list) {
          response.appendData(String.format("%d,%s,%s,%d,%s", 
              m.getNumber(),
              m.getOwner(),
              m.getName(),
              m.getPrice(),
              m.getExplain()));
        }
        break;

      case "menu/select":
        int num = Integer.parseInt(request.getDataList().get(0));

        menu = getMenuNumber(num);

        if(menu != null) {
          response.appendData(String.format("%d,%s,%s,%d,%s", 
              menu.getNumber(),
              menu.getOwner(),
              menu.getName(),
              menu.getPrice(),
              menu.getExplain()));
        }
        else {
          throw new Exception("해당 번호의 댓글이 없습니다.");
        }
        break;
      case "menu/update":
        fields = request.getDataList().get(0).split(",");

        menu = getMenuNumber(Integer.parseInt(fields[0]));
        if(menu == null) {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }

        menu.setName(fields[2]);
        menu.setPrice(Integer.parseInt(fields[3]));
        menu.setExplain(fields[4]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "menu/delete":
        num = Integer.parseInt(request.getDataList().get(0));
        menu = getMenuNumber(num);
        if(menu == null) {
          throw new Exception("해당 번호의 Buyer가 없습니다.");
        }
        list.remove(menu);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Menu getMenuNumber(int number) {
    for(Menu m : list) {
      if(m.getNumber() == number) {
        return m;
      }
    }
    return null;
  }
}
