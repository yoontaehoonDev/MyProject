package com.yoon.util;

import java.util.Scanner;

public class Prompt {
	static Scanner scanner = new Scanner(System.in);

	public static String inputString(String command) {
		System.out.print(command);
		return scanner.nextLine();
	}

	public static int inputInt(String command) {
		return Integer.parseInt(inputString(command));
	}

	public static void close() {
		scanner.close();
	}
}
