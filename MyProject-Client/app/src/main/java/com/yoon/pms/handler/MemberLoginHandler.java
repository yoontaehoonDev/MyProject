package com.yoon.pms.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.yoon.driver.Statement;
import com.yoon.pms.App;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Log;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberLoginHandler implements Command {

	Statement stmt;

	public MemberLoginHandler(Statement stmt) {
		this.stmt = stmt;

	}

	@Override
	public void service() throws Exception {

		//		아이디 탐색을 해서 없으면 존재 회원 X로 표시 후, 리턴
		//
		//		stmt.executeQuery("buyerMember/count", null)
		//
		//		if(buyerMemberList.size() == 0 && sellerMemberList.size() == 0) {
		//			System.out.println("존재하는 회원이 없습니다.\n");
		//			return;
		//		}

		if(MemberValidator.logStatus == false) {
			System.out.println("■ 메뉴 - 회원 - 로그인 ■");
			String id, password, temp;
			boolean pswCheck;

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat login = new SimpleDateFormat("님 로그인 : yyyy년 MM월 dd일 HH시 mm분 ss초");
			while(true) {
				System.out.println("1. 구매자 로그인  2. 판매자 로그인\n");
				String choice = Prompt.inputString("선택 : ");

				if(choice.equals("1")) {
					if(buyerMemberList.size() == 0) {
						System.out.println("존재하는 구매 회원이 없습니다.\n");
						return;
					}
					while(true) {
						id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
						if(id.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.\n");
							return;
						}
						stmt.executeQuery("buyerMember/verifyId", id);
						BuyerMember idCheck = 

								if(idCheck != null) {
									while(true) {
										password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
										if(password.length() == 0) {
											System.out.println("메인 메뉴로 돌아갑니다.\n");
											return;
										}

										pswCheck = verifyBuyerPassword(password, idCheck);

										if(pswCheck) {
											Log log = new Log();

											temp =  idCheck.getId() + login.format(cal.getTime());
											log.setLoginTime(temp);

											System.out.println("로그인 성공\n");
											MemberValidator.logStatus = true;
											MemberValidator.buyerMember = idCheck;
											App.location = 0;
											return;
										}
										else {
											System.out.println("비밀번호가 틀렸습니다.\n");
										}
									}
								}
								else {
									System.out.println("존재하지 않는 ID 입니다.");
								}
					}
				}

				else if (choice.equals("2")) {
					if(sellerMemberList.size() == 0) {
						System.out.println("존재하는 판매 회원이 없습니다.\n");
						return;
					}
					while(true) {
						id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
						if(id.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.\n");
							return;
						}

						SellerMember idCheck = verifySellerId(id);

						if(idCheck != null) {
							while(true) {
								password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
								if(password.length() == 0) {
									System.out.println("메인 메뉴로 돌아갑니다.\n");
									return;
								}

								pswCheck = verifySellerPassword(password, idCheck);

								if(pswCheck) {
									Log log = new Log();
									temp =  idCheck.getId() + login.format(cal.getTime());
									log.setLoginTime(temp);
									logList.add(log);
									System.out.println("로그인 성공\n");
									logCount = true;
									logStatus = 1;

									MemberValidator.logStatus = true;
									App.location = 1;
									return;
								}
								else {
									System.out.println("비밀번호가 틀렸습니다.\n");
								}
							}
						}
						else {
							System.out.println("존재하지 않는 ID 입니다.");
						}
					}
				}
				else {
					System.out.println("다시 입력하세요.");
				}
			}
		}
		else {
			System.out.println("이미 로그인 되었습니다.\n");
		}
	}




}
