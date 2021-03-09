package com.yoon.pms.listener;

import java.util.Map;
import com.yoon.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("[Sola Delivery]");
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("프로그램 종료");
  }
}
