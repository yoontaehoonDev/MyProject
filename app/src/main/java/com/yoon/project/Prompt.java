package com.yoon.project;

import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String inputString(String command) {
    System.out.print(command);
    return scanner.nextLine();
  }

  static int inputInt(String command) {
    return Integer.parseInt(inputString(command));
  }

  static void close() {
    scanner.close();
  }
}
