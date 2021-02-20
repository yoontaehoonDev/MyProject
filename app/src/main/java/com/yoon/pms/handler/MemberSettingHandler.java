package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberSettingHandler extends AbstractMemberHandler {

	private MemberValidatorHandler memberHandler;

	public MemberSettingHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, MemberValidatorHandler memberHandler) {
		super(buyerMemberList, sellerMemberList);
		this.memberHandler = memberHandler;
	}

	@Override
	public void service() {
		String name, choice;

		if(logCount == true) {
			MemberDeleteHandler delete = new MemberDeleteHandler(buyerMemberList, sellerMemberList);
			MemberUpdateHandler update = new MemberUpdateHandler(buyerMemberList, sellerMemberList, memberHandler);
			if(logStatus == 1) {
				System.out.println("■ 메뉴 - 회원 - 설정 ■");
				SellerMember s = sellerMemberNumber;

				System.out.println("[설정]");
				System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  소유주 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]\n", 
						s.getNumber(), s.getId(), s.getPassword(), s.getName(), s.getEmail(), s.getPhone());
				System.out.printf("상호명 : [%s]  사업자등록번호 : [%s]  영업시간 : [%s]  업종 : [%s]\n",
						s.getBusinessName(), s.getBusinessNumber(), s.getBusinessHour(), s.getCategory());


				System.out.println("[1. 정보 수정]  [2. 회원 탈퇴]  [3. 뒤로가기]\n");

				choice = Prompt.inputString("입력 : ");
				if(choice.equals("1")) {
					System.out.println("[개인정보 수정]");
					name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						update.service();
					}
					else {
						System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
					}
				}
				else if (choice.equals("2")) {
					System.out.println("[회원 탈퇴]");
					name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						delete.service();
					}
					else {
						System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
					}
				}

				else if (choice.equals("3")){
					System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
				}
				else {
					System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
				}
			}

			else if(logStatus == 0){
				System.out.println("■ 메뉴 - 회원 - 설정 ■");

				BuyerMember b = buyerMemberNumber;
				System.out.println("[설정]");
				System.out.printf("내 아이디 : [%s]  내 닉네임 : [%s]  내 이름 : [%s] 내 이메일 : [%s] 내 휴대폰번호 : [%s]\n",
						b.getId(), b.getNickname(), b.getName(), b.getEmail(), b.getPhone());
				System.out.println("[1. 정보 수정]  [2. 회원 탈퇴]  [3. 뒤로가기]\n");
				choice = Prompt.inputString("입력 : ");
				if(choice.equals("1")) {
					System.out.println("[개인정보 수정]");
					name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						update.service();
					}
					else {
						System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
					}
				}
				else if (choice.equals("2")) {
					System.out.println("[회원 탈퇴]");
					name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						delete.service();
					}
					else {
						System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
					}
				}

				else if (choice.equals("3")){
					System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
				}
				else {
					System.out.println("■ 메뉴 - 회원 ■ 으로 돌아갑니다.\n");
				}
			}
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.\n");
		}
	}


}
